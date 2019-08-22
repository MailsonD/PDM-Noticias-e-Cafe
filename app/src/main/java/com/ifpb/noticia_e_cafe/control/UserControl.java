package com.ifpb.noticia_e_cafe.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.ifpb.noticia_e_cafe.exception.UsuarioNotFound;
import com.ifpb.noticia_e_cafe.model.entities.Usuario;
import com.ifpb.noticia_e_cafe.model.interfaces.ExistingUserException;
import com.ifpb.noticia_e_cafe.model.interfaces.UsuarioDao;

public class UserControl {
    private UsuarioDao usuarioDao;
    private Context context;
    public UserControl(Context context) {
        this.context = context;
        usuarioDao = new UsuarioDao(context);
    }
    public Usuario login(Usuario u){
        try {
            Usuario usuario = usuarioDao.buscarPorEmailOrThrow(u.getEmail());
            if (usuario.getSenha().equals(u.getSenha())){
                SharedPreferences.Editor editor = context.getSharedPreferences("authenticatedUser", Context.MODE_PRIVATE).edit();
                editor.putBoolean("logado", true);
                editor.putString("nome", usuario.getNome());
                editor.putString("email", usuario.getEmail());
                editor.putString("senha", usuario.getSenha());
                editor.apply();
                return usuario;
            }
        } catch (UsuarioNotFound usuarioNotFound) {
            return null;
        }
        return null;
    }

    public void logout(){
        SharedPreferences mySPrefs = context.getSharedPreferences("authenticatedUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove("logado");
        editor.remove("nome");
        editor.remove("email");
        editor.remove("senha");
        editor.apply();
    }

    public Long cadastrar(Usuario u) throws ExistingUserException {
        Usuario usuario = new Usuario(u.getNome(), u.getEmail(), u.getSenha());
        return usuarioDao.salvar(usuario);
    }

}
