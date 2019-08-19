package com.ifpb.noticia_e_cafe.rss.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.ifpb.noticia_e_cafe.entities.Noticia;
import com.ifpb.noticia_e_cafe.rss.consumer.ConsumerExcpetion;
import com.ifpb.noticia_e_cafe.rss.consumer.RssConsumer;
import com.ifpb.noticia_e_cafe.rss.reciver.RssReceiver;

import java.util.List;


public class ConsumeRssService extends Service {

    private final long MINUTE = 60000L;
    private final long TEN_SECONDS = 10000L;
    private BackgroundWorker bw;

    public ConsumeRssService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int id){
        Log.d("APP_DEBUG","A iniciar a thread");
        bw = new BackgroundWorker();
        bw.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    private class BackgroundWorker extends Thread{

        @Override
        public void run() {
            Log.d("APP_DEBUG", "Iniciando requisição");
            realizarRequisicao();
            Log.d("APP_DEBUG", "Finalizando requisição");
            agendarProximoServico();
            stopSelf();
        }
    }

    private void realizarRequisicao() {
        try {
            List<Noticia> noticias = RssConsumer.consume(RssConsumer.URL_UIRAUNANET);
            for(Noticia n: noticias){
                System.out.println(n.getGuid());
            }
        } catch (ConsumerExcpetion consumerExcpetion) {
            consumerExcpetion.printStackTrace();
        }
    }

    private void agendarProximoServico(){
        Intent intent = new Intent(this, RssReceiver.class);
        PendingIntent intencaoAgendada = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + MINUTE,intencaoAgendada);
        Log.d("APP_DEBUG","Agendando servico");
    }

}
