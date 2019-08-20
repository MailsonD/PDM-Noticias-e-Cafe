package com.ifpb.noticia_e_cafe.telas;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ifpb.noticia_e_cafe.component.RefreshNoticesComponent;
import com.ifpb.noticia_e_cafe.control.NoticiaControl;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;

import java.util.List;

public class TelaPrincipal extends NavBar {

    private LinearLayout mainLayout;
    private RefreshNoticesComponent noticesComponent;
    private LinearLayout.LayoutParams layoutParams;
    private NoticiaControl noticiaControl;
    private List<Noticia> noticias;

    @Override
    public void onCreate(Bundle salvedInstanceState){
        super.onCreate(salvedInstanceState);
        init();
    }

    private void init() {
        mainLayout = new LinearLayout(this);
        layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );

        mainLayout.setLayoutParams(layoutParams);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        noticiaControl = new NoticiaControl(this);

        buscarNoticias();

        noticesComponent = new RefreshNoticesComponent(this, layoutParams, noticias);

        noticesComponent.setOnRefreshListener(() -> {
            buscarNoticias();
            noticesComponent.setNoticias(noticias);
        });

        mainLayout.addView(noticesComponent);
        setDynamicContent(mainLayout);
    }

    private void buscarNoticias() {
        noticias = noticiaControl.listar();
    }

    @Override
    public String getTitleNavBar() {
        return "Notícias";
    }
}
