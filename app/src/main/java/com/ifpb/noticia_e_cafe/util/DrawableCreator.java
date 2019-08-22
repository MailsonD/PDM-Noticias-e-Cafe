package com.ifpb.noticia_e_cafe.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.ifpb.noticia_e_cafe.exception.DrawableCreatorExcpetion;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 */
public class DrawableCreator {

    /**
     * Método para gerar um recurso Drawable de uma imagem através de uma URL
     * @param url -> Url para a qual se deseja gerar o drawable
     * @return Drawable -> O recurso drawable criado da imagem
     * @throws DrawableCreatorExcpetion caso ocorra algum problema no processo de decodeStream ou de abrir a conexão
     */
    public static Drawable gerarDrawable(URL url) throws DrawableCreatorExcpetion {
        try {
            Log.i("APP_INFO","Gerando drawable para url: "+url.getPath());
            InputStream is = CreateConnectionUrl.getInputStream(url);
            Bitmap x = BitmapFactory.decodeStream(is);
            return new BitmapDrawable(Resources.getSystem(), x);
        } catch (Exception e) {
            Log.e("APP_ERROR","Houve um erro ao gerar o drawable pela URL enviada");
            Log.e("APP_ERRO","Classe: "+DrawableCreator.class.getName());
            Log.e("APP_ERROR",e.getMessage());
            throw new DrawableCreatorExcpetion("Houve uma falha ao conectar com a url desejada");
        }
    }




}
