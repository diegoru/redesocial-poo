package br.com.ada.redesocial;

import br.com.ada.exceptions.InvalidInputException;
import br.com.ada.exceptions.InvalidPasswordException;
import br.com.ada.exceptions.UserNotFoundException;
import br.com.ada.post.Post;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RedeSocial {
    public void opcoesMenuPrincipal() {
        System.out.println("\n\t -- Menu Principal --");
        System.out.println(" Digite uma das opções abaixo: ");
        System.out.println(" C - para cadastrar-se");
        System.out.println(" E - para entrar");
        System.out.println(" S - para sair");
        System.out.print(" # Sua opção: ");
        Scanner entrada = new Scanner(System.in);
        String opcaoMenuPrincipal = entrada.nextLine().trim();
        if (opcaoMenuPrincipal.equalsIgnoreCase("c") ||
                opcaoMenuPrincipal.equalsIgnoreCase("e")) {
            RedeSocialService.getInstance().menuPrincipal(opcaoMenuPrincipal);
        } else if (opcaoMenuPrincipal.equalsIgnoreCase("s")) {
            System.out.println("\n Até logo e volte sempre!");
            RedeSocialService.getInstance().setSair(true);
        } else {
            System.out.println("\n Opção inválida!");
        }
    }
    public void opcoesMenuPerfil() {
        System.out.println("\n\t -- Menu Perfil --");
        System.out.println(" Digite uma das opções abaixo: ");
        System.out.println(" P - para postar");
        System.out.println(" T - para ver sua timeline");
        System.out.println(" S - para sair");
        System.out.print(" # Sua opção: ");
        Scanner entrada = new Scanner(System.in);
        String opcaoMenuPerfil = entrada.nextLine();
        if (opcaoMenuPerfil.equalsIgnoreCase("p") ||
                opcaoMenuPerfil.equalsIgnoreCase("t")) {
            RedeSocialService.getInstance().menuPerfil(opcaoMenuPerfil);
        } else if (opcaoMenuPerfil.equalsIgnoreCase("s")) {
            System.out.println("\n Logout realizado com sucesso!");
            RedeSocialService.getInstance().setLogado(false);
        } else {
            System.out.println("\n Opção inválida!");
        }
    }
    public void cadastroPerfil() throws InvalidInputException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n\t -- Realizar Cadastro --");
        System.out.print(" Informe o nome: ");
        String nome = entrada.nextLine();

        System.out.print(" Informe o login: ");
        String login = entrada.nextLine();

        System.out.print(" Informe a senha: ");
        String senha = entrada.nextLine();

        boolean cadastrado = RedeSocialService.getInstance().cadastrarPerfil(nome, login, senha);
        if (cadastrado) {
            System.out.println("\n Cadastro realizado com sucesso!");
        } else {
            System.out.println("\n Login já existe, tente utilizar outro nome. Caso seja você, faça o login.");
        }
    }
    public void entrar() throws UserNotFoundException, InvalidPasswordException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n\t -- Fazer Login --");
        System.out.print(" Informe seu login: ");
        String login = entrada.nextLine().trim();

        System.out.print(" Informe a sua senha: ");
        String senha = entrada.nextLine();

        RedeSocialService.getInstance().fazerLogin(login, senha);
        System.out.println("\n ## Olá, " + RedeSocialService.getInstance().getPerfilLogado().getNome() + "! ##");
    }
    public void criarPost() throws InvalidInputException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n\t -- Criar Post --");
        System.out.print(" Conteudo: ");
        String conteudo = entrada.nextLine();
        RedeSocialService.getInstance().postar(conteudo);
        System.out.println("\n Post criado com sucesso!");
    }
    public void mostrarTimeline() {
        RedeSocialService.getInstance().timeline();
        List<Post> postsPerfil = RedeSocialService.getInstance().getPostsTimeline();
        if (postsPerfil.size() == 0) {
            System.out.println("\n Nenhum post encontrado.");
        } else {
            System.out.println("\n\t -- Sua Timeline --\n");
            for (Post post : postsPerfil) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
                System.out.println(" " + post.getData().format(formatterData) + " às "
                        + post.getHora().format(formatterHora) + " - " + post.getConteudo());
                System.out.println(" ---");
            }
        }
    }
}
