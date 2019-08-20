package com.ifpb.noticia_e_cafe.model.interfaces.persistence;

/**
 * @author Leanderson Coelho
 * @date 20/08/2019
 *
 * Classe que provê o script para criação das tabelas
 */
public class CreationScript {

    public static String scriptOfInitializationUsuario = "CREATE TABLE "+ TABLES_PERSISTENCE.USUARIO.getTabela() +" ( "+
            UsuarioTable.ID.getColumnName()+" integer PRIMARY KEY AUTOINCREMENT, " +
            UsuarioTable.NOME.getColumnName()+" text, " +
            UsuarioTable.EMAIL.getColumnName()+" text UNIQUE, " +
            UsuarioTable.SENHA.getColumnName()+" text " +
            ")";

    public static String scriptOfInitializationNoticia = "CREATE TABLE "+ TABLES_PERSISTENCE.NOTICIA.getTabela() +" ( " +
            NoticiaTable.GUID.getColumnName()+" PRIMARY KEY," +
            NoticiaTable.SITE_FONTE.getColumnName()+" text NOT NULL," +
            NoticiaTable.TITULO.getColumnName()+" text NOT NULL," +
            NoticiaTable.LINK.getColumnName()+" text NOT NULL," +
            NoticiaTable.DATA_PUBLICACAO.getColumnName()+" text," +
            NoticiaTable.DESCRICAO.getColumnName()+" text," +
            NoticiaTable.CONTEUDO.getColumnName()+" text NOT NULL," +
            NoticiaTable.IMG.getColumnName()+" text )";
}
