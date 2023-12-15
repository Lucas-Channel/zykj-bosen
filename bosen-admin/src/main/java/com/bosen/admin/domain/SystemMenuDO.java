package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bs_sys_menu")
public class SystemMenuDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -4697090695126725122L;

    /**
     * 编码名称
     */
    private String name;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 目录basic，显示页self
     */
    private String component;

    /**
     * 菜单路由
     */
    private String path;

    /**
     * 父类菜单id
     */
    private String parentId;

    /**
     * 序号
     */
    private Integer sortNumber;

    /**
     * 菜单级数
     */
    private Integer levelNumber;

    /**
     * 所属平台：1、平台后台，2、商家后台
     */
    private Integer belongPlatform;
}
