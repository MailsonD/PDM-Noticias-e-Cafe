package com.ifpb.noticia_e_cafe.model.interfaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ifpb.noticia_e_cafe.exception.ExistingNoticeException;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.ManagerDataBase;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.NoticiaTable;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.TABLES_PERSISTENCE;
import com.ifpb.noticia_e_cafe.util.DrawableCreator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leanderson Coelho
 * @email leanderson.coelhoif@gmail.com
 * @date 21/08/2019
 * <p>
 * Classe responsável por fazer a persistência dos dados referentes a notícia
 */
public class NoticiaDao implements Dao<Noticia> {

    private SQLiteDatabase db;
    private ManagerDataBase managerDataBase;

    public NoticiaDao(Context context) {
        managerDataBase = new ManagerDataBase(context);
    }

    /**
     * Método responsável por persistir uma nova notícia.
     *
     * @param object Nova notícia que será persistida.
     * @return <b>ID</b> da nova notícia. <STRONG>Não é o mesmo valor da coluna GUID</STRONG>
     */
    @Override
    public Long salvar(Noticia object) throws ExistingNoticeException {
        if (this.buscarPorGuid(object.getGuid()) == null) {
            Log.i("APP_INFO", "SALVANDO NOTÍCIA: " + object.getTitulo());
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
        } else {
            throw new ExistingNoticeException();
        }

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
        List<Noticia> noticias = new ArrayList<>();

        Log.i("APP_INFO", "CONEXAO COM BANCO");
        db = managerDataBase.getWritableDatabase();
        Cursor cursor = db.query(TABLES_PERSISTENCE.NOTICIA.getTabela(), null, null, null, null, null, null, "10");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            noticias.add(fillNoticia(cursor));
            cursor.moveToNext();
        }
        Log.i("APP_INFO", "FECHANDO CONEXAO");
        cursor.close();
        db.close();
        Log.i("APP_INFO", "BUSCANDO TODAS AS NOTÍCIAS");
        return noticias;
    }

    private Noticia buscarPorGuid(String guid) {
        String[] args = new String[]{
                guid
        };
        String where = new String("guid = ?");
        Log.i("APP_INFO", "CONEXAO COM O BANCO");
        db = managerDataBase.getWritableDatabase();
        Cursor cursor = db.query(TABLES_PERSISTENCE.NOTICIA.getTabela(), null, where, args, null, null, null);

        if (cursor.moveToFirst()){
            db.close();
            Log.i("APP_INFO", "FECHANDO CONEXAO COM O BANCO");
            return this.fillNoticia(cursor);
        }else{
            db.close();
            Log.i("APP_INFO", "FECHANDO CONEXAO COM O BANCO");
            return null;
        }
    }

    private Noticia fillNoticia(Cursor cursor) {
        Noticia noticia = new Noticia(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(5),
                cursor.getString(6),
                null,
                cursor.getString(7)
        );
        try {
            noticia.setImg(DrawableCreator.gerarDrawable(new URL(noticia.getUrlImg())));
        } catch (Exception e) {
            Log.e("APP_ERROR", "Não foi possível gerar a imagem para a notícia: " + noticia.getGuid());
        }
        return noticia;
    }
}
