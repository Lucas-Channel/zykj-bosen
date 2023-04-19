package com.bosen.product.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Data
public class FreightSpaceUpsertVO implements Serializable {
    private static final long serialVersionUID = 3563212399722558732L;
    private String id;
    /**
     * 仓位名称
     */
    @NotBlank(message = "仓位名称不能为空")
    private String name;

    /**
     * 仓位编码
     */
    @NotBlank(message = "仓位编码不能为空")
    private String code;

    /**
     * 说明
     */
    private String remark;

    /**
     * 商品所属商家
     */
    private String merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 商品所属商家角色id
     */
    private String merchantRoleId;
}
