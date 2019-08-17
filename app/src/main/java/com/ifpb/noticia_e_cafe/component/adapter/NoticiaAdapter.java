package com.ifpb.noticia_e_cafe.component.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ifpb.noticia_e_cafe.component.NoticeComponent;
import com.ifpb.noticia_e_cafe.entities.Noticia;

import java.util.List;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 * @see android.widget.Adapter
 * @see Noticia
 */
public class NoticiaAdapter extends BaseAdapter {

    private final List<Noticia> noticias;
    private final Activity activity;

    public NoticiaAdapter(Activity act, List<Noticia> noticias) {
        Log.i("APP_INFO","Adapter para noticia criado com "+noticias.size()+" noticias!");
        this.noticias = noticias;
        this.activity = act;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int i) {
        return noticias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Constroi o componente de visualização de noticia para cada noticia dentro de um ListView
        Noticia noticia = noticias.get(i);
        if(noticia.getImg() != null){
            //Componente com imagem
            ImageView imageView = new ImageView(activity);
            imageView.setImageDrawable(noticia.getImg());
            return new NoticeComponent(activity,noticia.getTitulo(),noticia.getDecricao(),noticia.getDataPublicacao(),imageView);
        }
        //Componente sem imagem
        return new NoticeComponent(activity,noticia.getTitulo(),noticia.getDecricao(),noticia.getDataPublicacao());

    }
}
