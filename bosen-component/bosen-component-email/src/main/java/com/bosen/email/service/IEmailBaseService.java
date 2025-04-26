package com.bosen.email.service;

import com.bosen.email.domain.EmailFileRequest;
import com.bosen.email.domain.SimpleEmailRequest;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2024/9/12
 */
public interface IEmailBaseService {
    /**
     * 发送简要邮件信息
     */
    void sendSimpleEmail(SimpleEmailRequest request);
    /**
     * 发送简要邮件信息 + 附件
     */
    void sendSimpleEmailWithFiles(SimpleEmailRequest request, List<EmailFileRequest> fileRequests);
}
