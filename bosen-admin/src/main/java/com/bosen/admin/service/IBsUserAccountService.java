package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.BsUserAccount;
import com.bosen.admin.vo.resquest.RegisterAdminUserVO;
import com.bosen.admin.vo.resquest.UpdateAdminPasswordParamVO;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.BsUserAccountCacheVO;
import com.bosen.common.domain.UserDto;

/**
 * 用户账户表(BsUserAccount)接口类
 *
 * @author gaofeicm
 * @since 2023-03-30 22:27:07
 */
public interface IBsUserAccountService extends IService<BsUserAccount> {

    /**
     * 获取用户信息,username,可以是用户名称或者手机号
     * @param username 用户名称或者手机号
     * @return 结果
     */
    UserDto loadUserByUsername(String username);

    /**
     * 注册i会员信息
     * @param registerAdminUserVO 参数
     * @return 结果
     */
    ResponseData<Void> register(RegisterAdminUserVO registerAdminUserVO);

    /**
     * 修改密码
     * @param updatePasswordParam
     * @return 修改结果
     */
    ResponseData<Void> updatePassword(UpdateAdminPasswordParamVO updatePasswordParam);

    /**
     * 获取当前登录后台会员信息
     * @param userId 用户id
     * @return 对象
     */
    BsUserAccountCacheVO getCurrentAdminUser(String userId);
}
