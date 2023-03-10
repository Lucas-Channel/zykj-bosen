package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 公告
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/7
 */
@Data
@TableName(value = "bs_notice")
public class NoticeDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 7361874964226051198L;

    /**
     * 标题
     */
    private String title;

    /**
     * 公告类型
     */
    private Integer type;

    /**
     * 是否置顶 0-否 1-是
     */
    private Integer top;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态 1-待上架 2-已上架 3-已下架
     */
    private Integer status = 1;
}
