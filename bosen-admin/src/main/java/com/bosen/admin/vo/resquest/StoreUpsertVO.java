package com.bosen.admin.vo.resquest;

import com.bosen.admin.constant.StoreStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 开店申请
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class StoreUpsertVO implements Serializable {
    private static final long serialVersionUID = -7422649724701006057L;

    private String id;

    /**
     * 商家id
     */
    @NotEmpty(message = "商家id不能为空")
    private String merchantId;

    /**
     * 商家角色id
     */
    @NotEmpty(message = "商家角色id不能为空")
    private String merchantRoleId;

    /**
     * 店铺名称
     */
    @NotEmpty(message = "店铺名称不能为空")
    private String name;

    /**
     * 店铺logo
     */
    private String logoUrl;

    /**
     * 描述
     */
    private String remark;

    /**
     * 状态
     * @see StoreStatusEnum
     */
    private Integer status;

    /**
     * 信誉分
     */
    private BigDecimal creditScore;
}
