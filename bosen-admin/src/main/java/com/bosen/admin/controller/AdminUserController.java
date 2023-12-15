package com.bosen.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.admin.domain.BsUserAccount;
import com.bosen.admin.service.IBsUserAccountService;
import com.bosen.admin.vo.resquest.RegisterAdminUserVO;
import com.bosen.admin.vo.resquest.UpdateAdminPasswordParamVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.common.domain.UserDto;
import com.bosen.common.service.IBsUserAccountCacheService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 后台用户管理
 * @author gaofeicm，hhl
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Resource
    private IBsUserAccountService adminService;

    @Resource
    private IBsUserAccountCacheService cacheService;
    /**
     * 注册用户
     * @param registerAdminUserVO 注册信息
     * @return 结果
     */
    @PostMapping(value = "/register")
    public ResponseData<Void> register(@RequestBody @Valid RegisterAdminUserVO registerAdminUserVO) {
        return adminService.register(registerAdminUserVO);
    }

    /**
     * 获取详情
     * @param userId 用户id
     * @return 结果
     */
    @GetMapping(value = "/info")
    public ResponseData<BsUserAccount> getAdminInfo(@RequestParam String userId) {
        BsUserAccount one = adminService.lambdaQuery().eq(BsUserAccount::getId, userId).one();
        return ResponseData.success(one);
    }

    /**
     * 分页获取用户列表
     * @param pageVO 分页参数
     * @return 结果
     */
    @GetMapping(value = "/listPage")
    public ResponseData<PageData<BsUserAccount>> listPage(PageVO pageVO) {
        Page<BsUserAccount> list = adminService.page(new Page<>(pageVO.getCurrent(), pageVO.getSize()), new LambdaQueryWrapper<BsUserAccount>().orderByDesc(BsUserAccount::getCreateTime));
        return ResponseData.success(new PageData<>(list.getTotal(), list.getRecords()));
    }

    /**
     * 修改指定用户信息
     * @param admin 参数
     * @return 结果
     */
    @PostMapping(value = "/update")
    public ResponseData<Void> update(@RequestBody BsUserAccount admin) {
        cacheService.delAdmin(admin.getId());
        return ResponseData.judge(adminService.updateById(admin));
    }

    /**
     * 修改密码
     * @param updatePasswordParam 参数
     * @return 结果
     */
    @PostMapping(value = "/updatePassword")
    public ResponseData<Void> updatePassword(@RequestBody @Valid UpdateAdminPasswordParamVO updatePasswordParam) {
        return adminService.updatePassword(updatePasswordParam);
    }

    /**
     * 删除用户
     * @param id id
     * @return 结果
     */
    @PostMapping(value = "/delete/{id}")
    public ResponseData<Void> delete(@PathVariable Long id) {
        return ResponseData.judge(adminService.removeById(id));
    }

    /**
     * 修改用户状态
     * @param id id
     * @param status 状态
     * @return 结果
     */
    @PostMapping(value = "/updateStatus/{id}")
    public ResponseData<Void> updateStatus(@PathVariable String id,@RequestParam(value = "status") Integer status) {
        BsUserAccount umsAdmin = new BsUserAccount();
        umsAdmin.setStatus(status);
        umsAdmin.setId(id);
        cacheService.delAdmin(id);
        return ResponseData.judge(adminService.updateById(umsAdmin));
    }

    /**
     * 通过用户名查询登录账号信息主要用于b端用户登录
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/loadByUsername")
    public UserDto loadUserByUsername(@RequestParam String username) {
        return adminService.loadUserByUsername(username);
    }

    /**
     * 缓存用户信息
     * @param adminId 平台后台用户id
     */
    @PostMapping("/cacheAdminInfo")
    public void cacheAdminInfo(@RequestBody String adminId) {
        adminService.getCurrentAdminUser(adminId);
    }
}
