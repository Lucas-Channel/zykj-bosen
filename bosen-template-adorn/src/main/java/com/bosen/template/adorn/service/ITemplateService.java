package com.bosen.template.adorn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.template.adorn.domain.TemplateDO;
import com.bosen.template.adorn.vo.request.TemplateQueryVO;
import com.bosen.template.adorn.vo.request.TemplateUpsertVO;
import com.bosen.template.adorn.vo.response.TemplateDetailVO;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/24
 */
public interface ITemplateService extends IService<TemplateDO> {
    ResponseData<PageData<TemplateDetailVO>> pageList(TemplateQueryVO pageVO);

    ResponseData<Void> upsert(TemplateUpsertVO templateUpsertVO);
}
