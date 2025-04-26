package com.bosen.common.domain.api;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2025/4/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bs_oauth_jwt_keys")
public class JwtKeysDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -5709134437852928017L;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 加密方式
     */
    private String algorithm;
}
