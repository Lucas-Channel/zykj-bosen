package com.bosen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.admin.bo.ViewFrontMenuDetailBO;
import com.bosen.admin.domain.SystemMenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/12/12
 */
public interface SystemMenuMapper extends BaseMapper<SystemMenuDO> {
    List<ViewFrontMenuDetailBO> listMenuByMenuIds(@Param("ids") List<String> ids, @Param("languageCode") String languageCode);
}
