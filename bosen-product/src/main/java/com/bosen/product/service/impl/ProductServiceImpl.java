package com.bosen.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.vo.request.ApproveBatchInfoVO;
import com.bosen.common.vo.request.ApproveInfoVO;
import com.bosen.product.constant.AttributeTypeEnum;
import com.bosen.product.constant.ProductApproveStatusEnum;
import com.bosen.product.domain.ProductApproveRecordDO;
import com.bosen.product.domain.ProductAreaDO;
import com.bosen.product.domain.ProductDO;
import com.bosen.product.mapper.ProductMapper;
import com.bosen.product.service.IProductApproveRecordService;
import com.bosen.product.service.IProductAreaService;
import com.bosen.product.service.IProductAttributeService;
import com.bosen.product.service.IProductService;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.request.ProductRackingOrDownVO;
import com.bosen.product.vo.request.ProductUpsertVO;
import com.bosen.product.vo.response.ProductAttributeDetailVO;
import com.bosen.product.vo.response.ProductDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements IProductService {

    @Resource
    private IProductAttributeService productAttributeService;

    @Resource
    private IProductAreaService productAreaService;

    @Resource
    private IProductApproveRecordService productApproveRecordService;

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
    public ResponseData<Void> rackingOrDown(ProductRackingOrDownVO productRackingOrDownVO) {
        // 判断商品是否存在

        // 判断商品是否存在上架记录
        // 不存在上架记录，新增，否则，更新上架记录
        // 判断商品是否可以上架
        // 上架用户是否是商家自身
        // 更新商品上架状态
        // 同步数据到es，这里可以使用mq操作
        return null;
    }

    @Override
    public ResponseData<ProductDetailVO> detail(String id) {
        ProductDO productDO = this.getById(id);
        if (Objects.isNull(productDO)) {
            throw new BusinessException(ResponseCode.PRODUCT_NOT_EXIT_ERROR);
        }
        ProductDetailVO detailVO = new ProductDetailVO();
        BeanUtils.copyProperties(productDO, detailVO);
        // 查询商品规格
        List<ProductAttributeDetailVO> attributeDetailVOS = productAttributeService.listProductAttributeDetailByProductId(id);
        detailVO.setAttributeList(attributeDetailVOS.stream().filter(i -> Objects.equals(AttributeTypeEnum.ATTR.getCode(), i.getType())).collect(Collectors.toList()));
        detailVO.setSpecList(attributeDetailVOS.stream().filter(i -> Objects.equals(AttributeTypeEnum.SPEC.getCode(), i.getType())).collect(Collectors.toList()));
        return ResponseData.success(detailVO);
    }
}
