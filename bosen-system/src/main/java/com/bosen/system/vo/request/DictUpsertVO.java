package com.bosen.system.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
@Data
public class DictUpsertVO implements Serializable {
    private static final long serialVersionUID = -1893052309335521463L;
    private String id;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    /**
     * 字典编码
     */
    @NotBlank(message = "字典编码不能为空")
    private String dictCode;

    /**
     * 描述
     */
    private String remark;
}
