package com.bosen.camunda.api.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProcessDefinitionQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = -3957907955505685844L;

    /**
     * 流程key
     */
    private String processKey;

    /**
     * 流程name
     */
    private String processName;
}
