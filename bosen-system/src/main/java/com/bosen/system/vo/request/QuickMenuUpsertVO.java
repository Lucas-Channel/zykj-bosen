package com.bosen.system.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增修改快捷菜单VO
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/13
 */
@Data
public class QuickMenuUpsertVO implements Serializable {
    private static final long serialVersionUID = -5185659147867082597L;
    private String id;
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 图标颜色
     */
    private String iconColor;

    /**
     * 前端路由地址
     */
    private String frontRouterUrl;
}
