package com.bosen.admin.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RoleDetailVO implements Serializable {

    private static final long serialVersionUID = 4320341919988948344L;

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;
}