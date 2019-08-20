package com.ifpb.noticia_e_cafe.model.interfaces.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Leanderson Coelho
 * @date 20/08/2019
 */

/**
 * Classe responsável por gerenciar o versionamento do banco de dados
 */
public class ManagerDataBase extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "noticiaEcafe.db";
    public static final int VERSAO = 1;

    public ManagerDataBase(Context context){
        super(context,NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreationScript.scriptOfInitialization);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLES_PERSISTENCE.USUARIO.getTabela());
        onCreate(db);
    }
}
