package br.com.ada.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String mensagem) {
        super(mensagem);
    }
}
