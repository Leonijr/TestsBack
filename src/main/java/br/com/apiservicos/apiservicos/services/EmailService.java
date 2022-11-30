package br.com.apiservicos.apiservicos.services;


import br.com.apiservicos.apiservicos.domain.ResetPasswordToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.mail.EmailException;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    public void sendResetPasswordToken(ResetPasswordToken resetPasswordToken) throws JsonProcessingException, EmailException;
}