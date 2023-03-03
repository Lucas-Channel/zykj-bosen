package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@Data
public class RolePermissionUpsertVO implements Serializable {

    private static final long serialVersionUID = -122335714919139102L;

    private Long id;

    /**
     * 菜单id
     */
    @NotNull(message = "菜单id不能为空")
    private Long menuId;

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @NotEmpty(message = "权限id不能为空")
    private List<Long> permissionIds;
}
