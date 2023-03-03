package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 修改用户名密码参数
 */
@Data
public class UpdateAdminPasswordParamVO {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;

    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
}
