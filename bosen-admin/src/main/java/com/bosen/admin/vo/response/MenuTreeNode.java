package com.bosen.admin.vo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTreeNode extends MenuDetailVO implements Serializable {

    private static final long serialVersionUID = 8236852418044656447L;

    /**
     * 子菜单
     */
    private List<MenuTreeNode> children;
}
