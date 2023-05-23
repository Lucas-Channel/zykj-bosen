package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 后台-商家等级
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_admin_merchant_level")
public class AdminMerchantLevelDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -6765130903788737480L;

    private String name;

}
