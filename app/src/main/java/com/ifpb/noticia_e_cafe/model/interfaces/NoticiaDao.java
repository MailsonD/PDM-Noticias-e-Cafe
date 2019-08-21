package com.ifpb.noticia_e_cafe.model.interfaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.ifpb.noticia_e_cafe.model.entities.Noticia;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.ManagerDataBase;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.NoticiaTable;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.TABLES_PERSISTENCE;

import java.util.List;

/**
 * @author Leanderson Coelho
 * @email leanderson.coelhoif@gmail.com
 * @date 21/08/2019
 *
 * Classe responsável por fazer a persistência dos dados referentes a notícia
 */
public class NoticiaDao implements Dao<Noticia> {

    private SQLiteDatabase db;
    private ManagerDataBase managerDataBase;

    public NoticiaDao(Context context){
        managerDataBase = new ManagerDataBase(context);
    }

    /**
     * Método responsável por persistir uma nova notícia.
     * @param object Nova notícia que será persistida.
     * @return <b>ID</b> da nova notícia. <STRONG>Não é o mesmo valor da coluna GUID</STRONG>
     */
    @Override
    public Long salvar(Noticia object) {
        Log.i("APP_INFO", "SALVANDO NOTÍCIA: "+object.getTitulo());
        //variavel que guarda os dados da noticia para insert
        ContentValues valores = new ContentValues();
        Log.i("APP_INFO", "INICIANDO CONEXAO COM DATABASE");
        db = managerDataBase.getWritableDatabase();
        valores.put(NoticiaTable.GUID.getColumnName(), object.getGuid());
        valores.put(NoticiaTable.SITE_FONTE.getColumnName(), object.getSiteFonte());
        valores.put(NoticiaTable.TITULO.getColumnName(), object.getTitulo());
        valores.put(NoticiaTable.LINK.getColumnName(), object.getLink());
        valores.put(NoticiaTable.DATA_PUBLICACAO.getColumnName(), object.getDataPublicacao());
        valores.put(NoticiaTable.DESCRICAO.getColumnName(), object.getDecricao());
        valores.put(NoticiaTable.CONTEUDO.getColumnName(), object.getConteudo());
        valores.put(NoticiaTable.IMG.getColumnName(), object.getUrlImg());
        Long result = db.insert(TABLES_PERSISTENCE.NOTICIA.getTabela(), null, valores);
        Log.i("APP_INFO", "FECHANDO CONEXAO COM DATABASE");
        db.close();
        return result;
    }

    @Override
    public int excluir(Noticia object) {
        throw new UnsupportedOperationException("Operation not suported!");
    }

    @Override
    public int editar(Noticia object) {
        throw new UnsupportedOperationException("Operation not suported!");
    }

    @Override
    public List<Noticia> listar() {
        return null;
    }
}
