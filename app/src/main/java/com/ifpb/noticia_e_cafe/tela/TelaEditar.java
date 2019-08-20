package com.ifpb.noticia_e_cafe.tela;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;
import com.ifpb.noticia_e_cafe.util.DeviceProperties;

public class TelaEditar extends NavBar {

    private LinearLayout layoutInputs;
    private LinearLayout layoutButtons;
    private LinearLayout layoutMain;
    private TextView nomeTela;
    private InputField nome;
    private InputField email;
    private InputField senha;
    private ButtonComponent salvar;
    private ButtonComponent cancelar;
    private int width;

    public TelaEditar(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        this.width = DeviceProperties.getDeviceWidth(getWindowManager());

        //Layout principal
        this.layoutMain = new LinearLayout(this);
        this.layoutMain.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.layoutMain.setOrientation(LinearLayout.VERTICAL);
        this.layoutMain.setPadding(0,0,0,0);
        this.layoutMain.setGravity(Gravity.CENTER_VERTICAL);
        setDynamicContent(layoutMain);

        //Titulo da tela
        this.nomeTela = new TextView(this);
        this.nomeTela.setText("Editar informações");
        this.nomeTela.setTextSize(30f);
        this.nomeTela.setPadding(100,50,0,0);
        this.nomeTela.setTextColor(Color.rgb(7,8,8));

        this.construirLayoutInputs();
        this.construirLayoutBotoes();

        this.layoutMain.addView(nomeTela);
        this.layoutMain.addView(layoutInputs);
        this.layoutMain.addView(layoutButtons);

    }

    private void construirLayoutInputs(){
        this.layoutInputs = new LinearLayout(this);
        this.layoutInputs.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.layoutInputs.setOrientation(LinearLayout.VERTICAL);
        this.layoutInputs.setPadding(10,40,10,10);
        this.layoutInputs.setGravity(Gravity.CENTER_HORIZONTAL);

        //Inputs para edição das informações
        this.nome = new InputField(this,"Nome", InputType.TYPE_CLASS_TEXT, this.width);
        this.email = new InputField(this, "Email", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, this.width);
        this.senha = new InputField(this, "Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD, this.width);

        this.layoutInputs.addView(nome);
        this.layoutInputs.addView(email);
        this.layoutInputs.addView(senha);
    }

    private void construirLayoutBotoes(){
        this.layoutButtons = new LinearLayout(this);
        this.layoutButtons.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        this.layoutButtons.setOrientation(LinearLayout.HORIZONTAL);
        this.layoutButtons.setPadding(9,9,9,9);
        this.layoutButtons.setGravity(Gravity.CENTER_HORIZONTAL);

        //Botões
        this.salvar = new ButtonComponent(this,"Salvar",this.width/2,Color.rgb(0,128,0));
        this.cancelar = new ButtonComponent(this,"Cancelar",this.width/2, Color.rgb(255,0,0));

        this.cancelar.setOnClickAction((v) -> startActivity(new Intent(this,TelaPrincipal.class)));

        this.layoutButtons.addView(cancelar);
        this.layoutButtons.addView(salvar);

    }
}
