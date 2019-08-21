package com.ifpb.noticia_e_cafe.telas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ifpb.noticia_e_cafe.R;
import com.ifpb.noticia_e_cafe.component.NewsDetailsComponent;
import com.ifpb.noticia_e_cafe.exception.DrawableCreatorExcpetion;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;
import com.ifpb.noticia_e_cafe.model.interfaces.NoticiaDao;
import com.ifpb.noticia_e_cafe.util.DrawableCreator;

import java.net.MalformedURLException;
import java.net.URL;

public class TelaDetalhesNoticia extends NavBar {


    private Noticia noticia;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        noticia = (Noticia) intent.getSerializableExtra("noticia");
        imageView = new ImageView(this);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        NewsDetailsComponent newsDetailsComponent = new NewsDetailsComponent(this, noticia.getTitulo(), noticia.getConteudo(), noticia.getDataPublicacao(), noticia.getSiteFonte(), imageView, getWindowManager());
        scrollView.addView(newsDetailsComponent);
        setDynamicContent(scrollView);

        new GetImageInBackground().execute();

    }

    public void getImage(){
        try {
            noticia.setImg(
                    DrawableCreator.
                            gerarDrawable(
                                    new URL(noticia.getUrlImg()
                                    )
                            )
            );
        } catch (Exception e){
            Log.e("APP_ERROR","Não foi possível pegar a imagem da notícia");
        }
    }

    private class GetImageInBackground extends AsyncTask<Integer,Void,String>{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(TelaDetalhesNoticia.this);

            progressDialog.setMessage("Carregando Noticia");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            getImage();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            imageView.setImageDrawable(noticia.getImg());
            progressDialog.dismiss();
            super.onPostExecute(s);
        }
    }
}
