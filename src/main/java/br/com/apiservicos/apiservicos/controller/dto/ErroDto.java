package br.com.apiservicos.apiservicos.controller.dto;

import lombok.Getter;

@Getter
public class ErroDto {
    private String erro;
    private String propriedade;

    public ErroDto(String erro, String propriedade) {
        super();
        this.erro = erro;
        this.propriedade = propriedade;
    }

    public ErroDto(String erro) {
        this.erro = erro;
    }
}