package com.bosen.template.adorn.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 5765961994253888462L;

    private String templateName;
}
