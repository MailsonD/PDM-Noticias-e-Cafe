package com.ifpb.noticia_e_cafe.model.interfaces.persistence;

/**
 * @author Leanderson Coelho
 * @email leanderson.coelhoif@gmail.com
 * @date 20/08/2019
 */
public enum NoticiaTable {
    GUID("guid"),
    SITE_FONTE("siteFonte"),
    TITULO("titulo"),
    LINK("link"),
    DATA_PUBLICACAO("dataPublicacao"),
    DESCRICAO("descricao"),
    CONTEUDO("conteudo"),
    IMG("img");

    private String columnName;

    NoticiaTable(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
