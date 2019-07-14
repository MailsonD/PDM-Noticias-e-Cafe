package com.ifpb.noticia_e_cafe.util;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DeviceProperties {

    /**
     * Método para calcular a largura da tela do dispositivo
     * @param windowManager - variavel usada para obter as informações da tela
     * user 'getWindowManager()' em uma tela para obter a variavel.
     * @return Retorna a largura da tela.
     * **/
    public static int getDeviceWidth(WindowManager windowManager){
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * Método para calcular a altura da tela do dispositivo
     * @param windowManager - variavel usada para obter as informações da tela
     * user 'getWindowManager()' em uma tela para obter a variavel.
     * @return Retorna a altura da tela.
     * **/
    public static int getDeviceHeight(WindowManager windowManager){
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }



}
