package com.bosen.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字典管理明细(BsSysDictItem)表实体类
 *
 * @author Lucas
 * @since 2023-05-25 16:34:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sys_dict_item")
public class SysDictItemDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -1522190538448034085L;

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

