package com.ifpb.noticia_e_cafe.noticias;

import android.graphics.drawable.Drawable;

import java.util.Objects;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 */
public class Noticia{

    private int id;
    private String siteFonte;
    private String titulo;
    private String link;
    private String dataPublicacao;
    private String decricao;
    private String conteudo;
    private Drawable img;

    public Noticia(int id, String siteFonte, String titulo, String link, String dataPublicacao, String decricao, String conteudo, Drawable img) {
        this.id = id;
        this.siteFonte = siteFonte;
        this.titulo = titulo;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.decricao = decricao;
        this.conteudo = conteudo;
        this.img = img;
    }

    public Noticia(String siteFonte, String titulo, String link, String dataPublicacao, String decricao, String conteudo, Drawable img) {
        this.siteFonte = siteFonte;
        this.titulo = titulo;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.decricao = decricao;
        this.conteudo = conteudo;
        this.img = img;
    }

    public Noticia(String siteFonte, String titulo, String link, String dataPublicacao, String decricao, String conteudo) {
        this.siteFonte = siteFonte;
        this.titulo = titulo;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.decricao = decricao;
        this.conteudo = conteudo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiteFonte() {
        return siteFonte;
    }

    public void setSiteFonte(String siteFonte) {
        this.siteFonte = siteFonte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Noticia)) return false;
        Noticia noticia = (Noticia) o;
        return getId() == noticia.getId() &&
                Objects.equals(getSiteFonte(), noticia.getSiteFonte()) &&
                Objects.equals(getTitulo(), noticia.getTitulo()) &&
                Objects.equals(getLink(), noticia.getLink()) &&
                Objects.equals(getDataPublicacao(), noticia.getDataPublicacao()) &&
                Objects.equals(getDecricao(), noticia.getDecricao()) &&
                Objects.equals(getConteudo(), noticia.getConteudo()) &&
                Objects.equals(getImg(), noticia.getImg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSiteFonte(), getTitulo(), getLink(), getDataPublicacao(), getDecricao(), getConteudo(), getImg());
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "id=" + id +
                ", siteFonte='" + siteFonte + '\'' +
                ", titulo='" + titulo + '\'' +
                ", link='" + link + '\'' +
                ", dataPublicacao='" + dataPublicacao + '\'' +
                ", decricao='" + decricao + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", img=" + img +
                '}';
    }
}
