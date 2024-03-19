package com.bosen.admin.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取前端菜单转换类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/12/17
 */
@Data
public class ViewFrontMenuDetailBO implements Serializable {
    private static final long serialVersionUID = -7745471044522437417L;

    private String id;


    /**
     * 编码名称
     */
    private String name;

    /**
     * 菜单名称
     */
    private String title;

    private String i18nTitle;

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

    private Boolean keepAlive;

    private String singleLayout;
}
