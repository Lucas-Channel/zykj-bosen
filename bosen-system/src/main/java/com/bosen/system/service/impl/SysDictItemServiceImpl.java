package com.bosen.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.SysDictItemDO;
import com.bosen.system.mapper.SysDictItemMapper;
import com.bosen.system.service.ISysDictItemService;
import com.bosen.system.vo.request.DictItemQueryVO;
import com.bosen.system.vo.request.DictItemUpsertVO;
import com.bosen.system.vo.response.DictItemDetailVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

/**
 * 字典管理明细(BsSysDictItem)表服务实现类
 *
 * @author Lucas
 * @since 2023-05-25 16:34:05
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItemDO> implements ISysDictItemService {

    @Override
    public ResponseData<PageData<DictItemDetailVO>> pageList(DictItemQueryVO pageVO) {
        Page<SysDictItemDO> page = this.page(new Page<>(pageVO.getCurrent(), pageVO.getSize()), new LambdaQueryWrapper<SysDictItemDO>()
                        .eq(StringUtils.hasLength(pageVO.getDictId()), SysDictItemDO::getDictId, pageVO.getDictId())
                .orderByDesc(SysDictItemDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> BeanUtil.copyProperties(i, DictItemDetailVO.class)).collect(Collectors.toList())));
    }

    @Override
    @Transactional
    public ResponseData<Void> upsert(DictItemUpsertVO dictItemUpsertVO) {
        SysDictItemDO dictItemDO = BeanUtil.copyProperties(dictItemUpsertVO, SysDictItemDO.class);
        return ResponseData.judge(this.saveOrUpdate(dictItemDO));
    }
}

