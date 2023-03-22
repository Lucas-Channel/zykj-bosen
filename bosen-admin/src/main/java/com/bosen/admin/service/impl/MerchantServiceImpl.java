package com.bosen.admin.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.MerchantDO;
import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.mapper.MerchantMapper;
import com.bosen.admin.service.IMerchantRoleRelationService;
import com.bosen.admin.service.IMerchantService;
import com.bosen.admin.vo.resquest.RegisterMerchantVO;
import com.bosen.admin.vo.resquest.UpdateAdminPasswordParamVO;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.MerchantCacheVO;
import com.bosen.common.domain.UserDto;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.IMerchantCacheService;
import com.bosen.common.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/23
 */
@Slf4j
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, MerchantDO> implements IMerchantService {

    @Resource
    private IMerchantRoleRelationService merchantRoleRelationService;

    @Resource
    private IMerchantCacheService cacheService;

    @Override
    public UserDto loadUserByUsername(String username) {
        MerchantDO merchantDO = this.lambdaQuery().eq(MerchantDO::getRegisterMobile, username).or().eq(MerchantDO::getMerchantName, username).one();
        if (Objects.nonNull(merchantDO)) {
            UserDto userDto = new UserDto();
            userDto.setUsername(merchantDO.getMerchantName());
            userDto.setStatus(merchantDO.getStatus());
            userDto.setId(merchantDO.getId());
            userDto.setPassword(merchantDO.getPassword());
            List<SystemRole> roleList = merchantRoleRelationService.getMerchantRoleList(userDto.getId());
            if (!CollectionUtils.isEmpty(roleList)) {
                List<String> stringList = roleList.stream().map(SystemRole::getName).collect(Collectors.toList());
                userDto.setRoles(stringList);
            }
            return userDto;
        }
        return null;
    }

    @Override
    public ResponseData<Void> register(RegisterMerchantVO registerMerchantVO) {
        // 查询是否已存在该用户
        MerchantDO merchantDO = this.lambdaQuery().eq(MerchantDO::getRegisterMobile, registerMerchantVO.getRegisterMobile()).one();
        if (Objects.nonNull(merchantDO)) {
            throw new BusinessException(ResponseCode.USER_NAME_HAS_EXIT_ERROR);
        }
        MerchantDO merchantDO1 = new MerchantDO();
        BeanUtils.copyProperties(registerMerchantVO, merchantDO1);
        merchantDO1.setCreateTime(LocalDateTime.now());
        merchantDO1.setStatus(UserStatusConstant.ACTIVE_STATUS);
        merchantDO1.setPassword(BCrypt.hashpw(registerMerchantVO.getPassword()));
        return ResponseData.judge(this.save(merchantDO1));
    }

    @Override
    public ResponseData<Void> updatePassword(UpdateAdminPasswordParamVO updatePasswordParam) {
        MerchantDO merchantDO = this.baseMapper.selectById(updatePasswordParam.getUserId());
        if (Objects.isNull(merchantDO)) {
            throw new BusinessException(ResponseCode.USER_NAME_NOT_EXIT_ERROR);
        }
        if (!BCrypt.checkpw(updatePasswordParam.getOldPassword(), merchantDO.getPassword())) {
            throw new BusinessException(ResponseCode.OLD_PASSWORD_ERROR);
        }
        merchantDO.setPassword(BCrypt.hashpw(updatePasswordParam.getNewPassword()));
        cacheService.delMerchant(merchantDO.getId());
        return ResponseData.judge(this.updateById(merchantDO));
    }

    @Override
    public MerchantCacheVO getCurrentMerchant() {
        MerchantCacheVO merchantCacheVO = cacheService.getMerchant(AuthUser.getUserId());
        if (Objects.nonNull(merchantCacheVO)) {
            return merchantCacheVO;
        }
        merchantCacheVO = new MerchantCacheVO();
        MerchantDO adminUserDO = this.getById(AuthUser.getUserId());
        BeanUtils.copyProperties(adminUserDO,merchantCacheVO);
        cacheService.setMerchant(merchantCacheVO);
        return merchantCacheVO;
    }
}
