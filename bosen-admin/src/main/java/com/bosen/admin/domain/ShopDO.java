package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.admin.constant.ShopEnvironmentEnum;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;


@Data
@TableName("bs_shop")
@EqualsAndHashCode(callSuper = true)
public class ShopDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -8353228506198499006L;

    private String name;

    /**
     * 商城环境:1.web 2.H5 3.小程序 4.APP
     * @see ShopEnvironmentEnum
     */
    private Integer shopEnvironment;

    /**
     * 商城logo
     */
    private String logoUrl;

    private String remark;

    private Integer status;

    /**
     * 装修内容
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> adornContent;
}

