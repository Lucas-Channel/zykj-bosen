package com.bosen.admin.vo.resquest;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuQueryVO extends PageVO {
    /**
     * 菜单名称
     */
    private String name;
}
