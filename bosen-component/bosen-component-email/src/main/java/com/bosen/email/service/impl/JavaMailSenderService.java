package com.bosen.email.service.impl;

import com.bosen.email.domain.EmailFileRequest;
import com.bosen.email.domain.SimpleEmailRequest;
import com.bosen.email.service.IEmailBaseService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
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

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleEmail(SimpleEmailRequest request) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(String.join(",", request.getEmailTo()));
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
                    BodyPart filePart = new MimeBodyPart();
                    try {
                        filePart.setFileName(i.getFileName());
                        filePart.setDataHandler(new DataHandler(new ByteArrayDataSource(Files.readAllBytes(Paths.get(i.getFilePath())), "application/octet-stream")));
                        multipart.addBodyPart(filePart);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                });
            }
            mimeMessage.setContent(multipart);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
