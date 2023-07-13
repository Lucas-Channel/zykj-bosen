package com.bosen.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户快捷操作菜单表(BsUserQuickMenu)表实体类
 *
 * @author Lucas
 * @since 2023-07-13 14:16:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_user_quick_menu")
public class UserQuickMenuDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -3248925885731457928L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

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

