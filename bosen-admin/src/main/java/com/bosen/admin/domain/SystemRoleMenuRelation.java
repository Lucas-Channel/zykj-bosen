package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("bs_role_menu_relation")
public class SystemRoleMenuRelation implements Serializable {
    private static final long serialVersionUID = 5967542206426586443L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long menuId;

}