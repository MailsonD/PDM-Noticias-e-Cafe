package com.ifpb.noticia_e_cafe.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class TelaNoticia extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout linearLayout;
    @Override
    public void onCreate(Bundle salvedInstanceState){
        super.onCreate(salvedInstanceState);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "Principal");
        menu.add(Menu.NONE, 2, Menu.NONE, "Editar Cadastro");
        menu.add(Menu.NONE, 3, Menu.NONE, "Logout");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(this,TelaPrincipal.class));
                return true;
            case 2:
                startActivity(new Intent(this,TelaEditar.class));
                return true;
            case 3:
                startActivity(new Intent(this,TelaLogin.class));
                return true;
            default:
                return false;
        }
    }
}
