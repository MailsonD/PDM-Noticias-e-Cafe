package com.ifpb.noticia_e_cafe.model.interfaces.persistence;

/**
 * @author Leanderson Coelho
 * @date 20/08/2019
 */

/**
 * Campos da tabela Usuário
 */
public enum UsuarioTable {
    ID("_id"),
    NOME("nome"),
    EMAIL("email"),
    SENHA("senha");

    private String columnName;

    UsuarioTable(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
