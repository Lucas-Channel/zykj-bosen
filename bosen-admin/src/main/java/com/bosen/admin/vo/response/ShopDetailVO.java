package com.bosen.admin.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class ShopDetailVO implements Serializable {
    private static final long serialVersionUID = 8663361785439263887L;

    private String id;

    private String name;

    /**
     * 商城环境:1.web 2.H5 3.小程序 4.APP
     */
    private Integer shopEnvironment;

    /**
     * 商城logo
     */
    private String logoUrl;

    private String remark;

    private Integer status;
}
