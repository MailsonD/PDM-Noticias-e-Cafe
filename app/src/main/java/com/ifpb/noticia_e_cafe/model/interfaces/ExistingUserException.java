package com.ifpb.noticia_e_cafe.model.interfaces;

public class ExistingUserException extends Exception {
    public ExistingUserException(){
        super("Usuário já existente no bacon de dados!");
    }
}
