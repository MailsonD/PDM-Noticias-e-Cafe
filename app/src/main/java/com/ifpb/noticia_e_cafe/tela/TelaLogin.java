package com.ifpb.noticia_e_cafe.tela;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.R;
import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;
import com.ifpb.noticia_e_cafe.util.DeviceProperties;

public class TelaLogin extends AppCompatActivity {

    private LinearLayout layoutMain;
    private LinearLayout layoutImage;
    private LinearLayout layoutForm;
    private LinearLayout layoutCadastro;
    private InputField inputFieldEmail;
    private InputField inputFieldSenha;
    private ButtonComponent btnConfirmar;
    private TextView textoCadastro;
    private TextView linkCadastro;
    private ImageView imageView;
    private int width;
    private int heigth;

//    private Resources res;

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
        layoutMain.addView(layoutImage);
        layoutMain.addView(layoutForm);
        layoutMain.addView(layoutCadastro);

    }





    private void configurandoImagem() {
        //======== CONFIGURANDO LAYOUT DA IMAGEM =========
        layoutImage = new LinearLayout(this);
        layoutImage.setOrientation(LinearLayout.VERTICAL);
        layoutImage.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutImage.setPadding(0,25,0,0);
        layoutImage.setLayoutParams(new LinearLayout.LayoutParams(
                width/2, (int) ((heigth/2)*0.8))
        );

        imageView = new ImageView(this);
        imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.logo));

        layoutImage.addView(imageView);
    }

    private void configurandoFormulario() {
        //====== CONFIGURANDO LAYOUT DO FORMULARIO ========
        layoutForm = new LinearLayout(this);
        layoutForm.setOrientation(LinearLayout.VERTICAL);
        layoutForm.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutForm.setPadding(10,0,10,10);

        inputFieldEmail = new InputField(
                this,"Email", InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS,width
        );
        inputFieldSenha = new InputField(
                this,"Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD,width
        );
        btnConfirmar = new ButtonComponent(
                this,"Entrar", width/3, Color.rgb(0,128,0)
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
