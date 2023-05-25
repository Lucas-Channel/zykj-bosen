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
public class DictItemUpsertVO implements Serializable {
    private static final long serialVersionUID = -3662316467694595045L;

    private String id;

    /**
     * 字典id
     */
    @NotBlank(message = "字典id不能为空")
    private String dictId;

    /**
     * 字典项文本
     */
    @NotBlank(message = "字典文本不能为空")
    private String itemText;

    /**
     * 字典项值
     */
    @NotBlank(message = "字典值不能为空")
    private String itemValue;

    /**
     * 描述
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}
