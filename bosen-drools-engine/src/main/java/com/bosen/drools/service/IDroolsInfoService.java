package com.bosen.drools.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.drools.domain.DroolsScriptDO;
import com.bosen.drools.vo.request.DroolsQueryVO;
import com.bosen.drools.vo.request.DroolsUpsertVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 规则详情表(BsDroolsInfo)表服务接口
 *
 * @author Lucas
 * @since 2023-05-12 16:08:48
 */
public interface IDroolsInfoService extends IService<DroolsScriptDO> {
    /**
     * 分页查询
     * @param queryVO 参数
     * @return 分页结果
     */
    ResponseData<PageData<DroolsScriptDO>> pageList(DroolsQueryVO queryVO);

    /**
     * 倒入决策表
     * @param file 文件
     * @return 结果
     */
    ResponseData<String> importDecisionTables(MultipartFile file);

    /**
     * 新增/修改
     * @param droolsUpsertVO 参数
     * @return 结果
     */
    ResponseData<Void> upsertDrools(DroolsUpsertVO droolsUpsertVO);
}

