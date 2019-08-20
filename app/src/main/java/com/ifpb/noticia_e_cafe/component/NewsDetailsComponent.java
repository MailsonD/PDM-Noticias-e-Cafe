package com.ifpb.noticia_e_cafe.component;

import android.content.Context;
import android.graphics.Color;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.util.DeviceProperties;

public class NewsDetailsComponent extends LinearLayout {
    private String titulo;
    private String conteudo;
    private String data;
    private String site;
    private ImageView capaNoticia;
    private WindowManager windowManager;

    public NewsDetailsComponent(Context context, String titulo, String conteudo, String data, String site, ImageView capaNoticia, WindowManager windowManager) {
        super(context);
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.data = data;
        this.site = site;
        this.capaNoticia = capaNoticia;
        this.windowManager = windowManager;
        init();
    }

    public NewsDetailsComponent(Context context, String titulo, String conteudo, String data, String site, WindowManager windowManager) {
        super(context);
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.data = data;
        this.site = site;
        this.windowManager = windowManager;
        init();
    }
    //    private TextView autor;

    private void init() {
        int height = DeviceProperties.getDeviceHeight(windowManager);
        int width = DeviceProperties.getDeviceWidth(windowManager);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
        setPadding(30, 30, 30, 30);



        if(capaNoticia!=null){
            capaNoticia.setAdjustViewBounds(true);
            capaNoticia.setMaxHeight(height);
            capaNoticia.setMaxWidth(width);
            addView(capaNoticia);
        }

        TextView textViewTitulo = new TextView(getContext());
        textViewTitulo.setText("\n"+titulo);
        textViewTitulo.setTextSize(21);
        textViewTitulo.setWidth(width);

        TextView textViewInfo = new TextView(getContext());
        String info = data+"      "+site;
        textViewInfo.setText(info);
        textViewInfo.setTextSize(13);
        textViewInfo.setWidth(width);

        TextView textViewConteudo = new TextView(getContext());
        textViewConteudo.setText("\n"+conteudo);
        textViewConteudo.setTextSize(17);
        textViewConteudo.setWidth(width);

        addView(textViewTitulo);
        addView(textViewInfo);
        addView(textViewConteudo);
    }
}