package com.bosen.search.vo.request;

import com.bosen.common.domain.PageVO;
import com.bosen.search.constant.SortTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * es 查询商品数据参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 1253712645198168718L;

    /**
     * 搜索词：店铺，skuName
     */
    private String keyWord;

    /**
     * spuIds
     */
    private List<String> spuIds;

    /**
     * 商城ids
     */
    private List<String> shopIds;

    /**
     * 店铺ids
     */
    private List<String> storeIds;

    /**
     * skuId
     */
    private List<String> skuIds;

    /**
     * 品牌id
     */
    private List<String> brandIds;

    /**
     * 平台品类id
     */
    private List<String> categoryIds;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 商家品类id
     */
    private List<String> merchantCategoryIds;

    /**
     * 最小价格
     */
    private BigDecimal minPrice;

    /**
     * 最小价格
     */
    private BigDecimal maxPrice;

    /**
     * 排序方式
     * @see SortTypeEnum
     */
    private Integer sortType = SortTypeEnum.SoldDown.getCode();

    /**
     * 是否高亮显示
     */
    private Boolean highLight = false;
}

