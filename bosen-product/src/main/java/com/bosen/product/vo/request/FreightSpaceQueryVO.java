package com.bosen.product.vo.request;

import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.domain.PageVO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Data
public class FreightSpaceQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 6340260605949558508L;

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

}
