package com.bosen.drools.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.drools.domain.DroolsInfoDO;
import com.bosen.drools.mapper.DroolsInfoMapper;
import com.bosen.drools.service.IDroolsInfoService;
import com.bosen.drools.vo.request.DroolsQueryVO;
import com.bosen.drools.vo.request.DroolsUpsertVO;
import lombok.extern.slf4j.Slf4j;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 规则详情表(BsDroolsInfo)表服务实现类
 *
 * @author Lucas
 * @since 2023-05-12 16:08:49
 */
@Slf4j
@Service
public class DroolsInfoServiceImpl extends ServiceImpl<DroolsInfoMapper, DroolsInfoDO> implements IDroolsInfoService {

    @Override
    public ResponseData<PageData<DroolsInfoDO>> pageList(DroolsQueryVO queryVO) {
        Page<DroolsInfoDO> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<DroolsInfoDO>()
                .like(StringUtils.hasLength(queryVO.getDroolsName()), DroolsInfoDO::getDroolsName, queryVO.getDroolsName())
                .like(StringUtils.hasLength(queryVO.getDroolsCode()), DroolsInfoDO::getDroolsCode, queryVO.getDroolsCode())
                .orderByDesc(DroolsInfoDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords()));
    }

    @Override
    public ResponseData<String> importDecisionTables(MultipartFile file) {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String compile = null;
        try {
            compile = compiler.compile(file.getInputStream(), InputType.CSV);
            System.out.println("结果：" + compile);
            log.info("通过excel生产drools成功， {}", compile);
        } catch (IOException e) {
            log.error("通过excel生产drools失败：{}", e.getMessage());
            e.printStackTrace();
        }
        return ResponseData.success(compile);
    }

    @Override
    public ResponseData<Void> upsertDrools(DroolsUpsertVO droolsUpsertVO) {
        return null;
    }
}

