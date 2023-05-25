package com.bosen.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.SysDictDO;
import com.bosen.system.mapper.SysDictMapper;
import com.bosen.system.service.ISysDictService;
import com.bosen.system.vo.request.DictQueryVO;
import com.bosen.system.vo.request.DictUpsertVO;
import com.bosen.system.vo.response.DictDetailVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

/**
 * 字典管理(BsSysDict)表服务实现类
 *
 * @author Lucas
 * @since 2023-05-25 16:34:05
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictDO> implements ISysDictService {

    @Override
    public ResponseData<PageData<DictDetailVO>> pageList(DictQueryVO pageVO) {
        Page<SysDictDO> page = this.page(new Page<>(pageVO.getCurrent(), pageVO.getSize()), new LambdaQueryWrapper<SysDictDO>()
                .like(StringUtils.hasLength(pageVO.getDictCode()), SysDictDO::getDictCode, pageVO.getDictCode())
                .like(StringUtils.hasLength(pageVO.getDictName()), SysDictDO::getDictName, pageVO.getDictName())
                .orderByDesc(SysDictDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> BeanUtil.copyProperties(i, DictDetailVO.class)).collect(Collectors.toList())));
    }

    @Override
    @Transactional
    public ResponseData<Void> upsert(DictUpsertVO dictUpsertVO) {
        SysDictDO dictDO = BeanUtil.copyProperties(dictUpsertVO, SysDictDO.class);
        return ResponseData.judge(this.saveOrUpdate(dictDO));
    }
}

