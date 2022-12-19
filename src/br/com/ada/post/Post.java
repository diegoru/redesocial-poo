package br.com.ada.post;

import java.time.LocalDate;
import java.time.LocalTime;

public class Post {
    private LocalDate data;
    private LocalTime hora;
    private String conteudo;
    private String autor;

    public Post(String conteudo, String autor) {
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
        this.conteudo = conteudo;
        this.autor = autor;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
}
