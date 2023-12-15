package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 菜单权限多语言列表数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bs_sys_menu_permission_language")
public class SystemMenuPermissionMultiLanguageDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -4697090695126725122L;

    /**
     * 编码名称
     */
    private String permissionId;

    /**
     * 描述
     */
    private String description;

    /**
     * 语言
     */
    private String languageCode;

}
