package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SkuInventoryQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = -8806544162895165226L;

    private String skuName;

    /**
     * 入库批次
     */
    private String lotNumber;
}
