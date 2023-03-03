package com.bosen.admin.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@Data
public class SystemMenuPermissionDetail implements Serializable {
    private static final long serialVersionUID = 7461544432650867997L;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
