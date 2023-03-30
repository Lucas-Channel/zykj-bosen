package com.bosen.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.admin.domain.MerchantDO;
import com.bosen.admin.service.IMerchantService;
import com.bosen.admin.vo.resquest.RegisterMerchantVO;
import com.bosen.admin.vo.resquest.UpdateAdminPasswordParamVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.PageVO;
import com.bosen.common.domain.UserDto;
import com.bosen.common.service.IMerchantCacheService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 商户后台-管理
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Resource
    private IMerchantService merchantService;

    @Resource
    private IMerchantCacheService cacheService;

    /**
     * 注册商户
     * @param registerMerchantVO 注册信息
     * @return 结果
     */
    @PostMapping(value = "/register")
    public ResponseData<Void> register(@RequestBody @Valid RegisterMerchantVO registerMerchantVO) {
        return merchantService.register(registerMerchantVO);
    }

    /**
     * 获取详情
     * @param merchantId 用户id
     * @return 结果
     */
    @GetMapping(value = "/info")
    public ResponseData<MerchantDO> getAdminInfo(@RequestParam Long merchantId) {
        MerchantDO one = merchantService.lambdaQuery().eq(MerchantDO::getId, merchantId).one();
        return ResponseData.success(one);
    }

    /**
     * 分页获取用户列表
     * @param pageVO 分页参数
     * @return 结果
     */
    @GetMapping(value = "/listPage")
    public ResponseData<PageData<MerchantDO>> listPage(PageVO pageVO) {
        Page<MerchantDO> list = merchantService.page(new Page<>(pageVO.getCurrent(), pageVO.getSize()), new LambdaQueryWrapper<MerchantDO>().orderByDesc(MerchantDO::getCreateTime));
        return ResponseData.success(new PageData(list.getTotal(), list.getRecords()));
    }

    /**
     * 修改指定用户信息
     * @param merchantDO 参数
     * @return 结果
     */
    @PostMapping(value = "/update")
    public ResponseData<Void> update(@RequestBody MerchantDO merchantDO) {
        //cacheService.delMerchant(merchantDO.getId());
        return ResponseData.judge(merchantService.updateById(merchantDO));
    }

    /**
     * 修改密码
     * @param updatePasswordParam 参数
     * @return 结果
     */
    @PostMapping(value = "/updatePassword")
    public ResponseData<Void> updatePassword(@RequestBody @Valid UpdateAdminPasswordParamVO updatePasswordParam) {
        return merchantService.updatePassword(updatePasswordParam);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping(value = "/delete/{id}")
    public ResponseData<Void> delete(@PathVariable Long id) {
        return ResponseData.judge(merchantService.removeById(id));
    }

    /**
     * 修改用户状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping(value = "/updateStatus/{id}")
    public ResponseData<Void> updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setStatus(status);
        //merchantDO.setId(id);
        cacheService.delMerchant(id);
        return ResponseData.judge(merchantService.updateById(merchantDO));
    }

    /**
     * 通过用户名查询登录账号信息主要用于b端用户登录
     * @param mobile 手机号
     * @return 结果
     */
    @RequestMapping(value = "/loginByMobile", method = RequestMethod.GET)
    public UserDto loadByMobile(@RequestParam String mobile) {
        return merchantService.loadUserByUsername(mobile);
    }

    /**
     * 缓存商户信息
     * @param merchantId 商户后台用户id
     */
    @PostMapping("/cacheMerchantInfo")
    public void cacheAdminInfo(@RequestBody Long merchantId) {
        merchantService.getCurrentMerchant(merchantId);
    }
}
