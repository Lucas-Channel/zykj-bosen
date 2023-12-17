package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sys_menu_permission")
public class SystemMenuPermissionDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 609109419510902349L;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 名称
     */
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
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 接口地址
     */
    private String interfaceUrl;

}