package com.bosen.common.constant.response;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 分页数据
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/22
 */
@Data
public class PageData<T> {
    /**
     * 记录总条数
     */
    @NotEmpty
    private Long totalCount;
    /**
     * 数据体
     */
    private List<T> data;

    public PageData(Long totalCount, List<T> data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public PageData(){}
}
