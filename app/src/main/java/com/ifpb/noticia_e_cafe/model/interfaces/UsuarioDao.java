package com.ifpb.noticia_e_cafe.model.interfaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ifpb.noticia_e_cafe.exception.UsuarioNotFound;
import com.ifpb.noticia_e_cafe.model.entities.Usuario;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.ManagerDataBase;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.TABLES_PERSISTENCE;
import com.ifpb.noticia_e_cafe.model.interfaces.persistence.UsuarioTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leanderson Coelho
 * @date 20/08/2019
 * Classe responsável por fazer o acesso aos dados dos usuários armazenados no banco de dados
 */
public class UsuarioDao implements Dao<Usuario> {

    private SQLiteDatabase db;
    private ManagerDataBase managerDataBase;

    public UsuarioDao(Context context){
        managerDataBase = new ManagerDataBase(context);
    }

    /**
     * Adiciona um novo usuário ao banco de dados.
     * @param object Novo usuário que será adicionado
     * @return ID do novo Usuário.
     */
    @Override
    public Long salvar(Usuario object) {
        Log.i("APP_INFO", "CONEXAO COM BANCO");
        //variavel que guarda os campos da nova tupla
        ContentValues valores = new ContentValues();
        //"conexao" com banco
        db = managerDataBase.getWritableDatabase();
        valores.put(UsuarioTable.NOME.getColumnName(), object.getNome());
        valores.put(UsuarioTable.EMAIL.getColumnName(), object.getEmail());
        valores.put(UsuarioTable.SENHA.getColumnName(), object.getSenha());
        Log.i("APP_INFO", "INSERINDO DADOS");
        Long id = db.insert(TABLES_PERSISTENCE.USUARIO.getTabela(), null, valores);
        //fechando "conexao"
        db.close();
        Log.i("APP_INFO", id.toString());
        //retornando o id do novo elemento
        return id;
    }

    /**
     * {@link UnsupportedOperationException}
     */
    @Override
    public int excluir(Usuario object) {
        // TODO: 20/08/19
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Método responsável por atualizar os dados do usuário já cadastrado.
     * Somente os dados como [EMAIL, NOME, SENHA] poderão ser atualizados.
     * @param object Usuário que será atualizado
     * @return Quantidade de linhas afetadas.
     */
    @Override
    public int editar(Usuario object) {
        //novos valores para o usuario
        ContentValues valores = new ContentValues();
        valores.put(UsuarioTable.NOME.getColumnName(), object.getNome());
        valores.put(UsuarioTable.EMAIL.getColumnName(), object.getEmail());
        valores.put(UsuarioTable.SENHA.getColumnName(), object.getSenha());

        //Argumentos usados na consulta
        String[] argumentos = new String[]{
                object.getId()
        };

        //SELEÇÃO da consulta cláusula WHERE
        String where = new String(UsuarioTable.ID.getColumnName()+" = ?");

        Log.i("APP_INFO", "CONEXAO COM BANCO");        db = managerDataBase.getWritableDatabase();
//        Cursor cursor = db.query(TABLES_PERSISTENCE.USUARIO.getTabela(), null, where, argumentos, null, null, null);
        int update = db.update(TABLES_PERSISTENCE.USUARIO.getTabela(), valores, where, argumentos);
        Log.i("APP_INFO", "FECHANDO CONEXAO");
        db.close();
        return update;
    }

    /**
     * Método responsável por listar todos os usuários cadastrados
     * @return Usuários cadastrados no sistema
     */
    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        Log.i("APP_INFO", "CONEXAO COM BANCO");        db = managerDataBase.getWritableDatabase();
        Cursor cursor = db.query(TABLES_PERSISTENCE.USUARIO.getTabela(), null, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            usuarios.add(fillUser(cursor));
            cursor.moveToNext();
        }
        Log.i("APP_INFO", "FECHANDO CONEXAO");
        cursor.close();
        db.close();
        Log.i("APP_INFO", "BUSCANDO TODOS OS USUÁRIOS");
        return usuarios;
    }

    /**
     * Método responsável por buscar um usuário no banco de dados a partir do email
     * @param email Usado para conferir se é o usuário buscado
     * @return Um usuário, caso seja encontrado, caso não seja encontrado é lançado uma exceção {@link UsuarioNotFound}
     * @throws UsuarioNotFound
     */
    public Usuario buscarPorEmail(String email) throws UsuarioNotFound {
        //codigo referente a cláusula 'SELECT' do sql
        String[] columns = new String[]{
                UsuarioTable.ID.getColumnName(),
                UsuarioTable.NOME.getColumnName(),
                UsuarioTable.EMAIL.getColumnName(),
                UsuarioTable.SENHA.getColumnName(),
        };

        //Argumentos usados na consulta
        String[] argumentos = new String[]{
            email
        };

        //SELEÇÃO da consulta cláusula WHERE
        String where = new String("email = ?");

        Log.i("APP_INFO", "CONEXAO COM BANCO");        db = managerDataBase.getWritableDatabase();
        Cursor cursor = db.query(TABLES_PERSISTENCE.USUARIO.getTabela(), columns, where, argumentos, null, null, null);

        if(cursor.moveToFirst()){
            Log.i("APP_INFO", "BUSCA: USUÁRIO ENCONTRADO");
            Usuario usuario = fillUser(cursor);
            cursor.close();
            db.close();
            return usuario;
        }else{
            Log.i("APP_INFO", "BUSCA: USUÁRIO NÃO ENCONTRADO");
            cursor.close();
            db.close();
            throw new UsuarioNotFound();
        }
    }

    /**
     * Instância um novo usuário a partir da busca do mesmo pelo email
     * @param cursor Obtido na função de busca do usuário
     * @return Usuário que tem o mesmo email passado na busca.
     */
    private Usuario fillUser(Cursor cursor) {
        return new Usuario(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
    }
}
