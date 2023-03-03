package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RoleUpsertVO implements Serializable {

    private static final long serialVersionUID = -3222162554869660653L;
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

}