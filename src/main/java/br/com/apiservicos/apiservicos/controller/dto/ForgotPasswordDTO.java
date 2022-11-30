package br.com.apiservicos.apiservicos.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class ForgotPasswordDTO {

    @NotNull
    @Email
    private String email;

    public String getEmail() {
        return email;
    }
}
