package com.bosen.system.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典详情
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
@Data
public class DictDetailVO implements Serializable {
    private static final long serialVersionUID = -6717251050643791222L;
    private String id;
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 描述
     */
    private String remark;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}
