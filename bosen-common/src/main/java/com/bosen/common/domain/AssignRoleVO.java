package com.bosen.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/23
 */
@Data
public class AssignRoleVO implements Serializable {
    private static final long serialVersionUID = 7762637938249134127L;

    private String roleId;

    private String roleCode;

    public AssignRoleVO(String roleId, String roleCode) {
        this.roleId = roleId;
        this.roleCode = roleCode;
    }

    public AssignRoleVO() {
    }
}
