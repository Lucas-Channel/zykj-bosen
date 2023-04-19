package com.bosen.product.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Data
public class FreightSpaceUpdateStatusVO implements Serializable {
    private static final long serialVersionUID = 2355036442044337282L;

    @NotBlank(message = "id不能为空")
    private String id;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
}
