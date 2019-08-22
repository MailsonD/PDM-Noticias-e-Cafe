package com.ifpb.noticia_e_cafe.telas;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        mainLayout = new LinearLayout(this);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );
        mainLayout.setLayoutParams(layoutParams);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        noticiaControl = new NoticiaControl(this);
        noticesComponent = new RefreshNoticesComponent(this);

        new BuscarNoticiasEmBackground().execute();

        noticesComponent.setOnRefreshListener(this::refreshNoticias);

        mainLayout.addView(noticesComponent);
        setDynamicContent(mainLayout);
    }



    private void buscarNoticias() {
        noticias = noticiaControl.listar();
    }

    private void refreshNoticias(){
        RefreshNoticiasEmBackground refreshing = new RefreshNoticiasEmBackground();
        refreshing.execute();
    }

    @Override
    public String getTitleNavBar() {
        return "Notícias";
    }

    private class BuscarNoticiasEmBackground extends AsyncTask<Integer,Void,Integer>{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(TelaPrincipal.this);

            Log.i("APP_INFO","Buscando notícias para a tela principal");
            progressDialog.setMessage("Carregando notícias!");
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            buscarNoticias();
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            noticesComponent.setNoticias(noticias);
            Log.i("APP_INFO","Encerrando busca de notícias para tela principal");
            progressDialog.dismiss();
            super.onPostExecute(integer);
        }
    }

    private class RefreshNoticiasEmBackground extends AsyncTask<Integer,Void,String>{

        @Override
        protected String doInBackground(Integer... integers) {
            Log.d("APP_DEBUG","ATUALIZANDO");
            buscarNoticias();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            noticesComponent.setNoticias(noticias);
            noticesComponent.setRefreshing(false);
        }
    }

}
