package com.bosen.admin.vo.resquest;

import com.bosen.common.domain.PageVO;
import lombok.Data;

/**
 * 查询条件
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class StoreQueryVO extends PageVO {
    /**
     * 店铺名称
     */
    private String name;

    /**
     * 店铺状态
     */
    private Integer status;
}
