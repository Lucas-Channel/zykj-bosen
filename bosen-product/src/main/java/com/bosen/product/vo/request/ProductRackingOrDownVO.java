package com.bosen.product.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 商品上/下架请求参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Data
public class ProductRackingOrDownVO implements Serializable {
    private static final long serialVersionUID = -3362076186496528559L;

    /**
     * 是否上架，true，上架，false下架
     */
    @NotNull
    private Boolean rackingFlag;

    @NotEmpty
    private List<String> ids;
}
