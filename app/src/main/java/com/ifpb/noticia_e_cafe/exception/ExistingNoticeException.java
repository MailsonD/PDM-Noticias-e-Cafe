package com.ifpb.noticia_e_cafe.exception;

public class ExistingNoticeException extends Exception {
    public ExistingNoticeException(){
        super("Notícia já cadastrada no banco de dados");
    }
}
