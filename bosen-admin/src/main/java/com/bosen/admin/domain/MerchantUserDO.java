package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 平台入驻商家信息
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/19
 */
@Data
@TableName("bs_merchant_user")
public class MerchantUserDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -6493729386040690106L;

    private String mobile;

    private String name;

    private String password;

    private String icon;

    /**
     * 状态
     * @see UserStatusConstant
     */
    private Integer status;

    /**
     * 最后登录日期
     */
    private LocalDateTime loginTime;

    /**
     * 当前登陆状态
     */
    private Integer loginFlag;
}
