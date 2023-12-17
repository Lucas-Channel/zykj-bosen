package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_role_menu_relation")
public class SystemRoleMenuRelation extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 5967542206426586443L;

    private String roleId;

    private String menuId;

}