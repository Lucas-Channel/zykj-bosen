package com.bosen.email.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2024/9/12
 */
@Data
public class SimpleEmailRequest implements Serializable {
    private static final long serialVersionUID = -2103232715357466035L;

    /**
     * 发送人邮箱
     */
    private String emailFrom;

    /**
     * 收件人邮箱
     */
    private List<String> emailTo;

    /**
     * 抄送人邮箱
     */
    private List<String> emailCopyTo;

    /**
     * 邮件主题
     */
    private String emailTitle;

    /**
     * 邮件内容
     */
    private String content;

}
