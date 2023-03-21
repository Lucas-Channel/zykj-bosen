package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 标签管理-用于商品推送
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/21
 */
@Data
@TableName("bs_label")
public class LabelDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 2397355745865329791L;

    /**
     * 标签编码
     */
    private String labelCode;

    /**
     * 标签值
     */
    private String labelName;

    /**
     * 状态
     */
    private Integer status;

    private String remark;
}
