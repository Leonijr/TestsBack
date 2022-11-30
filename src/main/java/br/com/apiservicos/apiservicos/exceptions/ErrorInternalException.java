package br.com.apiservicos.apiservicos.exceptions;

public class ErrorInternalException extends RuntimeException {

    public ErrorInternalException() {
        super("erro.naoEncontrado");
    }
    public ErrorInternalException(String mensagem) {
        super(mensagem);
    }
}
