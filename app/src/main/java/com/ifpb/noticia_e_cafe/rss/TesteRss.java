package com.ifpb.noticia_e_cafe.rss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        if(xpp.getName().equalsIgnoreCase("item")){
                            insideItem = true;
                        }else if (xpp.getName().equalsIgnoreCase("title")){
                            if (insideItem){
                                // extract the text between <title> and </title>
                                titles.add(xpp.nextText());
                            }
                        }else if (xpp.getName().equalsIgnoreCase("link")){
                            if (insideItem) {
                                // extract the text between <link> and </link>
                                links.add(xpp.nextText());
                            }
                        }

                    }else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                        insideItem = false;
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

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TesteRss.this, android.R.layout.simple_list_item_1, titles);

            for (String title : titles) {
                Log.d("Testando Saporra", title);
            }

            listView.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }
}
