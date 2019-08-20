package com.ifpb.noticia_e_cafe.model.interfaces.persistence;

/**
 * @author Leanderson Coelho
 * @date 20/08/2019
 */

/**
 * Tabelas existentes no banco de dados
 */
public enum TABLES_PERSISTENCE {
    NOTICIA("noticia"),
    USUARIO("usuario");

    private String tabela;

    TABLES_PERSISTENCE(String valor) {
        this.tabela = valor;
    }

    public String getTabela(){
        return this.tabela;
    }
}
