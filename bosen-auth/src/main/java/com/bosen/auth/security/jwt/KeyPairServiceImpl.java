package com.bosen.auth.security.jwt;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.auth.mapper.JwtKeyPairMapper;
import com.bosen.auth.security.IKeyPairService;
import com.bosen.common.domain.api.JwtKeysDO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2025/4/26
 */
@Service
public class KeyPairServiceImpl  extends ServiceImpl<JwtKeyPairMapper, JwtKeysDO> implements IKeyPairService {

    @Override
    public KeyPair getCurrentKeyPair() {
        List<JwtKeysDO> list = this.list();
        return !CollectionUtils.isEmpty(list) ? convertToKeyPair(list.get(0)) : generateAndSaveKeyPair();
    }


    private KeyPair generateAndSaveKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            JwtKeysDO jwtKey = new JwtKeysDO();
            jwtKey.setPrivateKey(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
            jwtKey.setPublicKey(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
            jwtKey.setAlgorithm("RSA");
            jwtKey.setCreateTime(LocalDateTime.now());
            this.save(jwtKey);
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate key pair", e);
        }
    }

    private KeyPair convertToKeyPair(JwtKeysDO jwtKey) {
        try {
            byte[] privateBytes = Base64.getDecoder().decode(jwtKey.getPrivateKey());
            byte[] publicBytes = Base64.getDecoder().decode(jwtKey.getPublicKey());

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));

            return new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert keys", e);
        }
    }
}
