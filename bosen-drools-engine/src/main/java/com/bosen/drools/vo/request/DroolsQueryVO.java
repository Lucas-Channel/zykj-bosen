package com.bosen.drools.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DroolsQueryVO extends PageVO implements Serializable {

    private static final long serialVersionUID = 5527070666319121091L;

    private String droolsCode;

    private String droolsName;
}
