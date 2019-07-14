package com.ifpb.noticia_e_cafe.tela;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;
import com.ifpb.noticia_e_cafe.util.DeviceProperties;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout layoutMain;
    private LinearLayout layoutForm;
    private LinearLayout layoutCadastro;
    private InputField inputFieldEmail;
    private InputField inputFieldSenha;
    private ButtonComponent btnConfirmar;
    private TextView textoCadastro;
    private TextView linkCadastro;
    private int width;
    private int heigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //========== CONFIGURANDO LAYOUT PRINCIPAL =============
        layoutMain = new LinearLayout(this);
        layoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        );
        layoutMain.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutMain.setPadding(0,0,0,0);
        layoutMain.setOrientation(LinearLayout.VERTICAL);

        width = DeviceProperties.getDeviceWidth(getWindowManager());
        heigth = DeviceProperties.getDeviceHeight(getWindowManager());

        configurandoImagem();
        configurandoFormulario();
        configurandoTextoDeCadastro();


        //===== ADICIONANDO COMPONENTES AO LAYOUT PRINCIPAL =====
        setContentView(layoutMain);
        layoutMain.addView(layoutForm);
        layoutMain.addView(layoutCadastro);

    }





    private void configurandoImagem() {

    }

    private void configurandoFormulario() {
        //====== CONFIGURANDO LAYOUT DO FORMULARIO ========
        layoutForm = new LinearLayout(this);
        layoutForm.setOrientation(LinearLayout.VERTICAL);
        layoutForm.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutForm.setPadding(10,heigth/8,10,10);

        inputFieldEmail = new InputField(
                this,"Email", InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS,width
        );
        inputFieldSenha = new InputField(
                this,"Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD,width
        );
        btnConfirmar = new ButtonComponent(
                this,"Entrar", width/3, Color.GREEN
        );

        //=========== ADICIONANDO EVENTOS ONCLICK ==============

        btnConfirmar.setOnClickAction(
                (v) -> Log.d("DEBUGANDO_SAPORRA","MeuBotão")
        );

        //========= ADICIONANDO COMPONENTES AO LAYOUT =========

        layoutForm.addView(inputFieldEmail);
        layoutForm.addView(inputFieldSenha);
        layoutForm.addView(btnConfirmar);

    }

    private void configurandoTextoDeCadastro() {
        //======= CONFIGURANDO LAYOUT DO LINK DE CADASTRO ======
        layoutCadastro = new LinearLayout(this);
        layoutCadastro.setGravity(Gravity.CENTER_HORIZONTAL);
//        layoutCadastro.setVerticalGravity(Gravity.BOTTOM);
        layoutCadastro.setOrientation(LinearLayout.HORIZONTAL);
        layoutCadastro.setPadding(10,10,10,10);

        textoCadastro= new TextView(this);
        linkCadastro = new TextView(this);

        textoCadastro.setText("Não possui uma conta ainda?");
        linkCadastro.setText("Cadastre-se aqui!");
        linkCadastro.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        linkCadastro.setClickable(true);


        //=========== ADICIONANDO EVENTOS ONCLICK ==============
        linkCadastro.setOnClickListener(
                (v) -> startActivity(new Intent(this,TelaCadastro.class))
        );

        //========= ADICIONANDO COMPONENTES AO LAYOUT =========
        layoutCadastro.addView(textoCadastro);
        layoutCadastro.addView(linkCadastro);
    }



}
