package com.ifpb.noticia_e_cafe.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 */
public class CreateConnectionUrl {

    /**
     * Método que receberá uma URL e a partir dela irá abrir uma conexão em InputStream com a URL enviada
     * @param url -> Url para a qual se deseja abrir um InputStream
     * @return InputStream -> O InputStream aberto através da URL enviada
     * @throws ConnectionUrlException caso ocorra algum problema na abertura da conexão com a URL
     */
    public static InputStream getInputStream(URL url) throws ConnectionUrlException {
        try {
            Log.i("APP_INFO","Abrindo uma conxão com a url: "+url.getPath());
            return url.openConnection().getInputStream();
        } catch (Exception e) {
            Log.d("APP_ERRO","Houver um erro de conexão com a url: "+url.toString());
            Log.e("APP_ERRO","Classe: "+CreateConnectionUrl.class.getName());
            e.printStackTrace();
            throw new ConnectionUrlException("Houve uma falha ao conectar com a url desejada");
        }
    }

    public static class ConnectionUrlException extends Exception{

        public ConnectionUrlException(String msg){
            super(msg);
        }

    }

}
