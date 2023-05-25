package com.bosen.system.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictItemQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 7133306338456733166L;
    private String dictId;
}
