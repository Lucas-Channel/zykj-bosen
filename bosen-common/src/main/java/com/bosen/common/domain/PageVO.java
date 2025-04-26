package com.bosen.common.domain;

import lombok.Data;

/**
 * 分页参数
 * @Author: Lucas
 */
@Data
public class PageVO {
    private Integer current = 1;

    private Integer size = 20;
}
