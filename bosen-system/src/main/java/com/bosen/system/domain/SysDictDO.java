package com.bosen.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字典管理(BsSysDict)表实体类
 *
 * @author Lucas
 * @since 2023-05-25 16:34:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sys_dict")
public class SysDictDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -7750885388764781394L;
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 描述
     */
    private String remark;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;

}

