package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("bs_sys_menu_permission")
public class SystemMenuPermissionDO implements Serializable {
    private static final long serialVersionUID = 609109419510902349L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单id
     */
    private Long menuId;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 接口地址
     */
    private String interfaceUrl;

}