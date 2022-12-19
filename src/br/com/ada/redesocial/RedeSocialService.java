package br.com.ada.redesocial;

import br.com.ada.exceptions.InvalidInputException;
import br.com.ada.exceptions.InvalidPasswordException;
import br.com.ada.exceptions.UserNotFoundException;
import br.com.ada.perfil.Perfil;
import br.com.ada.perfil.PerfilService;
import br.com.ada.post.Post;
import br.com.ada.post.PostService;

import java.util.ArrayList;
import java.util.List;

public class RedeSocialService {
    private static final RedeSocialService INSTANCIA = new RedeSocialService();
    private boolean logado;
    private boolean sair;
    private final List<Perfil> perfis;
    private final List<Post> posts;
    private final List<Post> postsTimeline;
    private Perfil perfilLogado;
    private final RedeSocial redeSocial;

    public RedeSocialService() {
        this.logado = false;
        this.sair = false;
        this.perfis = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.postsTimeline = new ArrayList<>();
        this.redeSocial = new RedeSocial();
    }

    public void iniciarRedeSocial() {
        while (!sair) {
            if (logado) {
                redeSocial.opcoesMenuPerfil();
            } else {
                redeSocial.opcoesMenuPrincipal();
            }
        }
    }

    public void menuPrincipal(String opcao) {
        if (opcao.equalsIgnoreCase("c")) {
            try {
                redeSocial.cadastroPerfil();
            } catch (InvalidInputException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(" Algo deu errado.");
            }
        } else if (opcao.equalsIgnoreCase("e")) {
            try {
                redeSocial.entrar();
            } catch (UserNotFoundException | InvalidPasswordException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(" Algo deu errado.");
            }
        }
    }

    public void menuPerfil(String opcaoMenuPerfil) {
        if (opcaoMenuPerfil.equalsIgnoreCase("p")) {
            try {
                redeSocial.criarPost();
            } catch (InvalidInputException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(" Algo deu errado.");
            }
        } else if (opcaoMenuPerfil.equalsIgnoreCase("t")) {
            redeSocial.mostrarTimeline();
        }
    }

    public boolean cadastrarPerfil(String nome, String login, String senha) throws InvalidInputException {
        if (loginNaoExiste(login)) {
            PerfilService perfilService = new PerfilService();
            Perfil perfil = perfilService.cadastrar(nome, login, senha);
            perfis.add(perfil);
            return true;
        }
        return false;
    }

    public void fazerLogin(String login, String senha) throws UserNotFoundException, InvalidPasswordException {
        perfilLogado = validaLogin(login, senha);
        logado = true;
    }

    public void postar(String conteudo) throws InvalidInputException {
        PostService postService = new PostService();
        Post post = postService.cadastrar(conteudo, perfilLogado.getLogin());
        posts.add(post);
    }

    public void timeline() {
        postsTimeline.clear();
        for (int i = posts.size() - 1; i >= 0; i--) {
            if (posts.get(i).getAutor().equals(perfilLogado.getLogin())) {
                postsTimeline.add(posts.get(i));
            }
        }
    }

    private boolean loginNaoExiste(String login) {
        for (Perfil perfil : perfis) {
            if (perfil.getLogin().equalsIgnoreCase(login)) {
                return false;
            }
        }
        return true;
    }

    private Perfil validaLogin(String login, String senha) throws UserNotFoundException, InvalidPasswordException {
        for (Perfil perfil : perfis) {
            if (perfil.getLogin().equals(login)) {
                if (perfil.getSenha().equals(senha)) {
                    return perfil;
                } else {
                    throw new InvalidPasswordException("\n Senha incorreta!");
                }
            }
        }
        throw new UserNotFoundException("\n Usuário não cadastrado!");
    }

    public Perfil getPerfilLogado() {
        return perfilLogado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public void setSair(boolean sair) {
        this.sair = sair;
    }

    public List<Post> getPostsTimeline() {
        return postsTimeline;
    }

    public static RedeSocialService getInstance() {
        return INSTANCIA;
    }
}
