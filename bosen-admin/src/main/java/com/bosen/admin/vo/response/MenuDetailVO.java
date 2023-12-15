package com.bosen.admin.vo.response;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MenuDetailVO implements Serializable {
    private static final long serialVersionUID = 289039746307803817L;

    private String id;

    /**
     * 编码名称
     */
    @NotEmpty(message = "菜单编码不能为空")
    private String name;

    /**
     * 菜单名称
     */
    @NotEmpty(message = "菜单名称不能为空")
    private String title;

    /**
     * 菜单图标
     */
    @NotEmpty(message = "菜单图标不能为空")
    private String icon;

    /**
     * 目录basic，显示页self
     */
    @NotEmpty(message = "菜单类型不能为空")
    private String component;

    /**
     * 菜单路由
     */
    @NotEmpty(message = "路由地址不能为空")
    private String path;

    /**
     * 父类菜单id
     */
    private String parentId;

    /**
     * 序号
     */
    @NotNull(message = "序号不能为空")
    private Integer sortNumber;

    /**
     * 所属平台：1、平台后台，2、商家后台
     */
    @NotNull(message = "所属平台不能为空")
    private Integer belongPlatform;

}