package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@Data
public class MenuPermissionUpsertVO implements Serializable {

    private static final long serialVersionUID = -122335714919139102L;

    private String id;

    /**
     * 菜单id
     */
    @NotNull(message = "菜单id不能为空")
    private String menuId;

    /**
     * 名称
     */
    @NotNull(message = "权限名称不能为空")
    private String name;

    /**
     * 权限值，按钮的编码：sys:user:view
     */
    private String value;

    /**
     * 图标
     */
    private String icon;

    /**
     * 启用状态；0->禁用；1->启用
     */
    private Integer status = 1;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 接口地址
     */
    private String interfaceUrl;
}
