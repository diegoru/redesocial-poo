package br.com.ada.perfil;

import br.com.ada.exceptions.InvalidInputException;

public class PerfilService {
    public Perfil cadastrar(String nome, String login, String senha) throws InvalidInputException {
        if (validaNome(nome) && validaLogin(login) && validaSenha(senha)) {
            nome = upperfirstCase(nome);
            return new Perfil(nome, login, senha);
        }
        return null;
    }

    private boolean validaNome(String nome) throws InvalidInputException {
        if (nome.isBlank()) {
            throw new InvalidInputException("\n Nome não pode ser vazio.");
        } else if (!somenteLetras(nome)) {
            throw new InvalidInputException("\n Nome/Sobrenome deve possuir apenas letras.");
        }
        return true;
    }

    private boolean validaLogin(String login) throws InvalidInputException {
        if (login.isBlank()) {
            throw new InvalidInputException("\n Login não pode ser vazio.");
        } else if (login.length() < 2) {
            throw new InvalidInputException("\n Login deve possuir no mínimo 2 caracteres.");
        } else if (temEspaco(login)) {
            throw new InvalidInputException("\n Login não pode possuir espaços.");
        } else if (caracterInvalido(login)) {
            throw new InvalidInputException("\n Login só pode conter letras sem acento, números, sublinhados e pontos.");
        }
        return true;
    }

    private boolean validaSenha(String senha) throws InvalidInputException {
        if (senha.isBlank()) {
            throw new InvalidInputException("\n Senha não pode ser vazio.");
        } else if (senha.length() < 4) {
            throw new InvalidInputException("\n Senha deve possuir no mínimo 4 caracteres.");
        } else if (temEspaco(senha)) {
            throw new InvalidInputException("\n Senha não pode possuir espaços.");
        }
        return true;
    }

    private boolean somenteLetras(String texto) {
        for (Character letra : texto.toCharArray()) {
            if (!Character.isLetter(letra) && !Character.isWhitespace(letra)) {
                return false;
            }
        }
        return true;
    }

    private boolean temEspaco(String texto) {
        for (char c : texto.toCharArray()) {
            if (c == ' ') {
                return true;
            }
        }
        return false;
    }

    private String upperfirstCase(String texto) {
        String[] palavras = texto.trim().split(" ");
        String textoUpperFirstCase = "";
        for (String s : palavras) {
            if (!s.isBlank()) {
                textoUpperFirstCase = textoUpperFirstCase.concat(s.substring(0, 1).toUpperCase()
                        .concat(s.substring(1).toLowerCase()) + " ");
            }
        }
        return textoUpperFirstCase.trim();
    }

    private boolean caracterInvalido(String texto) {
        char[] caracterInvalidos = {'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï',
                'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï',
                'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ'};

        for (Character c : texto.toCharArray()) {
            if (!c.equals('_') && !c.equals('.') && !Character.isDigit(c) && !Character.isLetter(c)) {
                return true;
            } else {
                for (char invalido : caracterInvalidos) {
                    if (c.equals(invalido)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
