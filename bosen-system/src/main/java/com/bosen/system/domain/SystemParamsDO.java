package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 平台参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/7
 */
@Data
@TableName(value = "bs_sys_params")
public class SystemParamsDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 3198935440287629748L;

    /**
     * 参数编码
     */
    private String code;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 值
     */
    private String val;

    /**
     * 是否启用
     * @see YesOrNoConstant
     */
    private Integer status;
}
