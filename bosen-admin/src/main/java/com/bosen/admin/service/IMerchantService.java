package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.MerchantDO;
import com.bosen.admin.vo.resquest.RegisterMerchantVO;
import com.bosen.admin.vo.resquest.UpdateAdminPasswordParamVO;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.MerchantCacheVO;
import com.bosen.common.domain.UserDto;

/**
 * 商户后台管理员Service
 */
public interface IMerchantService extends IService<MerchantDO> {

    /**
     * 获取用户信息,username,可以是用户名称或者手机号
     */
    UserDto loadUserByUsername(String username);

    /**
     * 注册i会员信息
     * @param registerMerchantVO 参数
     * @return 结果
     */
    ResponseData<Void> register(RegisterMerchantVO registerMerchantVO);

    /**
     * 修改密码
     * @param updatePasswordParam
     * @return
     */
    ResponseData<Void> updatePassword(UpdateAdminPasswordParamVO updatePasswordParam);

    /**
     * 获取当前登录后台会员信息
     * @return 对象
     */
    MerchantCacheVO getCurrentMerchant(Long merchantId);
}
