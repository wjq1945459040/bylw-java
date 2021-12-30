package com.wjq.service.impl;

import com.wjq.service.MsmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 邮件服务实现类
 * </p>
 *
 * @author wjq
 * @since 2021-12-26
 */

@Service
@Slf4j
public class MsmServiceImpl implements MsmService {

    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;

    @Override
    public boolean sent(String email, String title, String code) {

        if (StringUtils.isEmpty(email)) {
            return false;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email);
        message.setSubject(title);
        message.setText(code);
        mailSender.send(message);
        log.info("邮件发送成功");
        return true;
    }
}
