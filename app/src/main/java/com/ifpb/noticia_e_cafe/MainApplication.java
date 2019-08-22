package com.ifpb.noticia_e_cafe;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.ifpb.noticia_e_cafe.rss.reciver.RssReceiver;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("APP_DEBUG","Iniciando aplicação");
//        sendBroadcast(new Intent(this, RssReceiver.class));
//        startService(new Intent(this, RssReceiver.class));

    }

}
