package com.bosen.template.adorn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.template.adorn.domain.TemplateDO;
import com.bosen.template.adorn.mapper.TemplateMapper;
import com.bosen.template.adorn.service.ITemplateService;
import com.bosen.template.adorn.vo.request.TemplateQueryVO;
import com.bosen.template.adorn.vo.request.TemplateUpsertVO;
import com.bosen.template.adorn.vo.response.TemplateDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

/**
 * 装修模板(TemplateDO)表服务实现类
 *
 * @author Lucas
 * @since 2023-04-24 18:23:21
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, TemplateDO> implements ITemplateService {

    @Override
    public ResponseData<PageData<TemplateDetailVO>> pageList(TemplateQueryVO pageVO) {
        Page<TemplateDO> page = this.page(new Page<>(pageVO.getCurrent(), pageVO.getSize()), new LambdaQueryWrapper<TemplateDO>()
                .like(StringUtils.hasLength(pageVO.getTemplateName()), TemplateDO::getTemplateName, pageVO.getTemplateName())
                .orderByDesc(TemplateDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            TemplateDetailVO detailVO = new TemplateDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> upsert(TemplateUpsertVO templateUpsertVO) {
        TemplateDO templateDO = new TemplateDO();
        BeanUtils.copyProperties(templateUpsertVO, templateDO);
        return ResponseData.judge(this.saveOrUpdate(templateDO));
    }
}

