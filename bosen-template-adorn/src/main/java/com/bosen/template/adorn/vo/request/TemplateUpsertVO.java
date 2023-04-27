package com.bosen.template.adorn.vo.request;


import com.bosen.template.adorn.constant.TemplateTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 装修模板新增/修改请求实体
 * @author Lucas
 * @since 2023-04-25 18:23:17
 */
@Data
public class TemplateUpsertVO implements Serializable {
    private static final long serialVersionUID = -3974027044919034427L;

    /**
     * 主键
     */
    private String id;

    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空")
    private String templateName;

    /**
     * 商城id
     */
    @NotBlank(message = "商城id不能为空")
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
    @NotNull(message = "模板类型不能为空")
    private Integer templateType;

    /**
     * 前端模板编码
     */
    @NotBlank(message = "前端模板编码不能为空")
    private String frontTemplateCode;

    /**
     * 前端模板名称
     */
    @NotBlank(message = "前端模板名称不能为空")
    private String frontTemplateName;
}