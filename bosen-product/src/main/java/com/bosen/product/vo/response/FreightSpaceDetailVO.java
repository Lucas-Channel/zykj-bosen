package com.bosen.product.vo.response;

import com.bosen.common.constant.common.YesOrNoConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Data
public class FreightSpaceDetailVO implements Serializable {
    private static final long serialVersionUID = -2981481256785053767L;

    private String id;

    /**
     * 仓位名称
     */
    private String name;

    /**
     * 仓位编码
     */
    private String code;

    /**
     * 是否正常使用
     * @see YesOrNoConstant
     */
    private Integer status;

    /**
     * 说明
     */
    private String remark;
}
