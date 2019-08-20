package com.ifpb.noticia_e_cafe.rss.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.ifpb.noticia_e_cafe.control.NoticiaControl;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;
import com.ifpb.noticia_e_cafe.exception.ConsumerExcpetion;
import com.ifpb.noticia_e_cafe.rss.consumer.RssConsumer;
import com.ifpb.noticia_e_cafe.rss.reciver.RssReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 *
 * @author Ian
 * @mail
 *
 * Esta classe tem representa o serviço que será executado em background para buscar as notícias do RSS
 * de cada site. As requisições parar o serviço não devem ser feitas diretamente para esta classe, e sim
 * para: RssReceiver.
 * @see RssReceiver
 */
public class ConsumeRssService extends Service {

    /**
     * Esta constante representa um minuto em milisegundos para ser utilizada no agendamento das próximas
     * chamadas do serviço
     */
    private final long MINUTE = 60000L;
    private BackgroundWorker bw;
    private NoticiaControl noticiaControl;

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
        noticiaControl = new NoticiaControl(this);
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

    /**
     * Esta classe serve para que possamos rodar cada tarefa solicitada ao serviço em uma thread
     * separada da thread principal.
     */
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

    /**
     * Método responsável por chamar a classe que irá realizar a requisição ao RSS
     * e tratar todas as notícias retornadas.
     * Este método deve ser executado sempre em uma thread, pois não se pode abrir
     * uma conexão com uma URL na thread principal.
     */
    private void realizarRequisicao() {
        List<Noticia> noticias = new ArrayList<>();
        try {
            noticias.addAll(getNoticiasFrom(RssConsumer.URL_RADARPB));
            noticias.addAll(getNoticiasFrom(RssConsumer.URL_UIRAUNANET));
            noticias.addAll(getNoticiasFrom(RssConsumer.URL_RADARSERTANEJO));

            salvarNoticias(noticias);
        } catch (ConsumerExcpetion consumerExcpetion) {
            Log.e("APP_ERROR",consumerExcpetion.getMessage());
            consumerExcpetion.printStackTrace();
        }
    }

    private void salvarNoticias(List<Noticia> noticias) {

        for(Noticia noticia: noticias){
            Log.i("APP_INFO","Salvando a noticia de id: "+noticia.getGuid());
            noticiaControl.salvar(noticia);
        }

    }

    private List<Noticia> getNoticiasFrom(String url) throws ConsumerExcpetion {
        return RssConsumer.consume(url);
    }


    /**
     * Método responsável por agendar a hora em que este serviço será executado novamente.
     * Desta maneira, não haverá uma thread ociosa no aplicativo até que ele precise realizar
     * novamente o processo de requisitar as notícias. O serviço só existirá enquanto estiver
     * realizando a requisição e seu processamento. Após isso o Android ficará responsável por
     * iniciar o serviço novamente no tempo agendado.
     */
    private void agendarProximoServico(){
        Intent intent = new Intent(this, RssReceiver.class);
        PendingIntent intencaoAgendada = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + MINUTE,intencaoAgendada);
        Log.d("APP_DEBUG","Agendando servico");
    }

}
