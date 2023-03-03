package com.bosen.admin.vo.resquest;

import com.bosen.common.domain.PageVO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@Data
public class MenuPermissionQueryVO extends PageVO {

    /**
     * 权限名称
     */
    private String name;

    /**
     * 菜单id
     */
    @NotNull(message = "菜单id不能为空")
    private Long menuId;
}
