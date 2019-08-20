package com.ifpb.noticia_e_cafe.tela;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifpb.noticia_e_cafe.R;


//public class NavBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
public class NavBar extends Activity implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayout container;

    private ViewGroup header(String string){
        //
        TextView tv = new TextView(this);
        tv.setTextSize(15);
        tv.setTextColor(Color.rgb(66, 66, 65));
        tv.setText(string);
        tv.setPadding(10, 5, 5, 5);
        //
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);
        l.setGravity(Gravity.CENTER);
        //
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.ic_person_outline_black_36dp);
        //
        l.addView(icon, 0);
        l.addView(tv, 1);
        //
        return l;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //criando o layout e vinculando ao activity
        DrawerLayout drawer = new DrawerLayout(this);
        setContentView(drawer);

        //Container que conterá todos os componentes e layouts da tela
        container = new LinearLayout(this);
        container.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        container.setOrientation(LinearLayout.VERTICAL);

        //criando o Toolbar
        AppBarLayout bar = new AppBarLayout(this);
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle(getTitleNavBar());
        toolbar.setBackgroundColor(Color.rgb(154, 159, 157));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(30);
        }
        bar.addView(toolbar);
        container.addView(bar, new DrawerLayout.LayoutParams(                              //-------------------------------------------//
                DrawerLayout.LayoutParams.MATCH_PARENT,//comprimento
                DrawerLayout.LayoutParams.WRAP_CONTENT//altura
        ));
        drawer.addView(container);

        //Adicionando toggle com icone de menu (padrão do material design)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);//para sincronizar ao fechar                          ////////////////////////////////////////////////
        toggle.syncState();

        //criar os paramentros do layout para definir a gravidade
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
                DrawerLayout.LayoutParams.WRAP_CONTENT,//comprimento
                DrawerLayout.LayoutParams.MATCH_PARENT//altura
        );
        params.gravity = Gravity.LEFT;//informar de onde sai a bandeijaf
        //adicionar ao layout
        NavigationView navigationView = new NavigationView(this);
        navigationView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        drawer.addView(navigationView, 1, params);                                   //-------------------------------------------//

        navigationView.getMenu().add(0, 0, 0, "Minha Conta");//.setIcon(R.drawable.ic_profile);
        navigationView.getMenu().add(0, 1, 1, "Notícias");//.setIcon(R.drawable.ic_trash);
        navigationView.getMenu().add(1, 2, 2, "Logout");//.setIcon(R.drawable.ic_trash);
        //
        navigationView.setNavigationItemSelectedListener(this);
        //
        Toolbar toolbarNavBar = new Toolbar(this);
        toolbarNavBar.setBackgroundColor(Color.rgb(218, 220, 223));
        toolbarNavBar.addView(header("Ian Carneiro Teixeira de Araujo"));
        navigationView.addHeaderView(toolbarNavBar);
        //

    }

    public void setDynamicContent(View c){
//        container.removeViewAt(2);
        container.addView(c, 1);
    }

    public String getTitleNavBar(){
        return "NavBar";
    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        Toast.makeText(this, "Não feche essa bombas", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == 0){
            startActivity(new Intent(this,TelaEditar.class));
        } else if (menuItem.getItemId() == 1){
            Toast.makeText(this, "Selecionou o menu 2", Toast.LENGTH_LONG).show();
        } else if (menuItem.getItemId() == 2){
            startActivity(new Intent(this,TelaLogin.class));
        }
        return false;
    }
}