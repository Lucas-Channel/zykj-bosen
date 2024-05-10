package com.bosen.search.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.bosen.common.constant.common.RedisKeyConstant;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.elasticsearch.domain.ESProductAttributeAndValueModelDO;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.domain.EsProductSalesAreaDO;
import com.bosen.elasticsearch.vo.request.DownProductRequestVO;
import com.bosen.search.constant.SortTypeEnum;
import com.bosen.search.mapper.EsProductMapper;
import com.bosen.search.service.IEsProductService;
import com.bosen.search.vo.request.HomePageQueryVO;
import com.bosen.search.vo.request.HotWordsRequestVO;
import com.bosen.search.vo.request.ProductQueryVO;
import com.bosen.search.vo.request.UpdateSkuSalesCountVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * es 商品搜索接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
@Slf4j
@Service
public class EsProductServiceImpl implements IEsProductService {
    @Resource
    private EsProductMapper esProductMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    @Transactional
    public ResponseData<Void> rackingProduct(List<ESProductSkuModelDO> productSkuList) {
        try {
            esProductMapper.saveAll(productSkuList);
        } catch (Exception e) {
            // 可能是spring boot 和 es 的版本问题没法解析结果
            if (!e.getMessage().contains("200 OK")) {
                log.error("上架失败：》》》》》》{}", e.getMessage());
                throw new BusinessException(ResponseCode.RACKING_PRODUCT_ERROR);
            }
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData<List<EsProductSalesAreaDO>> getProductSalesArea(String spuId) {
        Object o = redisTemplate.opsForValue().get(RedisKeyConstant.PRODUCT_AREA_KEY + "-" + spuId);
        if (null == o) {
            return ResponseData.success();
        }
        String json = o.toString();
        List<EsProductSalesAreaDO> areaDOS = JSONUtil.toList(json, EsProductSalesAreaDO.class);
        return ResponseData.success(areaDOS);
    }

    @Override
    public ResponseData<PageData<ESProductSkuModelDO>> pageData(ProductQueryVO queryVO) {
        QueryBuilder queryBuilder = getBoolQueryBuilder(queryVO);
        FieldSortBuilder sortBuilder = getSortBuilder(queryVO);
        PageData<ESProductSkuModelDO> esProductList = getEsProductList(queryBuilder, sortBuilder, queryVO);
        return ResponseData.success(esProductList);
    }

    @Override
    @Transactional
    public ResponseData<Void> updateEsSalesCount(List<UpdateSkuSalesCountVO> updateSkuSalesCountVOS) {
        List<ESProductSkuModelDO> list = updateSkuSalesCountVOS.stream().map(i -> esProductMapper.findBySkuIdAndShopIdAndStoreId(i.getSkuId(), i.getShopId(), i.getStoreId())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(list)) {
            Map<String, BigDecimal> map = updateSkuSalesCountVOS.stream().collect(Collectors.toMap(UpdateSkuSalesCountVO::getSkuId, UpdateSkuSalesCountVO::getSalesCount, (v1, v2) -> v1));
            list.forEach(i -> i.setSalesCount(i.getSalesCount().add(map.get(i.getSkuId()))));
            try {
                esProductMapper.saveAll(list);
            } catch (Exception e) {
                if (!e.getMessage().contains("200 OK")) {
                    log.error("更新数据失败：》》》》》》{}", e.getMessage());
                    throw new BusinessException(ResponseCode.UPDATE_PRODUCT_SKU_SALES_COUNT_ERROR);
                }
            }
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData<Map<String, Map<String, List<ESProductAttributeAndValueModelDO>>>> getSpuHasRackingAttribute(String spuId, String shopId, String storeId) {
        List<ESProductSkuModelDO> skuModelDOS = esProductMapper.findBySpuIdAndShopIdAndStoreId(spuId, shopId, storeId);
        List<ESProductAttributeAndValueModelDO> attrs = skuModelDOS.stream().map(ESProductSkuModelDO::getAttrs).flatMap(Collection::stream).collect(Collectors.toList());
        Map<String, List<ESProductAttributeAndValueModelDO>> collect = attrs.stream().collect(Collectors.groupingBy(ESProductAttributeAndValueModelDO::getAttributeType));
        Map<String, Map<String, List<ESProductAttributeAndValueModelDO>>> map = new HashMap<>();
        collect.forEach((k, v) -> {
            Map<String, List<ESProductAttributeAndValueModelDO>> listMap = v.stream().collect(Collectors.groupingBy(ESProductAttributeAndValueModelDO::getAttributeName));
            map.put(k, listMap);
        });
        return ResponseData.success(map);
    }

    @Override
    public ResponseData<Void> downProduct(DownProductRequestVO downProductRequestVO) {
        try {
            esProductMapper.deleteBySpuIdInAndShopIdInAndStoreIdIn(downProductRequestVO.getSpuIds(), downProductRequestVO.getShopIds(), downProductRequestVO.getStoreIds());
        } catch (Exception e) {
            if (!e.getMessage().contains("200 OK")) {
                log.error("下架商品失败：》》》》》》{}", e.getMessage());
                throw new BusinessException(ResponseCode.DOWN_PRODUCT_ERROR);
            }
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData<PageData<ESProductSkuModelDO>> listHomePageProduct(HomePageQueryVO pageQueryVO) {
        List<String> hotWords = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(RedisKeyConstant.USER_HOT_WORDS_KEY + ":用户id", 1, Integer.MAX_VALUE, 0, 3);
        if (!CollectionUtils.isEmpty(typedTupleSet)) {
           hotWords = typedTupleSet.stream().map(ZSetOperations.TypedTuple::getValue).map(String::valueOf).collect(Collectors.toList());
        }
        BoolQueryBuilder boolQueryBuilder = getBoolQueryBuilder(hotWords);
        FieldSortBuilder sortBuilder = getSortBuilder();
        ProductQueryVO queryVO = new ProductQueryVO();
        queryVO.setSize(pageQueryVO.getSize());
        queryVO.setCurrent(pageQueryVO.getCurrent());
        PageData<ESProductSkuModelDO> esProductList = getEsProductList(boolQueryBuilder, sortBuilder, queryVO);
        return ResponseData.success(esProductList);
    }

    private BoolQueryBuilder getBoolQueryBuilder(List<String> userHotWords) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (CollUtil.isNotEmpty(userHotWords)) {
            userHotWords.forEach(i -> {
                queryBuilder.should(QueryBuilders.multiMatchQuery(i, "skuName", "storeName"));
            });
        }
        return queryBuilder;
    }

    /**
     * 封装排序规则
     * @param queryVO 参数
     * @return 结果
     */
    private FieldSortBuilder getSortBuilder() {
        return SortBuilders.fieldSort("salesCount").order(SortOrder.DESC);
    }

    @Override
    public ResponseData<Void> saveHotWords(HotWordsRequestVO hotWordsRequestVO) {
        // 每次搜索点击，热搜词热度+1
        redisTemplate.opsForZSet().incrementScore(RedisKeyConstant.PRODUCT_HOT_WORDS_KEY, hotWordsRequestVO.getHotWords(), 1);
        // 保存用户热搜词
        redisTemplate.opsForZSet().incrementScore(RedisKeyConstant.USER_HOT_WORDS_KEY + ":用户id", hotWordsRequestVO.getHotWords(), 1);
        return ResponseData.success();
    }

    @Override
    public ResponseData<List<String>> listHotWords(String searchKey) {
        // 获取前十的热搜词
        List<String> hotWords = new ArrayList<>();
        if (StringUtils.hasLength(searchKey)) {
            // 如果搜索key不为空，推荐相关的热词前十
            Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(RedisKeyConstant.PRODUCT_HOT_WORDS_KEY, 1, Integer.MAX_VALUE);
            if (!CollectionUtils.isEmpty(typedTupleSet)) {
                hotWords = typedTupleSet.stream().map(ZSetOperations.TypedTuple::getValue).map(String::valueOf).filter(i -> i.contains(searchKey)).collect(Collectors.toList());
            }
        } else {
            // 返回热搜词前十
            Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(RedisKeyConstant.PRODUCT_HOT_WORDS_KEY, 1, Integer.MAX_VALUE, 0, 10);
            if (!CollectionUtils.isEmpty(typedTupleSet)) {
                hotWords = typedTupleSet.stream().map(ZSetOperations.TypedTuple::getValue).map(String::valueOf).collect(Collectors.toList());
            }
        }
        return ResponseData.success(hotWords);
    }

    /**
     * 封装查询参数
     * @param queryVO 参数
     * @return 结果
     */
    private BoolQueryBuilder getBoolQueryBuilder(ProductQueryVO queryVO) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.hasLength(queryVO.getKeyWord())) {
            queryBuilder.should(QueryBuilders.matchQuery("skuName", queryVO.getKeyWord()))
                    .should(QueryBuilders.matchQuery("storeName", queryVO.getKeyWord()));
        }
        if (CollUtil.isNotEmpty(queryVO.getSpuIds())) {
            queryBuilder.must(QueryBuilders.termsQuery("spuId", queryVO.getSpuIds()));
        }
        if (CollUtil.isNotEmpty(queryVO.getSkuIds())) {
            queryBuilder.must(QueryBuilders.termsQuery("skuId", queryVO.getSkuIds()));
        }
        if (CollUtil.isNotEmpty(queryVO.getBrandIds())) {
            queryBuilder.must(QueryBuilders.termsQuery("brandId", queryVO.getBrandIds()));
        }
        if (CollUtil.isNotEmpty(queryVO.getCategoryIds())) {
            queryBuilder.must(QueryBuilders.termsQuery("categoryId", queryVO.getCategoryIds()));
        }
        if (CollUtil.isNotEmpty(queryVO.getMerchantCategoryIds())) {
            queryBuilder.must(QueryBuilders.termsQuery("merchantCategoryId", queryVO.getMerchantCategoryIds()));
        }
        if (CollUtil.isNotEmpty(queryVO.getShopIds())) {
            queryBuilder.must(QueryBuilders.termsQuery("shopId", queryVO.getShopIds()));
        }
        if (CollUtil.isNotEmpty(queryVO.getStoreIds())) {
            queryBuilder.must(QueryBuilders.termsQuery("storeId", queryVO.getStoreIds()));
        }
        if (Objects.nonNull(queryVO.getProductType())) {
            queryBuilder.must(QueryBuilders.termQuery("productType", queryVO.getProductType()));
        }
        if (Objects.nonNull(queryVO.getMinPrice()) && BigDecimal.ZERO.compareTo(queryVO.getMinPrice()) < 0) {
            queryBuilder.must(QueryBuilders.rangeQuery("salesPrice").gte(queryVO.getMinPrice()));
        }
        if (Objects.nonNull(queryVO.getMaxPrice()) && BigDecimal.ZERO.compareTo(queryVO.getMaxPrice()) < 0) {
            queryBuilder.must(QueryBuilders.rangeQuery("salesPrice").lte(queryVO.getMaxPrice()));
        }
        return queryBuilder;
    }

    /**
     * 封装排序规则
     * @param queryVO 参数
     * @return 结果
     */
    private FieldSortBuilder getSortBuilder(ProductQueryVO queryVO) {
        SortTypeEnum typeEnum = SortTypeEnum.parse(queryVO.getSortType());
        FieldSortBuilder sortBuilder;
        switch (typeEnum) {
            case SoldDown:
                sortBuilder = SortBuilders.fieldSort("salesCount").order(SortOrder.DESC);
                break;
            case PriceDown:
                sortBuilder = SortBuilders.fieldSort("salesPrice").order(SortOrder.DESC);
                break;
            case PriceUp:
                sortBuilder = SortBuilders.fieldSort("salesPrice").order(SortOrder.ASC);
                break;
            default:
                sortBuilder = SortBuilders.fieldSort("upperDate").order(SortOrder.DESC);
                break;
        }
        return sortBuilder;
    }

    /**
     * 读取es数据
     * @param queryBuilder 查询参数
     * @param sortBuilder 排序
     * @param queryVO 参数
     * @return 列表
     */
    private PageData<ESProductSkuModelDO> getEsProductList(QueryBuilder queryBuilder, FieldSortBuilder sortBuilder, ProductQueryVO queryVO) {
        PageRequest pageRequest = PageRequest.of(queryVO.getCurrent() - 1, queryVO.getSize());
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageRequest)
                .withSorts(sortBuilder)
                .withTrackTotalHits(true)
                ;
        // 是否需要高亮，无效
        if(queryVO.getHighLight()){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuName");
            highlightBuilder.preTags("<span class='red'>").postTags("</span>");
            nativeSearchQueryBuilder
                    .withHighlightBuilder(highlightBuilder);
        }
        SearchHits<ESProductSkuModelDO> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), ESProductSkuModelDO.class);
        return new PageData<>(searchHits.getTotalHits(), searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList()));
    }
}
