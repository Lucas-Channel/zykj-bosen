package com.bosen.system.controller;


import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.UserQuickMenuDO;
import com.bosen.system.service.IUserQuickMenuService;
import com.bosen.system.vo.request.QuickMenuUpsertVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户快捷操作菜单表(BsUserQuickMenu)表控制层
 *
 * @author Lucas
 * @since 2023-07-13 14:16:35
 */
@RestController
@RequestMapping("bsUserQuickMenu")
public class UserQuickMenuController {
    /**
     * 服务对象
     */
    @Resource
    private IUserQuickMenuService bsUserQuickMenuService;

    /**
     * 查询当前登入用户的所有快捷菜单数据
     * @return 所有数据
     */
    @GetMapping("/list")
    public ResponseData<List<UserQuickMenuDO>> listByLoginUser() {
        return bsUserQuickMenuService.listByLoginUser();
    }

    /**
     * 新增/修改数据
     *
     * @param quickMenuUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("upsert")
    public ResponseData<Void> upsert(@RequestBody QuickMenuUpsertVO quickMenuUpsertVO) {
        return bsUserQuickMenuService.upsert(quickMenuUpsertVO);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestParam("idList") List<String> idList) {
        return ResponseData.judge(this.bsUserQuickMenuService.removeByIds(idList));
    }
}

