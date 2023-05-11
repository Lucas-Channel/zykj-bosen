package com.bosen.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.common.RedisKeyConstant;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.vo.request.ApproveBatchInfoVO;
import com.bosen.common.vo.request.ApproveInfoVO;
import com.bosen.elasticsearch.domain.ESProductAttributeAndValueModelDO;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.domain.EsProductSalesAreaDO;
import com.bosen.elasticsearch.vo.request.DownProductRequestVO;
import com.bosen.product.constant.AttributeTypeEnum;
import com.bosen.product.constant.ProductApproveStatusEnum;
import com.bosen.product.domain.*;
import com.bosen.product.feign.EsApiFeignService;
import com.bosen.product.mapper.ProductAttributeValueMapper;
import com.bosen.product.mapper.ProductMapper;
import com.bosen.product.service.*;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.request.ProductRackingOrDownVO;
import com.bosen.product.vo.request.ProductUpsertVO;
import com.bosen.product.vo.response.ProductAttributeDetailVO;
import com.bosen.product.vo.response.ProductDetailVO;
import com.bosen.product.vo.response.ProductStoreShopDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements IProductService {

    @Resource
    private IProductAttributeService productAttributeService;

    @Resource
    private IProductAreaService productAreaService;

    @Resource
    private IProductApproveRecordService productApproveRecordService;

    @Resource
    private IProductSkuService productSkuService;

    @Resource
    private IProductStoreShopService productStoreShopService;

    @Resource
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Resource
    private EsApiFeignService esApiFeignService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseData<PageData<ProductDetailVO>> listPages(ProductQueryVO queryVO) {
        Page<ProductDetailVO> page = new Page<>(queryVO.getCurrent(), queryVO.getSize());
        IPage<ProductDetailVO> list = baseMapper.listPages(page, queryVO);
        return ResponseData.success(new PageData<>(list.getTotal(), list.getRecords()));
    }

    @Override
    public ResponseData<PageData<ProductDetailVO>> listPagesForMerchant(ProductQueryVO queryVO) {
        // 获取当前登录账户信息
        queryVO.setMerchantId(null);
        queryVO.setMerchantRoleId(null);
        return this.listPages(queryVO);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsertProduct(ProductUpsertVO formData) {
        if (Objects.equals(formData.getSalesAllArea(), YesOrNoConstant.NO) && CollUtil.isEmpty(formData.getAreaList())) {
            throw new BusinessException(ResponseCode.SAVE_PRODUCT_AREA_LIST_NOT_EMPTY_ERROR);
        }
        // step1 保存spu基本信息
        ProductDO productDO = new ProductDO();
        BeanUtils.copyProperties(formData, productDO);
        productDO.setStatus(ProductApproveStatusEnum.WAIT_SUBMIT_APPROVE_APPLY.getCode());
        boolean saveOrUpdate = this.saveOrUpdate(productDO);
        // step2 保存spu属性和规格
        if (saveOrUpdate) {
            productAttributeService.upsertProductAttribute(formData.getAttrList(), formData.getSpecList(), productDO.getId());
            if (Objects.equals(formData.getSalesAllArea(), YesOrNoConstant.NO)) {
                // step2.4 保存spu销售范围
                productAreaService.lambdaUpdate().eq(ProductAreaDO::getProductId, productDO.getId()).remove();
                List<ProductAreaDO> areaDOS = formData.getAreaList().stream().map(i -> {
                    ProductAreaDO attributeDO = new ProductAreaDO();
                    BeanUtils.copyProperties(i, attributeDO);
                    attributeDO.setProductId(productDO.getId());
                    return attributeDO;
                }).collect(Collectors.toList());
                productAreaService.saveBatch(areaDOS);
            }
        } else {
            throw new BusinessException("保存商品失败");
        }
        return ResponseData.success();
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> approveProduct(ApproveInfoVO approveInfoVO) {
        String adminId = "1";
        String adminRoleId = "1";
        String adminName = "超级管理员";
        ProductDO productDO = baseMapper.selectById(approveInfoVO.getOriginalId());
        if (!Objects.equals(productDO.getStatus(), ProductApproveStatusEnum.WAIT_APPROVE.getCode())) {
            throw new BusinessException(ResponseCode.APPROVE_PRODUCT_STATUS_ERROR);
        }
        productDO.setStatus(Objects.equals(approveInfoVO.getAgree(), YesOrNoConstant.YES) ? ProductApproveStatusEnum.AGREE.getCode() : ProductApproveStatusEnum.DISAGREE.getCode());
        this.updateById(productDO);
        // 添加审核记录
        ProductApproveRecordDO approveRecordDO = new ProductApproveRecordDO();
        approveRecordDO.setProductId(approveInfoVO.getOriginalId())
                .setOperationUserId(adminId)
                .setOperationUserRoleId(adminRoleId)
                .setOperationUserName(adminName)
                .setStatus(productDO.getStatus())
                .setAgreeAdvice(approveInfoVO.getReason());
        productApproveRecordService.save(approveRecordDO);
        return ResponseData.success();
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> approveBatch(ApproveBatchInfoVO approveBatchInfoVOS) {
        String adminId = "1";
        String adminRoleId = "1";
        String adminName = "超级管理员";
        List<ProductDO> productDOs = baseMapper.selectBatchIds(approveBatchInfoVOS.getOriginalId());
        List<ProductDO> dos = productDOs.stream().filter(i -> !Objects.equals(i.getStatus(), ProductApproveStatusEnum.WAIT_APPROVE.getCode())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(dos)) {
            throw new BusinessException(ResponseCode.APPROVE_PRODUCT_STATUS_ERROR);
        }
        productDOs.forEach(i -> {
            i.setStatus(Objects.equals(approveBatchInfoVOS.getAgree(), YesOrNoConstant.YES) ? ProductApproveStatusEnum.AGREE.getCode() : ProductApproveStatusEnum.DISAGREE.getCode());
            // 添加审核记录
            ProductApproveRecordDO approveRecordDO = new ProductApproveRecordDO();
            approveRecordDO.setProductId(i.getId())
                    .setOperationUserId(adminId)
                    .setOperationUserRoleId(adminRoleId)
                    .setOperationUserName(adminName)
                    .setStatus(i.getStatus())
                    .setAgreeAdvice(approveBatchInfoVOS.getReason());
            productApproveRecordService.save(approveRecordDO);
        });
        this.updateBatchById(productDOs);
        return ResponseData.success();
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> submitApproveProduct(String id) {
        String merchantId = "1";
        String merchantRoleId = "1";
        String merchantName = "lucas";
        ProductDO productDO = baseMapper.selectById(id);
        if (Objects.isNull(productDO)) {
            throw new BusinessException(ResponseCode.PRODUCT_NOT_EXIT_ERROR);
        }
        if (!Objects.equals(productDO.getStatus(), ProductApproveStatusEnum.WAIT_SUBMIT_APPROVE_APPLY.getCode())) {
            throw new BusinessException(ResponseCode.SUBMIT_APPROVE_PRODUCT_STATUS_ERROR);
        }
        if (!Objects.equals(productDO.getMerchantId(), merchantId) && !Objects.equals(productDO.getMerchantRoleId(), merchantRoleId)) {
            throw new BusinessException(ResponseCode.SUBMIT_APPROVE_PRODUCT_BY_SELF_ERROR);
        }
        productDO.setStatus(ProductApproveStatusEnum.WAIT_APPROVE.getCode());
        this.updateById(productDO);
        // 添加审核记录
        ProductApproveRecordDO approveRecordDO = new ProductApproveRecordDO();
        approveRecordDO.setProductId(id)
                .setOperationUserId(merchantId)
                .setOperationUserRoleId(merchantRoleId)
                .setOperationUserName(merchantName)
                .setStatus(productDO.getStatus())
                .setAgreeAdvice("提交审核");
        productApproveRecordService.save(approveRecordDO);
        return ResponseData.success();
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> submitApproveProductBatch(List<String> ids) {
        String merchantId = null;
        String merchantRoleId = null;
        String merchantName = null;
        List<ProductDO> list = baseMapper.selectBatchIds(ids);
        List<ProductDO> notSit = list.stream().filter(i -> !Objects.equals(i.getStatus(), ProductApproveStatusEnum.WAIT_SUBMIT_APPROVE_APPLY.getCode())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(notSit)) {
            throw new BusinessException(ResponseCode.SUBMIT_APPROVE_PRODUCT_STATUS_ERROR);
        }
        List<ProductDO> notLoginUser = list.stream().filter(i -> !Objects.equals(i.getMerchantId(), merchantId) && !Objects.equals(i.getMerchantRoleId(), merchantRoleId)).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(notLoginUser)) {
            throw new BusinessException(ResponseCode.SUBMIT_APPROVE_PRODUCT_BY_SELF_ERROR);
        }
        list.forEach(i -> {
            i.setStatus(ProductApproveStatusEnum.WAIT_APPROVE.getCode());
            // 添加审核记录
            ProductApproveRecordDO approveRecordDO = new ProductApproveRecordDO();
            approveRecordDO.setProductId(i.getId())
                    .setOperationUserId(merchantId)
                    .setOperationUserRoleId(merchantRoleId)
                    .setOperationUserName(merchantName)
                    .setStatus(i.getStatus())
                    .setAgreeAdvice("提交审核");
            productApproveRecordService.save(approveRecordDO);
        });
        this.updateBatchById(list);
        return ResponseData.success();
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> deleteByIds(List<Long> ids) {
        // 待审核，上架，审核通过
        List<Integer> statusList = new ArrayList<>(Arrays.asList(ProductApproveStatusEnum.WAIT_APPROVE.getCode(),
                ProductApproveStatusEnum.UP.getCode(), ProductApproveStatusEnum.AGREE.getCode()));
        List<ProductDO> list = baseMapper.selectBatchIds(ids);
        List<ProductDO> notDelete = list.stream().filter(i -> statusList.contains(i.getStatus())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(notDelete)) {
            throw new BusinessException(ResponseCode.CANNOT_DELETE_PRODUCT_ERROR);
        }
        list.forEach(i -> {
            i.setDelFlag(1);
        });
        return ResponseData.judge(this.updateBatchById(list));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> rackingProduct(ProductRackingOrDownVO productRackingOrDownVO) {
        // 判断商品及sku是否符合上架条件
        List<ProductDO> productDOList = this.checkRackingProduct(productRackingOrDownVO, "1", "1");
        List<ProductSkuDO> skuList = this.checkRackingProductSku(productRackingOrDownVO, "1", "1");
        // 删除上架记录，物理删除
        ResponseData<Boolean> responseData = productStoreShopService.deleteByProductIdPhysic(productRackingOrDownVO.getProductIds());
        if (!Objects.equals(responseData.getCode(), ResponseCode.SUCCESS.getCode())) throw new BusinessException(ResponseCode.DELETE_PRODUCT_UPPER_RECORD_HISTORY_ERROR);
        Map<String, ProductDO> productDOMap = productDOList.stream().collect(Collectors.toMap(ProductDO::getId, Function.identity(), (oldSpu, newSpu) -> oldSpu));
        // 封装同步到es的数据
        List<ESProductSkuModelDO> esList = new ArrayList<>();
        productRackingOrDownVO.getStoreShopList().forEach(ss -> {
            skuList.forEach(sku -> {
                ESProductSkuModelDO esProductSkuModelDO = new ESProductSkuModelDO();
                ProductDO productDO = productDOMap.get(sku.getProductId());
                esProductSkuModelDO
                        .setSpuId(sku.getProductId())
                        .setBrandId(productDO.getBrandId())
                        .setCategoryId(productDO.getCategoryId())
                        .setMerchantCategoryId(productDO.getMerchantCategoryId())
                        .setProductType(productDO.getProductType())
                        .setShopId(ss.getShopId())
                        .setShopName(ss.getShopName())
                        .setStoreId(ss.getStoreId())
                        .setStoreName(ss.getStoreName())
                        .setSkuId(sku.getId())
                        .setSkuName(sku.getName())
                        .setSkuImg(sku.getSkuImg())
                        .setAlbum(sku.getAlbum())
                        .setOriginPrice(sku.getOriginPrice())
                        .setVipPrice(sku.getVipPrice())
                        .setSalesPrice(sku.getSalesPrice())
                        .setUpperDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
                        .setSalesCount(Objects.nonNull(sku.getSalesCount()) ? sku.getSalesCount() : BigDecimal.ZERO)
                        .setSalesAllArea(Objects.equals(productDO.getSalesAllArea(), YesOrNoConstant.YES));
                // 获取sku下的规格信息
                List<ESProductAttributeAndValueModelDO> esProductAttributeAndValueModelDOS = productAttributeValueMapper.listEsProductAttributeAndValueBySkuId(sku.getId());
                esProductSkuModelDO.setAttrs(esProductAttributeAndValueModelDOS);
                esList.add(esProductSkuModelDO);
            });
        });
        // 调用feign接口，进行商品上架到es
        ResponseData<Void> data = esApiFeignService.rackingProduct(esList);
        if (!Objects.equals(data.getCode(), ResponseCode.SUCCESS.getCode())) {
            throw new BusinessException(ResponseCode.RACKING_PRODUCT_ERROR);
        }
        // 上架后更新状态，保存上架记录数据，使用事务同步处理器处理,集成mq之后，放入mq
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                // 新增上架记录
                addRecord(productRackingOrDownVO);
                // 更新商品上架状态
                updateStatus(productDOList, skuList);
                // 设置spu商品销售范围，缓存至redis，key:productId, val: list
                setSalesAreaToRedis(productDOList);
                // 缓存商品库存到redis
                setStockToRedis(skuList);
            }
        });
        return ResponseData.success();
    }

    private void setSalesAreaToRedis(List<ProductDO> productDOList) {
        List<String> ids = productDOList.stream().filter(i -> !Objects.equals(i.getSalesAllArea(), YesOrNoConstant.YES)).map(ProductDO::getId).collect(Collectors.toList());
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        List<ProductAreaDO> areaDOS = productAreaService.lambdaQuery().select(ProductAreaDO::getProductId, ProductAreaDO::getProvinceCode, ProductAreaDO::getProvinceName,
                        ProductAreaDO::getAllowAllCity, ProductAreaDO::getCityCode, ProductAreaDO::getCityName,
                        ProductAreaDO::getAllowAllRegion, ProductAreaDO::getRegionCode, ProductAreaDO::getRegionName,
                        ProductAreaDO::getAllowAllCommunity, ProductAreaDO::getCommunityCode, ProductAreaDO::getCommunityName)
                .in(ProductAreaDO::getProductId, ids).list();
        areaDOS.stream().map(i -> BeanUtil.copyProperties(i, EsProductSalesAreaDO.class))
                .collect(Collectors.groupingBy(EsProductSalesAreaDO::getProductId))
                .forEach((k, v) -> redisTemplate.opsForValue().set(RedisKeyConstant.PRODUCT_AREA_KEY + "-" + k, JSONUtil.toJsonStr(v)));
    }

    private void setStockToRedis(List<ProductSkuDO> skuList) {
        Map<String, Map<String, BigDecimal>> map = new HashMap<>();
        skuList.forEach(i -> {
            HashMap<String, BigDecimal> hashMap = new HashMap<>();
            hashMap.put("stockNum", i.getStockNum());
            hashMap.put("lockedStockNum", i.getLockedStockNum());
            map.put(i.getId(), hashMap);
        });
        redisTemplate.opsForHash().putAll(RedisKeyConstant.PRODUCT_SKU_STOCK_INFO_KEY, map);
    }

    private void addRecord(ProductRackingOrDownVO productRackingOrDownVO) {
        List<ProductStoreShopDO> list = new ArrayList<>();
        productRackingOrDownVO.getProductIds().forEach(productId -> {
            list.addAll(productRackingOrDownVO.getStoreShopList().stream().map(i -> {
                ProductStoreShopDO productStoreShopDO = new ProductStoreShopDO();
                BeanUtils.copyProperties(i, productStoreShopDO);
                productStoreShopDO.setProductId(productId);
                return productStoreShopDO;
            }).collect(Collectors.toList()));
        });
        boolean saveBatch = productStoreShopService.saveBatch(list);
        if (!saveBatch) {
            throw new BusinessException(ResponseCode.SAVE_PRODUCT_STORE_SHOP_ERROR);
        }
    }

    private void updateStatus(List<ProductDO> productDOList, List<ProductSkuDO> skuList) {
        productDOList.forEach(i -> {
            i.setStatus(ProductApproveStatusEnum.UP.getCode());
            i.setPushDateTime(LocalDateTime.now());
        });
        skuList.forEach(i -> {
            i.setStatus(ProductApproveStatusEnum.UP.getCode());
            i.setUpdateTime(LocalDateTime.now());
        });
        boolean updateBatchById = this.updateBatchById(productDOList);
        boolean skuUpdate = productSkuService.updateBatchById(skuList);
        if (!updateBatchById) {
            throw new BusinessException(ResponseCode.UPDATE_PRODUCT_ERROR);
        }
        if (!skuUpdate) {
            throw new BusinessException(ResponseCode.UPDATE_PRODUCT_ERROR);
        }
    }

    private List<ProductDO> checkRackingProduct(ProductRackingOrDownVO productRackingOrDownVO, String merchantId, String merchantRoleId) {
        List<ProductDO> list = this.lambdaQuery()
                .in(ProductDO::getId, productRackingOrDownVO.getProductIds())
                .eq(ProductDO::getMerchantId, merchantId)
                .eq(ProductDO::getMerchantRoleId, merchantRoleId)
                .in(ProductDO::getStatus, Arrays.asList(ProductApproveStatusEnum.AGREE.getCode(), ProductApproveStatusEnum.DOWN.getCode()))
                .list();
        if (!Objects.equals(list.size(), productRackingOrDownVO.getProductIds().size())) {
            throw new BusinessException(ResponseCode.RACKING_COUNT_ERROR);
        }
        return list;
    }

    private List<ProductSkuDO> checkRackingProductSku(ProductRackingOrDownVO productRackingOrDownVO, String merchantId, String merchantRoleId) {
        // 是否设置商品库存
        List<ProductSkuDO> skuList = productSkuService.lambdaQuery()
                .in(ProductSkuDO::getProductId, productRackingOrDownVO.getProductIds())
                .eq(ProductSkuDO::getMerchantId, merchantId)
                .eq(ProductSkuDO::getMerchantRoleId, merchantRoleId)
                .list();
        int skuProductListSize = skuList.stream().map(ProductSkuDO::getProductId).distinct().collect(Collectors.toList()).size();
        if (!Objects.equals(skuProductListSize, productRackingOrDownVO.getProductIds().size())) {
            throw new BusinessException(ResponseCode.RACKING_COUNT_ERROR);
        }
        return skuList;
    }

    @Override
    public ResponseData<ProductDetailVO> detail(String id) {
        ProductDetailVO detailVO = baseMapper.detailById(id);
        if (Objects.isNull(detailVO)) {
            throw new BusinessException(ResponseCode.PRODUCT_NOT_EXIT_ERROR);
        }
        // 查询商品规格
        List<ProductAttributeDetailVO> attributeDetailVOS = productAttributeService.listProductAttributeDetailByProductId(id);
        detailVO.setAttributeList(attributeDetailVOS.stream().filter(i -> Objects.equals(AttributeTypeEnum.ATTR.getCode(), i.getType())).collect(Collectors.toList()));
        detailVO.setSpecList(attributeDetailVOS.stream().filter(i -> Objects.equals(AttributeTypeEnum.SPEC.getCode(), i.getType())).collect(Collectors.toList()));
        // 商品上架商城与店铺
        if (Objects.equals(detailVO.getStatus(), ProductApproveStatusEnum.UP.getCode())) {
            ResponseData<List<ProductStoreShopDetailVO>> data = productStoreShopService.listByProductId(id);
            if (!Objects.equals(data.getCode(), ResponseCode.SUCCESS.getCode())) {
                throw new BusinessException("查询商品上架商城与店铺数据列表失败");
            }
            detailVO.setStoreShopDetailList(data.getData());
        }
        return ResponseData.success(detailVO);
    }

    @Override
    @Transactional
    public ResponseData<Void> downProduct(DownProductRequestVO downProductRequestVO) {
        // 是否存在非上架状态的商品
        List<ProductDO> list = this.lambdaQuery().ne(ProductDO::getStatus, ProductApproveStatusEnum.UP.getCode())
                .in(ProductDO::getId, downProductRequestVO.getSpuIds())
                .list();
        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(ResponseCode.DOWN_PRODUCT_ONLY_UP_ERROR);
        }
        // 删除上架记录，物理删除
        ResponseData<Boolean> responseData = productStoreShopService.deleteByProductIdPhysic(downProductRequestVO.getSpuIds());
        if (!Objects.equals(responseData.getCode(), ResponseCode.SUCCESS.getCode())) throw new BusinessException(ResponseCode.DELETE_PRODUCT_UPPER_RECORD_HISTORY_ERROR);
        // 删除es中的记录
        ResponseData<Void> data = esApiFeignService.downProduct(downProductRequestVO);
        if (!Objects.equals(data.getCode(), ResponseCode.SUCCESS.getCode())) {
            throw new BusinessException(ResponseCode.DOWN_PRODUCT_ERROR);
        }
        // 修改商品状态为已下架
        List<ProductDO> productDOList = this.lambdaQuery().in(ProductDO::getId, downProductRequestVO.getSpuIds())
                .list();
        productDOList.forEach(i -> {
            i.setStatus(ProductApproveStatusEnum.DOWN.getCode());
            i.setPullDateTime(LocalDateTime.now());
        });
        this.updateBatchById(productDOList);
        return ResponseData.success();
    }
}
