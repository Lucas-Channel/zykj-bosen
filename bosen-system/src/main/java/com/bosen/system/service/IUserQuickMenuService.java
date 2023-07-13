package com.bosen.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.UserQuickMenuDO;
import com.bosen.system.vo.request.QuickMenuUpsertVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户快捷操作菜单表(BsUserQuickMenu)表服务接口
 *
 * @author Lucas
 * @since 2023-07-13 14:16:41
 */
public interface IUserQuickMenuService extends IService<UserQuickMenuDO> {


    /**
     * 查询当前登入用户的所有快捷菜单数据
     * @return 所有数据
     */
    ResponseData<List<UserQuickMenuDO>> listByLoginUser();

    /**
     * 新增/修改数据
     *
     * @param quickMenuUpsertVO 实体对象
     * @return 新增结果
     */
    ResponseData<Void> upsert(@RequestBody QuickMenuUpsertVO quickMenuUpsertVO);
}

