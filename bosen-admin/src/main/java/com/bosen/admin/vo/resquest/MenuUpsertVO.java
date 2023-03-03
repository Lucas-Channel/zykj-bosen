package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MenuUpsertVO implements Serializable {


    private static final long serialVersionUID = -391194121774323350L;

    private Long id;

    /**
     * 父级ID
     */
    private Long parentId = 0L;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
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
     * 前端图标
     */
    private String icon;

    /**
     * 前端隐藏
     */
    private Integer hidden;

    /**
     * 类型：0：目录， 1：菜单
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 路由地址，如果类型是菜单，该值必填
     */
    private String routeUrl;

}