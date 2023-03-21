package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 省市级地域信息表
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/21
 */
@Data
@TableName("bs_area")
public class AreaDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -3856242775790156612L;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父类id
     */
    private Long parentId;
}
