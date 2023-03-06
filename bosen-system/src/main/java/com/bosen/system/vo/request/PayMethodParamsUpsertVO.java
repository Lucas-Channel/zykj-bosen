package com.bosen.system.vo.request;

import com.bosen.system.constants.PayMethodParamCodeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 支付方式
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Data
public class PayMethodParamsUpsertVO implements Serializable {

    private static final long serialVersionUID = 2406876730596961806L;

    private Long id;

    @NotNull
    private Long payMethodId;

    /**
     * 参数编码
     * @see PayMethodParamCodeEnum
     */
    @NotBlank
    private String paramCode;

    /**
     * 参数值
     */
    @NotBlank
    private String paramVal;

    /**
     * 备注
     */
    @NotBlank
    private String paramRemark;

}
