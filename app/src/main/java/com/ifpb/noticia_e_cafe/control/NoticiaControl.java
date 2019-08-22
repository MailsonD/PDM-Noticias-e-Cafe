package com.ifpb.noticia_e_cafe.control;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ifpb.noticia_e_cafe.exception.ExistingNoticeException;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;
import com.ifpb.noticia_e_cafe.model.interfaces.NoticiaDao;

import java.util.List;

public class NoticiaControl {

    private NoticiaDao noticiaDao;
    private Context context;

    public NoticiaControl(Context context){
        this.context = context;
        noticiaDao = new NoticiaDao(context);
    }

    public void salvar(Noticia noticia){
        try {
            noticiaDao.salvar(noticia);
        } catch (ExistingNoticeException e) {
            Log.e("APP_ERROR", "Notícia já cadastrada!");
        }
    }

    public List<Noticia> listar(){
        return noticiaDao.listar();
    }

}
