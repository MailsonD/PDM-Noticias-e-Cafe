package com.ifpb.noticia_e_cafe.tela;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout linearLayoutForm;
//    private LinearLayout linearLayoutButtons;
    private InputField inputFieldEmail;
    private InputField inputFieldSenha;
    private ButtonComponent btnConfirmar;
    private EditText textoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //============ INSTANCIANDO COMPONENTES ===============
        linearLayoutForm = new LinearLayout(this);
//        linearLayoutButtons = new LinearLayout(this);
        inputFieldEmail = new InputField(this,"Email", InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS,100);
        inputFieldSenha = new InputField(this,"Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD,100);
        btnConfirmar = new ButtonComponent(this,"Entrar", 50, Color.GREEN);
        textoCadastro= new EditText(this);
        textoCadastro.setText("Cadastre-se");
        textoCadastro.setGravity(Gravity.BOTTOM);
        textoCadastro.setClickable(true);

        //============== CONFIGURANDO LAYOUT ==================
        linearLayoutForm.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        );
        linearLayoutForm.setGravity(Gravity.CENTER);
        linearLayoutForm.setOrientation(LinearLayout.VERTICAL);

        //=====================================================
//        linearLayoutButtons.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
//        );
//        linearLayoutButtons.setGravity(Gravity.CENTER);
//        linearLayoutButtons.setOrientation(LinearLayout.VERTICAL);

        //========= ADICIONANDO COMPONENTES AO LAYOUT =========
        setContentView(linearLayoutForm);
        linearLayoutForm.addView(inputFieldEmail);
        linearLayoutForm.addView(inputFieldSenha);
        linearLayoutForm.addView(btnConfirmar);
        linearLayoutForm.addView(textoCadastro);


        //=========== ADICIONANDO EVENTOS ONCLICK ==============

        btnConfirmar.setOnClickAction((v) -> Log.d("DEBUGANDO_SAPORRA","MeuBotão"));
        btnConfirmar.setOnClickAction(
                (v) -> onNewIntent(new Intent(this,TelaCadastro.class))
        );


    }
}
