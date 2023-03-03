package com.bosen.admin.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Data
public class RolesInterfaceUrlVO implements Serializable {
    private static final long serialVersionUID = 7027763243175164749L;

    private String roleName;

    private String interfaceUrl;
}
