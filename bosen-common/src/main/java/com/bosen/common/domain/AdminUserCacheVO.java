package com.bosen.common.domain;

import com.bosen.common.constant.common.SexConstant;
import com.bosen.common.constant.common.UserStatusConstant;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台用户缓存对象
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@Data
public class AdminUserCacheVO implements Serializable {

    private static final long serialVersionUID = 651500098581133656L;

    private Long id;
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
