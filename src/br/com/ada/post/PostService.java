package br.com.ada.post;

import br.com.ada.exceptions.InvalidInputException;

public class PostService {
    public Post cadastrar(String conteudo, String autor) throws InvalidInputException {
        if (validaPost(conteudo)) {
            return new Post(conteudo, autor);
        }
        return null;
    }

    private boolean validaPost(String conteudo) throws InvalidInputException {
        if (conteudo.isBlank()) {
            throw new InvalidInputException("\n Conteúdo não pode ser vazio.");
        } else if (conteudo.trim().length() < 4) {
            throw new InvalidInputException("\n O conteúdo do post não pode conter menos que 4 caracteres.");
        }
        return true;
    }
}
