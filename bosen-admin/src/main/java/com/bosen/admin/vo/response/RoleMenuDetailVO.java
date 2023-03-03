package com.bosen.admin.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleMenuDetailVO implements Serializable {
    private static final long serialVersionUID = 289039746307803817L;
    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单级数
     */
    private Integer level;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否分配当前角色
     */
    private Boolean hasMenuPower = false;

    /**
     * 子集
     */
    private List<RoleMenuDetailVO> children;
}