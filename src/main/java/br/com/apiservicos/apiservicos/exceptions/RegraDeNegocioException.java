package br.com.apiservicos.apiservicos.exceptions;

public class RegraDeNegocioException extends RuntimeException{

    public RegraDeNegocioException() {
        super("erro.naoEncontrado");
    }
    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}