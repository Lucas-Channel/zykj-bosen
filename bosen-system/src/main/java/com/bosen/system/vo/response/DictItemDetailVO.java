package com.bosen.system.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典详情
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
@Data
public class DictItemDetailVO implements Serializable {
    private static final long serialVersionUID = -1251398627660030505L;
    private String id;

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 描述
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}
