package com.bosen.pay.api.vo.request.wechat.h5;

import com.bosen.pay.api.constant.H5PaySceneTypeConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * h5支付，h5场景信息
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
public class H5InfoVO implements Serializable {
    private static final long serialVersionUID = -8561974476821234815L;

    /**
     * 场景类型：iOS, Android, Wap
     * @see H5PaySceneTypeConstant
     */
    @NotBlank(message = "场景类型不能为空")
    private String type;


}
