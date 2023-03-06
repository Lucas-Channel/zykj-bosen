package com.bosen.system.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Data
public class PayMethodUpsertVO implements Serializable {
    private static final long serialVersionUID = -7248953174098970035L;

    private Long id;

    @NotBlank
    private String payMethodCode;

    @NotBlank
    private String payMethodName;

    @NotBlank
    private String iconUrl;

    @NotNull
    private Integer enableFlag;
}
