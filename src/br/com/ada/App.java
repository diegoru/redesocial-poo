package br.com.ada;

import br.com.ada.redesocial.RedeSocialService;

public class App {
    public static void main(String[] args) {
        System.out.println(" ## SEJA BEM-VINDO(A) A REDE SOCIAL ##");
        RedeSocialService.getInstance().iniciarRedeSocial();
    }
}
