package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商城
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/13
 */
@Data
@TableName("bs_shop")
public class ShopDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -8353228506198499006L;

    private String name;

    /**
     * 商城环境:1.web 2.H5 3.小程序 4.APP
     */
    private Integer shopEnvironment;

    /**
     * 商城logo
     */
    private String logoUrl;

    private String describe;

    private Integer status;
}
