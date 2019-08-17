package com.ifpb.noticia_e_cafe.entities;

import android.graphics.drawable.Drawable;

public class Noticia{

    private String titulo;
    private String link;
    private String dataPublicacao;
    private String decricao;
    private String conteudo;
    private Drawable img;

    public Noticia(String titulo, String link, String dataPublicacao, String decricao, String conteudo) {
        this.titulo = titulo;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.decricao = decricao;
        this.conteudo = conteudo;
    }

    public Noticia(String titulo, String link, String dataPublicacao, String decricao, String conteudo, Drawable img) {
        this.titulo = titulo;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.decricao = decricao;
        this.conteudo = conteudo;
        this.img = img;
    }

    public Noticia() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", link='" + link + '\'' +
                ", dataPublicacao='" + dataPublicacao + '\'' +
                ", decricao='" + decricao + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
