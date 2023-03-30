package com.bosen.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户账户表(BsUserAccount)缓存类
 *
 * @author gaofeicm
 * @since 2023-03-30 22:27:07
 */
@Data
public class BsUserAccountCacheVO implements Serializable {

    private static final long serialVersionUID = -91825670664020709L;

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 最后登出时间
     */
    private Date logoutTime;
}
