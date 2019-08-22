package com.ifpb.noticia_e_cafe.rss.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ifpb.noticia_e_cafe.rss.service.ConsumeRssService;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 *
 * Esta classe tem como intuito servir de brodcast para receber todas as intenções de iniciar o
 * serviço de consumo do RSS e as encaminhar da maneira correta.
 * Ela também pode ser aplicada para iniciar o serviço quando determinadas ações do android
 * forem executadas como ao iniciar o celular, quando a bateria ficar fraca, etc...
 */
public class RssReceiver extends BroadcastReceiver {

    /**
     * Este método é o responsável por encaminhar a requisição corretamente para o serviço
     * de consumo.
     * @param context -> Referente ao contexto que deseja iniciar o serviço
     * @param intent - Referente a intenção agendada para iniciar o serviço
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("APP_DEBUG","Iniciando serviço");
        context.startService(new Intent(context, ConsumeRssService.class));
    }

}
