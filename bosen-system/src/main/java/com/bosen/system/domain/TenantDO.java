package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/27
 */
@Data
@TableName(value = "bs_tenant")
public class TenantDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -9109076658446143138L;

    /**
     * 租户编码
     */
    private String tenantCode;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 描述
     */
    private String desc;

    /**
     * 状态
     */
    private Integer status;
}
