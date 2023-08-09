package com.bosen.drools.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.drools.constant.DroolsTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 规则详情表(BsDroolsInfo)表实体类
 *
 * @author Lucas
 * @since 2023-05-12 16:08:45
 */
@Data
@TableName("bs_drools_script")
@EqualsAndHashCode(callSuper = true)
public class DroolsScriptDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 5401741591009633180L;

    /**
     * 规则编码
     */
    private String droolsCode;

    /**
     * 规则名称
     */
    private String droolsName;

    /**
     * 规则代码
     */
    private String droolsScript;

    /**
     * 描述
     */
    private String droolsDesc;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;

    /**
     * 规则类型：1、优惠券规则，2、营销活动规则
     * @see DroolsTypeEnum
     */
    private Integer droolsType;

    /**
     * 数据源id
     */
    private String dataSourceId;
}

