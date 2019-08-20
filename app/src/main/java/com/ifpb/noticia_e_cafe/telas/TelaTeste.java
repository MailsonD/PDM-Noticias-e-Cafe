package com.ifpb.noticia_e_cafe.telas;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.ifpb.noticia_e_cafe.model.entities.Noticia;
import com.ifpb.noticia_e_cafe.model.entities.Usuario;
import com.ifpb.noticia_e_cafe.model.interfaces.NoticiaDao;
import com.ifpb.noticia_e_cafe.model.interfaces.UsuarioDao;

public class TelaTeste extends AppCompatActivity {

    private LinearLayout layoutMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Layout principal
        this.layoutMain = new LinearLayout(this);
        this.layoutMain.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.layoutMain.setOrientation(LinearLayout.VERTICAL);
        this.layoutMain.setPadding(0,0,0,0);
        this.layoutMain.setGravity(Gravity.CENTER_VERTICAL);
        setContentView(layoutMain);

//        NoticiaDao noticiaDao = new NoticiaDao(this);
//        Log.d("APP_DEBUG", "NOVA NOTICIA: "+noticiaDao.salvar(new Noticia(
//                "3948324932",
//                "www.tste.com",
//                "TItulo",
//                "www.noticia.link.com",
//                "21/08/2019",
//                "Descricao da noTicia",
//                "Conteudo noticia",
//                null,
//                "URL noticia")));

    }
}
