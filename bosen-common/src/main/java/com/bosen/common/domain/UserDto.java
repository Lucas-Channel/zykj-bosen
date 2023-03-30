package com.bosen.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录用户信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDto {

    private String id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 账户状态
     */
    private Integer status;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 登录默认选中角色id
     */
    private Long defaultRoleId;
    /**
     * 角色列表
     */
    private List<AssignRoleVO> roles;

    private Long exp;

}
