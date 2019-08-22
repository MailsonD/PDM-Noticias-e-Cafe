package com.ifpb.noticia_e_cafe.model.interfaces;

import java.util.List;

public interface Dao<T> {
    public Long salvar(T object) throws ExistingUserException;
    public int excluir(T object);
    public int editar(T object);
    public List<T> listar();
}
