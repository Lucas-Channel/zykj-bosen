package com.bosen.member.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 会员注册对象
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@Data
public class MemberRegisterVO implements Serializable {
    private static final long serialVersionUID = -7647212786084597544L;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "手机号码不能为空")
    private String phone;

    private String password;

    /**
     * 是否默认密码
     */
    private Boolean defaultPassword;
}
