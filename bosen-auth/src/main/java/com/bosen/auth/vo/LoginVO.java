package com.bosen.auth.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/15
 */
@Data
public class LoginVO implements Serializable {
    private static final long serialVersionUID = -3483997481573409274L;
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String refresh_token;
    private String username;
    private String password;
}
