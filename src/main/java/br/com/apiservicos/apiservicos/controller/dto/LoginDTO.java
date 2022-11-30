package br.com.apiservicos.apiservicos.controller.dto;

import br.com.apiservicos.apiservicos.exceptions.RegraDeNegocioException;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull
    private String email;

    @NotNull
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(senha.isEmpty()){
            throw new RegraDeNegocioException("senha.IsEmpty");
        }
        this.senha = senha;
    }

}