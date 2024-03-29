package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.SexConstant;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台管理-用户-后台管理员
 * 考虑一下是否需要性别和生日字段，平台后台正常而言不需要，商家后台好像也不需要
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName(value = "bs_admin_user_b")
public class AdminUserDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 496498778273679222L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 别名
     */
    private String nickName;

    /**
     * 状态
     * @see UserStatusConstant
     */
    private Integer status;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 最后登录日期
     */
    private LocalDateTime loginTime;

    /**
     * 性别
     * @see SexConstant
     */
    private Integer sex;

    /**
     * 生日
     */
    private LocalDateTime birthDate;
}
