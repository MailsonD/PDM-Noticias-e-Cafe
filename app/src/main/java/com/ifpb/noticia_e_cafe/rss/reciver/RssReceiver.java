package com.ifpb.noticia_e_cafe.rss.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ifpb.noticia_e_cafe.rss.service.ConsumeRssService;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 */
public class RssReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, ConsumeRssService.class));
    }

}
