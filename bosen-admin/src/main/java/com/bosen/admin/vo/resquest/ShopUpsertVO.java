package com.bosen.admin.vo.resquest;

import com.bosen.admin.constant.ShopEnvironmentEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class ShopUpsertVO implements Serializable {
    private static final long serialVersionUID = 587356629775366011L;

    private String id;

    @NotEmpty(message = "商城名称不能为空")
    private String name;

    /**
     * 商城环境:1.web 2.H5 3.小程序 4.APP
     * @see ShopEnvironmentEnum
     */
    @NotNull(message = "商城环境不能为空")
    private Integer shopEnvironment;

    /**
     * 商城logo
     */
    private String logoUrl;

    private String describe;

    private Integer status;
}
