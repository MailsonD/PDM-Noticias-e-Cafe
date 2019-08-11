package com.ifpb.noticia_e_cafe.rss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class TesteRss extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> links = new ArrayList<>();
    ArrayList<Noticia> noticias = new ArrayList<>();
    LinearLayout layoutMain;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutMain = new LinearLayout(this);
        layoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        );
        layoutMain.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutMain.setPadding(0,0,0,0);
        layoutMain.setOrientation(LinearLayout.VERTICAL);

        listView = new ListView(this);

        layoutMain.addView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(links.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                TesteRss.this.startActivity(intent);
            }
        });

        new ProcessaEmBackground().execute();

        setContentView(layoutMain);
    }

    private InputStream getInputStream(URL url){
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public class ProcessaEmBackground extends AsyncTask<Integer,Void,String>{

        ProgressDialog progressDialog = new ProgressDialog(TesteRss.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog.setMessage("Carregando...Tenha paciência meu amigo!");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            try{
                URL url = new URL("http://uirauna.net/feed/");

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;

                int eventType = xpp.getEventType();

                Noticia noticia= new Noticia();
                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        if(xpp.getName().equalsIgnoreCase(Item.ABERTURA.value())){
                            noticia = new Noticia();
                            insideItem = true;
                        }else if (xpp.getName().equalsIgnoreCase(Item.TITULO.value())){
                            if (insideItem){
                                noticia.setTitulo(xpp.nextText());
//                                titles.add(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.PUBLICACAO.value())){
                            if (insideItem) {
                                noticia.setDataPublicacao(xpp.nextText());
//                                links.add(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.CONTEUDO.value())){
                            if (insideItem) {
                                noticia.setConteudo(Html.fromHtml(xpp.nextText()).toString());
//                                links.add(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.DESCRICAO.value())){
                            if (insideItem) {
                                noticia.setDecricao(Html.fromHtml(xpp.nextText()).toString());
//                                links.add(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.LINK.value())){
                            if (insideItem) {
                                noticia.setLink(xpp.nextText());
//                                links.add(xpp.nextText());
                            }
                        }

                    }else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase(Item.ABERTURA.value())){
                        insideItem = false;
                        noticias.add(noticia);
                    }
                    eventType = xpp.next();
                }


            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayAdapter<Noticia> adapter = new ArrayAdapter<Noticia>(TesteRss.this,
                    android.R.layout.simple_list_item_1,
                    noticias
            );

            for (String title : titles) {
                Log.d("Testando Saporra", title);
            }

            listView.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }

    private class Noticia{

        private String titulo;
        private String link;
        private String dataPublicacao;
        private String decricao;
        private String conteudo;

        public Noticia(String titulo, String link, String dataPublicacao, String decricao, String conteudo) {
            this.titulo = titulo;
            this.link = link;
            this.dataPublicacao = dataPublicacao;
            this.decricao = decricao;
            this.conteudo = conteudo;
        }

        public Noticia() {

        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDataPublicacao() {
            return dataPublicacao;
        }

        public void setDataPublicacao(String dataPublicacao) {
            this.dataPublicacao = dataPublicacao;
        }

        public String getDecricao() {
            return decricao;
        }

        public void setDecricao(String decricao) {
            this.decricao = decricao;
        }

        public String getConteudo() {
            return conteudo;
        }

        public void setConteudo(String conteudo) {
            this.conteudo = conteudo;
        }

        @Override
        public String toString() {
            return "Noticia{" +
                    "titulo='" + titulo + '\'' +
                    ", link='" + link + '\'' +
                    ", dataPublicacao='" + dataPublicacao + '\'' +
                    ", decricao='" + decricao + '\'' +
                    ", conteudo='" + conteudo + '\'' +
                    '}';
        }
    }

    private enum Item{
        ABERTURA("item"),
        TITULO("title"),
        LINK("link"),
        PUBLICACAO("pubdate"),
        DESCRICAO("description"),
        CONTEUDO("content:encoded")
        ;


        private final String value;

        Item(String value){
            this.value = value;
        }

        public String value(){
            return this.value;
        }

    }
}
