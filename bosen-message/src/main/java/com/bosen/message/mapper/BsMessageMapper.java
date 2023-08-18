package com.bosen.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosen.message.domain.BsMessage;
import com.bosen.message.vo.request.MessageQueryVO;
import com.bosen.message.vo.response.BsMessageDetailVO;
import org.apache.ibatis.annotations.Param;

/**
 * 消息(BsMessage)表数据库访问层
 *
 * @author Lucas
 * @since 2023-08-18 11:14:55
 */
public interface BsMessageMapper extends BaseMapper<BsMessage> {

    IPage<BsMessageDetailVO> pageList(Page<BsMessageDetailVO> page, @Param("queryParams") MessageQueryVO queryVO);

    Integer unReadList(@Param("queryParams") MessageQueryVO queryVO);
}

