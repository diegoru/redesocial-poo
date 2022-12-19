package br.com.ada.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String mensagem) {
        super(mensagem);
    }
}
