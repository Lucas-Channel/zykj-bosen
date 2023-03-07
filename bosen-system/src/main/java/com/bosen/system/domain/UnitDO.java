package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 单位
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/7
 */
@Data
@TableName(value = "bs_unit")
public class UnitDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 7357452653267910139L;

    private String unitCode;

    private String unitName;

    /**
     * 是否开启使用
     * @see YesOrNoConstant
     */
    private Integer status;
}
