package com.ifpb.noticia_e_cafe.rss;

import android.app.IntentService;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;



public class MyIntentService extends Service {
    private Handler handler;
    public MyIntentService() {
        super();
        handler = new Handler();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        BackgroundWorker bw = new BackgroundWorker(2000);
        bw.run();
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int id){
//        return onStartCommand(intent, flags, id);
//    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stopSelf();
    }

    private class BackgroundWorker extends Thread{
        private int tempo;
        int count=0;
        public BackgroundWorker(int tempo){
            this.tempo = tempo;
        }

        @Override
        public void run() {
            Log.d("Atualizando contador...", ++count+"");
            handler.postDelayed(this, tempo);
        }
    }
}
