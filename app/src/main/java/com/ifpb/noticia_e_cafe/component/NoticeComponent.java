package com.ifpb.noticia_e_cafe.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.entities.Noticia;
import com.ifpb.noticia_e_cafe.util.DeviceProperties;

/**
 *
 * @author Leanerson-Coelho
 * @date 08/08/2019
 */

public class NoticeComponent extends LinearLayout {


    private TextView titulo;
    private TextView descricao;
    private TextView data;
    private ImageView capaNoticia;
    private LinearLayout layoutInformacoes;
    private LinearLayout layoutImagem;

    /**
     * Constroi um <b>Componente</b> de notícia sem imagem.
     * @param tela -> Activity da tela que o componente será inserido
     * @param noticia -> Objeto notícia que será exibido no componente
     */
    public NoticeComponent(Activity tela, Noticia noticia) {
        super(tela);
        this.init(tela, noticia);
        //definindo layout
        if(noticia.getImg() != null){
            this.noticeWithoutLayoutImage(tela);
        }else{
            this.noticeWithLayoutImage(tela, noticia.getImg());
        }
    }

    /**
     * private void init -> Adiciona os valores informados no construtor ao seus respectivos componentes.
     * @param tela -> Activity da tela que o componente será inserido
     * @param noticia -> Objeto notícia que será exibido no componente
     */
    private void init(Activity tela, Noticia noticia) {
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setOrientation(VERTICAL);

        Integer halfWidth = DeviceProperties.getDeviceWidth(tela.getWindowManager())/2;

        this.titulo = new TextView(tela);
        this.titulo.setText(noticia.getTitulo());
        this.titulo.setTextSize(20);
        this.titulo.setWidth(halfWidth);

        this.descricao = new TextView(tela);
        this.descricao.setText(noticia.getDecricao());
        this.descricao.setTextSize(14);
        this.descricao.setPadding(0,10,0,0);
        this.descricao.setWidth(halfWidth);

        this.data = new TextView(tela);
        this.data.setText(noticia.getDataPublicacao());
        this.data.setTextSize(16);
        this.data.setPadding(0,10,0,0);
        this.data.setWidth(halfWidth);
    }

    /**
     * private void noticeWithoutLayoutImage -> Cria e configura o layout da notícia sem imagem.
     * @param context -> Context da tela que o componente será inserido
     */
    private void noticeWithoutLayoutImage(Context context){
        this.layoutInformacoes = new LinearLayout(context);
        this.layoutInformacoes.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        this.layoutInformacoes.setOrientation(VERTICAL);
        this.layoutImagem.setPadding(10,10,10,10);
        addView(this.layoutInformacoes);
        this.layoutInformacoes.addView(this.titulo);
        this.layoutInformacoes.addView(this.descricao);
        this.layoutInformacoes.addView(this.data);
    }

    /**
     * private void noticeWithLayoutImage -> Cria e configura o layout da notícia com imagem.
     * @param context -> Context da tela que o componente será inserido
     * @param imgCapa -> Imagem de capa da notícia.
     */
    private void noticeWithLayoutImage(Context context, Drawable imgCapa){
        this.layoutImagem = new LinearLayout(context);
        this.layoutImagem.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        this.layoutImagem.setOrientation(HORIZONTAL);
        this.layoutImagem.setPadding(20,20,20,20);
        addView(this.layoutImagem);

        LinearLayout columInfo = new LinearLayout(this.layoutImagem.getContext());
        columInfo.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        columInfo.setOrientation(VERTICAL);

        columInfo.addView(this.titulo);
        columInfo.addView(this.descricao);
        columInfo.addView(this.data);

        LinearLayout columImg = new LinearLayout(this.layoutImagem.getContext());
        columImg.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        columImg.setOrientation(VERTICAL);
        columImg.setGravity(Gravity.CENTER_VERTICAL);

        ImageView capaNoticia = new ImageView(context);
        capaNoticia.setImageDrawable(imgCapa);

        //permite definir dimensoes da img
        capaNoticia.setAdjustViewBounds(true);
        capaNoticia.setMaxWidth(300);
        capaNoticia.setMaxHeight(300);

        columImg.addView(capaNoticia);

        this.layoutImagem.addView(columInfo);
        this.layoutImagem.addView(columImg);
    }

}
