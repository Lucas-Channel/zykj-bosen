package com.bosen.admin.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MenuDetailVO implements Serializable {
    private static final long serialVersionUID = 289039746307803817L;
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId = 0L;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

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
     * 前端名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 前端隐藏
     */
    private Integer hidden;

    /**
     * 类型：0：目录， 1：菜单
     */
    private Integer type;

    /**
     * 路由地址，如果类型是菜单，该值必填
     */
    private String routeUrl;

}