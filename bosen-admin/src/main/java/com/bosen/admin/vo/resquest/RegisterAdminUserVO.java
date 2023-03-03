package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * 用户注册参数
 */
@Data
public class RegisterAdminUserVO {
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private String icon;

    @Email
    private String email;

    private String nickName;

    private String note;

    private String mobile;

    private LocalDateTime birthDate;
}
