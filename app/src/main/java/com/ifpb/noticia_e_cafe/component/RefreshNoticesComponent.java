package com.ifpb.noticia_e_cafe.component;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.ifpb.noticia_e_cafe.component.adapter.NoticiaAdapter;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 *
 * Este componente funciona como um container para <b>notícias</b> com a funcionalidade de se atualizar
 * ao puxar a tela para baixo. Ele trabalha com uma lista de notícias ao invés de trabalhar um adapter
 * diretamente.
 * @see NoticiaAdapter
 */
public class RefreshNoticesComponent extends SwipeRefreshLayout {

    private List<Noticia> noticias;
    private NoticiaAdapter noticiaAdapter;
    private ListView listView;
    private SwipeRefreshLayout.LayoutParams layoutParams;
    private Activity act;

    public RefreshNoticesComponent(@NonNull Activity activity){
        super(activity);
        this.act = activity;
        this.layoutParams = new SwipeRefreshLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT
        );
        noticias = new ArrayList<>();
        init();
    }

    public RefreshNoticesComponent(@NonNull Activity activity, LayoutParams layoutParams, List<Noticia> noticias) {
        super(activity);
        this.act = activity;
        this.layoutParams = layoutParams;
        this.noticias = noticias;
        init();
    }

    private void init() {
        setLayoutParams(layoutParams);
        noticiaAdapter = new NoticiaAdapter(act,noticias);

        listView = new ListView(getContext());
        listView.setAdapter(noticiaAdapter);

        addView(listView);
    }

    @Override
    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        this.layoutParams = layoutParams;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
        atualizarNoticias();
    }

    public void addNoticia(Noticia noticia){
        noticias.add(noticia);
        atualizarNoticias();
    }

    private void atualizarNoticias(){
        noticiaAdapter = new NoticiaAdapter(act,noticias);
        listView.setAdapter(noticiaAdapter);
    }


}
