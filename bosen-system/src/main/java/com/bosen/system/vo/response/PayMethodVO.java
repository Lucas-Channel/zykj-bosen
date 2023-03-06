package com.bosen.system.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Data
public class PayMethodVO implements Serializable {
    private static final long serialVersionUID = -5757445565330238871L;

    private Long id;

    private String payMethodCode;

    private String payMethodName;

    private String iconUrl;

    private Integer enableFlag;
}
