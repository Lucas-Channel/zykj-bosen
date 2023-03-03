package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 更新角色菜单权限请求参数体
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@Data
public class RoleMenuUpsertVO implements Serializable {

    private static final long serialVersionUID = 4920370178044962801L;

    @NotEmpty(message = "菜单id不能为空")
    private List<Long> menuIds;

    @NotNull(message = "角色id不能为空")
    private Long roleId;
}
