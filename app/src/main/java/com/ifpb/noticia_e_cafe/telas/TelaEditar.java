package com.ifpb.noticia_e_cafe.telas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;
import com.ifpb.noticia_e_cafe.control.UserControl;
import com.ifpb.noticia_e_cafe.model.entities.Usuario;
import com.ifpb.noticia_e_cafe.util.DeviceProperties;

/**
 * @author Leanderson-Coelho
 */
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

    public TelaEditar() {
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.width = DeviceProperties.getDeviceWidth(getWindowManager());

        //Layout principal
        this.layoutMain = new LinearLayout(this);
        this.layoutMain.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.layoutMain.setOrientation(LinearLayout.VERTICAL);
        this.layoutMain.setPadding(0, 0, 0, 0);
        this.layoutMain.setGravity(Gravity.CENTER_VERTICAL);
        setDynamicContent(layoutMain);

        //Titulo da tela
        this.nomeTela = new TextView(this);
        this.nomeTela.setText("Editar informações");
        this.nomeTela.setTextSize(30f);
        this.nomeTela.setPadding(100, 50, 0, 0);
        this.nomeTela.setTextColor(Color.rgb(7, 8, 8));

        this.construirLayoutInputs();
        this.construirLayoutBotoes();
        this.inserirValoresInputs();

        this.layoutMain.addView(nomeTela);
        this.layoutMain.addView(layoutInputs);
        this.layoutMain.addView(layoutButtons);
        setTitle("Tela de edição");

        //acoes butoes
        salvar.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nome.getValue().isEmpty()) {
                    Toast.makeText(TelaEditar.this, "Informe o seu nome!", Toast.LENGTH_SHORT).show();
                } else if (!(email.getValue().matches("^[\\w \\- \\. % +]{2,}@[a-z]{2,}(\\.[a-z]{2,})+$"))) {
                    Toast.makeText(TelaEditar.this, "Informe um email válido!", Toast.LENGTH_SHORT).show();
                } else if (senha.getValue().length() < 8) {
                    Toast.makeText(TelaEditar.this, "Informe uma senha de no mínimo 8 caracteres.", Toast.LENGTH_SHORT).show();
                } else {
                    UserControl userControl = new UserControl(TelaEditar.this);
                    Usuario usuario = new Usuario(nome.getValue(), email.getValue(), senha.getValue());
                    int resultado = userControl.update(usuario);
                    if (resultado > 0) {
                        nome.setValue("");
                        email.setValue("");
                        senha.setValue("");
                        startActivity(new Intent(TelaEditar.this, TelaPrincipal.class));
                        Toast.makeText(TelaEditar.this, "Dados Atualizados", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(TelaEditar.this, "Não foi possível atualizar os dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelar.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaEditar.this, TelaLogin.class));
            }
        });
    }

    @Override
    public String getTitleNavBar() {
        return "Edição";
    }

    private void construirLayoutInputs() {
        this.layoutInputs = new LinearLayout(this);
        this.layoutInputs.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.layoutInputs.setOrientation(LinearLayout.VERTICAL);
        this.layoutInputs.setPadding(10, 40, 10, 10);
        this.layoutInputs.setGravity(Gravity.CENTER_HORIZONTAL);

        //Inputs para edição das informações
        this.nome = new InputField(this, "Nome", InputType.TYPE_CLASS_TEXT, this.width);
        this.email = new InputField(this, "Email", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, this.width);
        this.senha = new InputField(this, "Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD, this.width);

        this.layoutInputs.addView(nome);
        this.layoutInputs.addView(email);
        this.layoutInputs.addView(senha);
    }

    private void construirLayoutBotoes() {
        this.layoutButtons = new LinearLayout(this);
        this.layoutButtons.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        this.layoutButtons.setOrientation(LinearLayout.HORIZONTAL);
        this.layoutButtons.setPadding(9, 9, 9, 9);
        this.layoutButtons.setGravity(Gravity.CENTER_HORIZONTAL);

        //Botões
        this.salvar = new ButtonComponent(this, "Salvar", this.width / 2, Color.rgb(0, 128, 0));
        this.cancelar = new ButtonComponent(this, "Cancelar", this.width / 2, Color.rgb(255, 0, 0));

        this.cancelar.setOnClickAction((v) -> startActivity(new Intent(this, TelaPrincipal.class)));

        this.layoutButtons.addView(cancelar);
        this.layoutButtons.addView(salvar);
    }

    private void inserirValoresInputs() {
        SharedPreferences sh = this.getSharedPreferences("authenticatedUser", Context.MODE_PRIVATE);
        this.nome.setValue(sh.getString("nome", null));
        this.email.setValue(sh.getString("email", null));
    }
}
