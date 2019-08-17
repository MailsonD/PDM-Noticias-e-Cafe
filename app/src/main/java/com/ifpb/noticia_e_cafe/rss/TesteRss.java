package com.ifpb.noticia_e_cafe.rss;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.component.NoticeComponent;
import com.ifpb.noticia_e_cafe.entities.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TesteRss extends AppCompatActivity {

    ArrayList<Noticia> noticias = new ArrayList<>();
    ArrayList<NoticeComponent> noticeComponents = new ArrayList<>();
    LinearLayout layoutMain;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listView = new ListView(this);

        layoutMain = new LinearLayout(this);
        layoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        );
        layoutMain.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutMain.setPadding(0,0,0,0);
        layoutMain.setOrientation(LinearLayout.VERTICAL);

        layoutMain.addView(listView);
//        layoutMain.addView(textView);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Uri uri = Uri.parse(links.get(position));
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                TesteRss.this.startActivity(intent);
//            }
//        });

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
//                        ImageView imageView = new ImageView(TesteRss.this);
//                        imageView.setImageDrawable(noticia.getImg());
//                        noticeComponents.add(
//                                new NoticeComponent(
//                                        TesteRss.this,
//                                        TesteRss.this.getWindowManager(),
//                                        noticia.getTitulo(),
//                                        noticia.getDecricao(),
//                                        noticia.getDataPublicacao(),
//                                        imageView
//                                )
//                        );
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

//            ArrayAdapter<Noticia> adapter = new ArrayAdapter<Noticia>(TesteRss.this,
//                    android.R.layout.simple_list_item_1,
//                    noticias
//            );
//            ArrayAdapter<NoticeComponent> adapter = new ArrayAdapter<NoticeComponent>(TesteRss.this,
//                    android.R.layout.simple_list_item_1,
//                    noticeComponents
//            );
            NoticiaAdapter adapter = new NoticiaAdapter(TesteRss.this,noticias);


            listView.setAdapter(adapter);


            progressDialog.dismiss();
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

    private class NoticiaAdapter extends BaseAdapter {

        private final List<Noticia> noticias;
        private final Activity act;

        public NoticiaAdapter(Activity act, List<Noticia> noticias) {
            Log.d("SAPORRA","Existems "+noticias.size()+" Noticias");
            this.noticias = noticias;
            this.act = act;
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
            Noticia noticia = noticias.get(i);
            Log.d("SAPORRA","Noticia "+i);
            ImageView imageView = new ImageView(act);
            imageView.setImageDrawable(noticia.getImg());
            return new NoticeComponent(act,noticia.getTitulo(),noticia.getDecricao(),noticia.getDataPublicacao(),imageView);
//            TextView textView = new TextView(act);
//            textView.setText(noticia.getConteudo());
//            return textView;
        }
    }


}
