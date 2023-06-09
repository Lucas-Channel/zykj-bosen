package com.bosen.pay.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.KeyPair;

/**
 * 公私钥和序列号
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyPairAndSerialNo {
    private KeyPair keyPair;

    private String serialNumber;
}
