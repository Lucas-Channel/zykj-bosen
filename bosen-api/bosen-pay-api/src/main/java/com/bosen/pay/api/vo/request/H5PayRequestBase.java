package com.bosen.pay.api.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * h5支付
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class H5PayRequestBase extends PayRequestBase implements Serializable {
    private static final long serialVersionUID = -4048265855954681127L;
    /**
     * 场景信息
     */
    @NotNull(message = "场景信息不能为空")
    private H5SceneInfo scene_info;
}

