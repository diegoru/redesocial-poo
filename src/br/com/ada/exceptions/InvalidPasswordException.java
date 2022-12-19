package br.com.ada.exceptions;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String mensagem) {
        super(mensagem);
    }
}
