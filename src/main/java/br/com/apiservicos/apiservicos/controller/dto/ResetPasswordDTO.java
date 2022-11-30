package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.services.validation.ChagePasswordSave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ChagePasswordSave
public class ResetPasswordDTO {

    @NotNull
    @Size(min=10)
    private String token;

    @NotNull
    @Size(min=6)
    private String password;

    @NotNull
    @Size(min=6)
    @JsonProperty("password_confirm")
    private String passwordConfirm;

    public String getToken() {
        return token;
    }
}