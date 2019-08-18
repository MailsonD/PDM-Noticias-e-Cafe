package com.ifpb.noticia_e_cafe;

import android.app.Application;
import android.content.Intent;

import com.ifpb.noticia_e_cafe.rss.reciver.RssReceiver;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, RssReceiver.class));

    }

}
