package com.ifpb.noticia_e_cafe.telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.ifpb.noticia_e_cafe.model.entities.Usuario;
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

        UsuarioDao userDao = new UsuarioDao(this);
        userDao.salvar(new Usuario("santos", "santos@gmail.com", "244123"));
//        try {
//            Log.d("LCS", userDao.buscarPorEmail("novoEmail").toString());
//        } catch (UsuarioNotFound usuarioNotFound) {
//            usuarioNotFound.printStackTrace();
//        }
//        Log.d("LCS", );
//        userDao.listar();
//        try {
//            Usuario santos = userDao.buscarPorEmail("santos@gmail.com");
//            santos.setEmail("novoEmail");
//            int update = userDao.editar(santos);
//            if(update > 0){
//                Log.d("LCS", "ATUALIZADO");
//            }else Log.d("LCS", "NAO ATUALIZADO");
//        } catch (UsuarioNotFound usuarioNotFound) {
//            usuarioNotFound.printStackTrace();
//        }
    }
}
