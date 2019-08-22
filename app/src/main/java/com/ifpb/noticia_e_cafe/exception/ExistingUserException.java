package com.ifpb.noticia_e_cafe.exception;

public class ExistingUserException extends Exception {
    public ExistingUserException(){
        super("Usuário já existente no bacon de dados!");
    }
}
