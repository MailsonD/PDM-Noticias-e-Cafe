package com.ifpb.noticia_e_cafe.rss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TesteRss extends AppCompatActivity {

//    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> links = new ArrayList<>();
//    ArrayList<Spanned> conteudos = new ArrayList<>();
    ArrayList<Noticia> noticias = new ArrayList<>();
    LinearLayout layoutMain;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        textView = new TextView(this);
        listView = new ListView(this);

        layoutMain = new LinearLayout(this);
        layoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        );
        layoutMain.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutMain.setPadding(0,0,0,0);
        layoutMain.setOrientation(LinearLayout.VERTICAL);

//        listView = new ListView(this);

        layoutMain.addView(listView);
//        layoutMain.addView(textView);

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

        private final Pattern PATTERN = Pattern.compile("<img .*\\/>");
        private final Pattern PATTERN_SRC = Pattern.compile("http.+\\.[a-z]*\"");
        ProgressDialog progressDialog = new ProgressDialog(TesteRss.this);

        public Drawable gerarDrawable(String urlString) {
            try {
                InputStream is = getInputStream(new URL(urlString));
                Bitmap x = BitmapFactory.decodeStream(is);
                return new BitmapDrawable(Resources.getSystem(), x);
            } catch (Exception e) {
                return null;
            }
        }

        public String encontraImg(String texto) {
            Matcher matcher = PATTERN.matcher(texto);
            if(matcher.find()){
                String img = matcher.group(0);

//                Log.d("SAPORRA","Encontro/ img: " + img);
                Matcher matcheSrc = PATTERN_SRC.matcher(img);
                if(matcheSrc.find()){
                    String src = matcheSrc.group(0);
                    src = src.substring(0,src.length()-1);
//                    Log.d("SAPORRA","Encontrou o src: " + src);
                    return src;
                }

            } else {
                Log.d("SAPORRA","Não encontrou.");

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog.setMessage("Carregando...Tenha paciência meu amigo!");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            String html = null;
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
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.PUBLICACAO.value())){
                            if (insideItem) {
                                noticia.setDataPublicacao(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.CONTEUDO.value())){
                            if (insideItem) {
                                html = xpp.nextText();
                                noticia.setImg(gerarDrawable(encontraImg(html)));
                                noticia.setConteudo(Html.fromHtml(html).toString());
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.DESCRICAO.value())){
                            if (insideItem) {
                                noticia.setDecricao(Html.fromHtml(xpp.nextText()).toString());
                            }
                        }else if (xpp.getName().equalsIgnoreCase(Item.LINK.value())){
                            if (insideItem) {
                                noticia.setLink(xpp.nextText());
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
            return html;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayAdapter<Noticia> adapter = new ArrayAdapter<Noticia>(TesteRss.this,
                    android.R.layout.simple_list_item_1,
                    noticias
            );


            listView.setAdapter(adapter);

//            URLImageParser p = new URLImageParser(textView,TesteRss.this);

//            Spanned testeHtml = Html.fromHtml(s, p, null);
//            textView.setText(testeHtml);

            progressDialog.dismiss();
        }
    }

    private class Noticia{

        private String titulo;
        private String link;
        private String dataPublicacao;
        private String decricao;
        private String conteudo;
        private Drawable img;

        public Noticia(String titulo, String link, String dataPublicacao, String decricao, String conteudo) {
            this.titulo = titulo;
            this.link = link;
            this.dataPublicacao = dataPublicacao;
            this.decricao = decricao;
            this.conteudo = conteudo;
        }

        public Noticia(String titulo, String link, String dataPublicacao, String decricao, String conteudo, Drawable img) {
            this.titulo = titulo;
            this.link = link;
            this.dataPublicacao = dataPublicacao;
            this.decricao = decricao;
            this.conteudo = conteudo;
            this.img = img;
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

        public Drawable getImg() {
            return img;
        }

        public void setImg(Drawable img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "Noticia{" +
                    "titulo='" + titulo + '\'' +
                    ", link='" + link + '\'' +
                    ", dataPublicacao='" + dataPublicacao + '\'' +
                    ", decricao='" + decricao + '\'' +
                    ", conteudo='" + conteudo + '\'' +
                    ", img='" + img + '\'' +
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

    public class URLDrawable extends BitmapDrawable {
        // the drawable that you need to set, you could set the initial drawing
        // with the loading image if you need to
        protected Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if(drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    public class URLImageParser implements Html.ImageGetter {
        Context c;
        View container;

        /***
         * Construct the URLImageParser which will execute AsyncTask and refresh the container
         * @param t
         * @param c
         */
        public URLImageParser(View t, Context c) {
            this.c = c;
            this.container = t;
        }

        public Drawable getDrawable(String source) {
            URLDrawable urlDrawable = new URLDrawable();

            // get the actual source
            ImageGetterAsyncTask asyncTask =
                    new ImageGetterAsyncTask( urlDrawable);

            asyncTask.execute(source);

            // return reference to URLDrawable where I will change with actual image from
            // the src tag
            return urlDrawable;
        }

        public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable>  {
            URLDrawable urlDrawable;

            public ImageGetterAsyncTask(URLDrawable d) {
                this.urlDrawable = d;
            }

            @Override
            protected Drawable doInBackground(String... params) {
                String source = params[0];
                return fetchDrawable(source);
            }

            @Override
            protected void onPostExecute(Drawable result) {
                // set the correct bound according to the result from HTTP call
                urlDrawable.setBounds(0, 0, result.getIntrinsicWidth(), result.getIntrinsicHeight());

                // change the reference of the current drawable to the result
                // from the HTTP call
                urlDrawable.drawable = result;

                // redraw the image by invalidating the container
                URLImageParser.this.container.invalidate();
            }

            /***
             * Get the Drawable from URL
             * @param urlString
             * @return
             */
            public Drawable fetchDrawable(String urlString) {
                try {
                    InputStream is = getInputStream(new URL(urlString));
                    Drawable drawable = Drawable.createFromStream(is, "src");
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    return drawable;
                } catch (Exception e) {
                    return null;
                }
            }

        }
    }
}
