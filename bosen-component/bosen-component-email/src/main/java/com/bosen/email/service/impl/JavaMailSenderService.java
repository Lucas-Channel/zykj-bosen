package com.bosen.email.service.impl;

import com.bosen.email.domain.EmailFileRequest;
import com.bosen.email.domain.SimpleEmailRequest;
import com.bosen.email.service.IEmailBaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2024/9/12
 */
@Service("javaMailSenderService")
public class JavaMailSenderService implements IEmailBaseService {

    @Value("${spring.mail.from}")
    private String emailFrom;

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleEmail(SimpleEmailRequest request) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(String.join(",", request.getEmailTo()));
        simpleMailMessage.setFrom(emailFrom);
        if (!CollectionUtils.isEmpty(request.getEmailCopyTo())) {
            simpleMailMessage.setCc(String.join(",", request.getEmailCopyTo()));
        }
        simpleMailMessage.setSubject(request.getEmailTitle());
        simpleMailMessage.setText(request.getContent());
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendSimpleEmailWithFiles(SimpleEmailRequest request, List<EmailFileRequest> fileRequests) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            mimeMessage.setSubject(request.getEmailTitle());
            BodyPart bodyPart = new MimeBodyPart();
            // 主体内容
            bodyPart.setContent(request.getContent(), "text/html;charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            if (!CollectionUtils.isEmpty(fileRequests)) {
                fileRequests.forEach(i -> {
                    MimeBodyPart filePart = new MimeBodyPart();
                    try {
                        filePart.setFileName(MimeUtility.encodeText(i.getFileName()));
                        filePart.setDataHandler(new DataHandler(new FileDataSource(new File(i.getFilePath()))));
                        multipart.addBodyPart(filePart);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                });
            }
            mimeMessage.setContent(multipart);
            mimeMessage.setFrom(emailFrom);
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", request.getEmailTo())));
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void sendHtmlEmail(SimpleEmailRequest request) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(request.getEmailTo().toArray(new String[0]));
            helper.setSubject(request.getEmailTitle());
            helper.setText(request.getContent(), true); // true表示内容为HTML
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
        javaMailSender.send(message);
    }

    @Override
    public void sendEmailWithInlineImage(SimpleEmailRequest request, List<EmailFileRequest> picFiles) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(request.getEmailTo().toArray(new String[0]));
            helper.setSubject(request.getEmailTitle());
            helper.addInline("logo", new File(""));// todo 等接入文件服务后调整
            helper.setText(request.getContent(), true); // true表示内容为HTML
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
        javaMailSender.send(message);
    }

}
