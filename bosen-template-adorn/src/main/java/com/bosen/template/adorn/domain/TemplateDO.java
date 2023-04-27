package com.bosen.template.adorn.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.template.adorn.constant.TemplateTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 装修模板(Template)表实体类
 *
 * @author Lucas
 * @since 2023-04-24 18:23:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bs_template")
public class TemplateDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 8597145899590048331L;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 适用商城：商城id
     */
    private String shopId;

    /**
     * 说明
     */
    private String remark;

    /**
     * logo地址
     */
    private String logoUrl;

    /**
     * 是否启用
     */
    private Integer status;

    /**
     * 模板类型:0.平台模板 1.企业商城模板 2.店铺模板 3.活动模板
     * @see TemplateTypeEnum
     */
    private Integer templateType;

    /**
     * 前端模板编码
     */
    private String frontTemplateCode;

    /**
     * 前端模板名称
     */
    private String frontTemplateName;

}