package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.controller.dto.ResetPasswordDTO;
import br.com.apiservicos.apiservicos.domain.ResetPasswordToken;
import br.com.apiservicos.apiservicos.domain.Usuario;
import br.com.apiservicos.apiservicos.utils.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${application.smtp-sender}")
    private String smtpSender;

    @Value("${application.reset-password-url}")
    private String resetPassowdUrl;

    public void sendResetPasswordToken(ResetPasswordToken resetPasswordToken)
            throws JsonProcessingException, EmailException {
        SimpleMailMessage msg = this.prepareResetPasswordEmail(resetPasswordToken);
        sendEmail(msg);
    }

    protected SimpleMailMessage prepareResetPasswordEmail(ResetPasswordToken resetPasswordToken)
            throws JsonProcessingException, EmailException {

        SimpleMailMessage sm = new SimpleMailMessage();


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();


        Usuario user = resetPasswordToken.getUser();

        System.out.println(user);
        sm.setTo(user.getEmail());
        sm.setFrom(smtpSender);
        sm.setSubject("Alteração de senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));

        ResetPasswordDTO dto = ObjectMapperUtil.map(resetPasswordToken,ResetPasswordDTO.class);

        System.out.println(dto);
        dto.setPassword("newPassword");
        dto.setPasswordConfirm("newPassword");

        String sb = "Olá, você pediu para fazer um alteração em sua senha? caso sim entre no link abaixo para fazer a alteração, " +
                "Caso não foi você fique atento pois alguem está tentando entrar em sua conta! " +
                resetPassowdUrl + "/" + dto.getToken();


        sm.setText(sb);
        return sm;
    }
}