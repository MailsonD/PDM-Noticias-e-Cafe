package com.ifpb.noticia_e_cafe.model.entities;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 */
public class Noticia implements Serializable {

    private String guid;
    private String siteFonte;
    private String titulo;
    private String link;
    private String dataPublicacao;
    private String decricao;
    private String conteudo;
    private transient Drawable img;
    private String urlImg;

    public Noticia(String guid, String siteFonte, String titulo, String link, String dataPublicacao, String decricao, String conteudo, Drawable img, String urlImg) {
        this.guid = guid;
        this.siteFonte = siteFonte;
        this.titulo = titulo;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.decricao = decricao;
        this.conteudo = conteudo;
        this.img = img;
        this.urlImg = urlImg;
    }

    public Noticia(String siteFonte, String titulo, String link, String dataPublicacao, String decricao, String conteudo, Drawable img, String urlImg) {
        this.siteFonte = siteFonte;
        this.titulo = titulo;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.decricao = decricao;
        this.conteudo = conteudo;
        this.img = img;
        this.urlImg = urlImg;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSiteFonte() {
        return siteFonte;
    }

    public void setSiteFonte(String siteFonte) {
        this.siteFonte = siteFonte;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "guid='" + guid + '\'' +
                ", siteFonte='" + siteFonte + '\'' +
                ", titulo='" + titulo + '\'' +
                ", link='" + link + '\'' +
                ", dataPublicacao='" + dataPublicacao + '\'' +
                ", decricao='" + decricao + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", img=" + img +
                ", urlImg='" + urlImg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return guid.equals(noticia.guid) &&
                siteFonte.equals(noticia.siteFonte) &&
                titulo.equals(noticia.titulo) &&
                link.equals(noticia.link) &&
                dataPublicacao.equals(noticia.dataPublicacao) &&
                decricao.equals(noticia.decricao) &&
                conteudo.equals(noticia.conteudo) &&
                img.equals(noticia.img) &&
                urlImg.equals(noticia.urlImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, siteFonte, titulo, link, dataPublicacao, decricao, conteudo, img, urlImg);
    }
}
