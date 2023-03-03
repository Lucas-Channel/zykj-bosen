package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 币种
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@Data
@TableName(value = "bs_currency")
public class Currency implements Serializable {
    private static final long serialVersionUID = 7323169270105023267L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String currencyCode;

    private String currencyName;

    private String currencyIcon;

    private LocalDateTime createTime;
}
