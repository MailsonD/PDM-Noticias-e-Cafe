package com.ifpb.noticia_e_cafe.exception;

public class UsuarioNotFound extends Exception {
    public UsuarioNotFound(){
        super("Usuário não encontrado");
    }
}
