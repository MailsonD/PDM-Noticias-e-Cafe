package com.ifpb.noticia_e_cafe.rss;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ifpb.noticia_e_cafe.component.adapter.NoticiaAdapter;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;
import com.ifpb.noticia_e_cafe.rss.consumer.ConsumerExcpetion;
import com.ifpb.noticia_e_cafe.rss.consumer.RssConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TesteRss extends AppCompatActivity {

    List<Noticia> noticias = new ArrayList<>();
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

        ProgressDialog progressDialog = new ProgressDialog(TesteRss.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Carregando...Tenha paciência meu amigo!");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            try {
                TesteRss.this.noticias = RssConsumer.consume(RssConsumer.URL_UIRAUNANET);
            } catch (ConsumerExcpetion consumerExcpetion) {
                consumerExcpetion.printStackTrace();
                Log.d("APP_DEBUG",consumerExcpetion.getMessage());
            }
            return null;
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







}
