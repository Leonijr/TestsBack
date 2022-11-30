package br.com.apiservicos.apiservicos.config;

import br.com.apiservicos.apiservicos.services.EmailService;
import br.com.apiservicos.apiservicos.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DevConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
