package com.ifpb.noticia_e_cafe.telas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;
import com.ifpb.noticia_e_cafe.control.UserControl;
import com.ifpb.noticia_e_cafe.model.Usuario;
import com.ifpb.noticia_e_cafe.model.interfaces.UsuarioDao;

public class TelaCadastro extends AppCompatActivity {
    private LinearLayout linearLayoutButtons;
    private LinearLayout linearLayoutImputs;
    private InputField nome;
    private InputField email;
    private InputField senha;
    private ButtonComponent cancelar;
    private ButtonComponent cadastrar;
    private TextView titulo;
    private LinearLayout linearLayout;
    @Override
    public void onCreate(Bundle salvedInstanceState){
        super.onCreate(salvedInstanceState);

        linearLayoutButtons= new LinearLayout(this);
        linearLayoutButtons.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayoutButtons.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutButtons.setPadding(9,9,9,9);
        linearLayoutButtons.setGravity(Gravity.CENTER_HORIZONTAL);

        linearLayoutImputs= new LinearLayout(this);
        linearLayoutImputs.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayoutImputs.setOrientation(LinearLayout.VERTICAL);
        linearLayoutImputs.setPadding(9,9,9,9);
        linearLayoutImputs.setGravity(Gravity.CENTER_HORIZONTAL);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        nome = new InputField(this,"Nome:", InputType.TYPE_CLASS_TEXT, width);
        linearLayoutImputs.addView(nome);

        email = new InputField(this,"Email:", InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS, width);
        linearLayoutImputs.addView(email);


        senha = new InputField(this,"Password:", InputType.TYPE_TEXT_VARIATION_PASSWORD, width);
        linearLayoutImputs.addView(senha);

        cancelar = new ButtonComponent(this, "Cancelar", width/3, Color.rgb(255,0,0));
        linearLayoutButtons.addView(cancelar);

        cadastrar = new ButtonComponent(this, "Cadastrar", width/3, Color.rgb(0,128,0));
        linearLayoutButtons.addView(cadastrar);

        cancelar.setOnClickAction((v) -> startActivity(new Intent(this,TelaLogin.class)));

        cadastrar.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nome.getValue().isEmpty()){
                    Toast.makeText(TelaCadastro.this, "Informe o seu nome!", Toast.LENGTH_SHORT).show();
                }else if(!(email.getValue().matches("^[\\w \\- \\. % +]{2,}@[a-z]{2,}(\\.[a-z]{2,})+$"))){
                    Toast.makeText(TelaCadastro.this, "Informe um email válido!", Toast.LENGTH_SHORT).show();
                }else if(senha.getValue().length()<8){
                    Toast.makeText(TelaCadastro.this, "Informe uma senha de no mínimo 8 caracteres.", Toast.LENGTH_SHORT).show();
                }else{
                    UserControl userControl = new UserControl(TelaCadastro.this);
                    Usuario usuario = new Usuario(nome.getValue(), email.getValue(), senha.getValue());
                    userControl.cadastrar(usuario);
                    startActivity(new Intent(TelaCadastro.this,TelaLogin.class));
                    Toast.makeText(TelaCadastro.this, "Cadastro Realizado!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        titulo = new TextView(this);
        titulo.setText("Cadastro\n\n");
        titulo.setTextSize(30f);
        titulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        linearLayout= new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(9,9,9,9);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        setContentView(linearLayout);

        linearLayout.addView(titulo);
        linearLayout.addView(linearLayoutImputs);
        linearLayout.addView(linearLayoutButtons);
    }
}
