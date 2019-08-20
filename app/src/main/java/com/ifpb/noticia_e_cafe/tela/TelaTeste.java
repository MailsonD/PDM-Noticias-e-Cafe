package com.ifpb.noticia_e_cafe.tela;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ifpb.noticia_e_cafe.R;
import com.ifpb.noticia_e_cafe.component.NoticeComponent;
import com.ifpb.noticia_e_cafe.exception.UsuarioNotFound;
import com.ifpb.noticia_e_cafe.model.Usuario;
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

        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.logo);

        ImageView img1 = new ImageView(this);
        img1.setImageResource(R.drawable.logo);

        NoticeComponent noticia = new NoticeComponent(this,"Meu titulo","Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Quisque vehicula ex mauris, non feugiat felis vestibulum quis. Nunc vitae elit vitae lorem condimentum efficitur non id nunc. ","10/04/2010", img1);
        NoticeComponent noticia1 = new NoticeComponent(this,"Meu titulo","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vehicula ex mauris," +
                " non feugiat felis vestibulum quis. Nunc vitae elit vitae lorem condimentum efficitur non id nunc. ","10/04/2010", img);

        layoutMain.addView(noticia);
        layoutMain.addView(noticia1);

        UsuarioDao userDao = new UsuarioDao(this);
//        userDao.salvar(new Usuario("santos", "santos@gmail.com", "244123"));
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
