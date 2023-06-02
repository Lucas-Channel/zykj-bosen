package com.bosen.gateway.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 白名单(BsGatewayWhiteUrls)表实体类
 *
 * @author Lucas
 * @since 2023-06-02 16:25:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_gateway_white_urls")
public class GatewayWhiteUrlsDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -6676590860382051009L;

    //访问地址
    private String visitUrl;
    //描述
    private String remark;

}

