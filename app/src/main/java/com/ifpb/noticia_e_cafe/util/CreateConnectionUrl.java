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
     * public static InputStream -> Esta função receberá uma URL e a partir dela irá abrir uma conexão em InputStream para a URL enviada
     * @param url -> Url para a qual se deseja abrir um InputStream
     * @return InputStream ->
     * @throws ConnectionUrlException
     */
    public static InputStream getInputStream(URL url) throws ConnectionUrlException {
        try {
            return url.openConnection().getInputStream();
        } catch (Exception e) {
            Log.d("ERRO","Houver um erro de conexão com a url: "+url.getPath());
            throw new ConnectionUrlException("Houve uma falha ao conectar com a url desejada");
        }
    }

    public static class ConnectionUrlException extends Exception{

        public ConnectionUrlException(String msg){
            super(msg);
        }

    }

}
