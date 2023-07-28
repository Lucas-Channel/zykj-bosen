package com.bosen.camunda.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 新增/修改camunda用户
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/28
 */
@Data
public class CamundaUserUpsertVO implements Serializable {
    private static final long serialVersionUID = -5352937980798822013L;

    /**
     * 中文名称
     */
    @NotEmpty(message = "用户名称不能为空")
    private String userId;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 姓
     */
    @NotEmpty(message = "姓氏不能为空")
    private String firstName;

    /**
     * 名称
     */
    @NotEmpty(message = "name不能为空")
    private String lastName;
}
