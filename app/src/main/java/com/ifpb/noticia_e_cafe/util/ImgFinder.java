package com.ifpb.noticia_e_cafe.util;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 */
public class ImgFinder {

    private static final Pattern PATTERN_IMG = Pattern.compile("<img .*\\/>");
    private static final Pattern PATTERN_SRC = Pattern.compile("http.+\\.[a-z]*\"");

    /**
     * Método para procurarar uma imagem dentro do conteúdo da notícia
     * @param conteudo
     * @return uma String com uma url para a imagem caso seja encontrada
     * @return null caso não enctrone nenhuma imagem no conteúdo
     */
    public static String encontraImg(String conteudo) {
        Matcher matcher = PATTERN_IMG.matcher(conteudo);
        if(matcher.find()){
            String img = matcher.group(0);
            Matcher matcheSrc = PATTERN_SRC.matcher(img);
            if(matcheSrc.find()){
                String src = matcheSrc.group(0);
                src = src.substring(0,src.length()-1);
                Log.i("APP_INFO","A imagem foi enontrada com sucesso para o conteúdo enviado");
                return src;
            }
        }
        Log.i("APP_INFO","Não foi encontrada nenhuma imagem no conteúdo enviado");
        return null;
    }
}
