package com.ifpb.noticia_e_cafe.model.interfaces.persistence;

/**
 * @author Leanderson Coelho
 * @date 20/08/2019
 *
 * Classe que provê o script para criação das tabelas
 */
public class CreationScript {

    public static String scriptOfInitialization = "CREATE TABLE "+ TABLES_PERSISTENCE.USUARIO.getTabela() +" ( "+
            UsuarioTable.ID.getColumnName()+" integer PRIMARY KEY AUTOINCREMENT, " +
            UsuarioTable.NOME.getColumnName()+" text, " +
            UsuarioTable.EMAIL.getColumnName()+" text UNIQUE, " +
            UsuarioTable.SENHA.getColumnName()+" text " +
            ")";
}
