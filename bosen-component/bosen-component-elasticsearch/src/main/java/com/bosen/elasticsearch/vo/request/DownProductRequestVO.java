package com.bosen.elasticsearch.vo.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品下架参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/11
 */
@Data
public class DownProductRequestVO implements Serializable {
    private static final long serialVersionUID = -6610134953971489682L;

    private List<String> spuIds;

    private List<String> shopIds;

    private List<String> storeIds;
}
