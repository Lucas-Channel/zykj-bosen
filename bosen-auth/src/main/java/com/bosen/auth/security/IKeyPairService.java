package com.bosen.auth.security;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.domain.api.JwtKeysDO;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2025/4/26
 */
public interface IKeyPairService extends IService<JwtKeysDO> {

    KeyPair getCurrentKeyPair();

}
