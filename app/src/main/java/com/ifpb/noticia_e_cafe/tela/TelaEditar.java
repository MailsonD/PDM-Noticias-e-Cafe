package com.ifpb.noticia_e_cafe.tela;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Display;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;

public class TelaEditar extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LinearLayout layoutInputs = new LinearLayout(this);
        layoutInputs.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layoutInputs.setOrientation(LinearLayout.VERTICAL);
        layoutInputs.setPadding(10,40,10,10);
        layoutInputs.setGravity(Gravity.CENTER_HORIZONTAL);

        final LinearLayout layoutButtons = new LinearLayout(this);
        layoutButtons.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layoutButtons.setOrientation(LinearLayout.HORIZONTAL);
        layoutButtons.setPadding(9,9,9,9);
        layoutButtons.setGravity(Gravity.CENTER_HORIZONTAL);

        final LinearLayout layoutMain = new LinearLayout(this);
        layoutMain.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layoutMain.setOrientation(LinearLayout.VERTICAL);
        layoutMain.setPadding(0,0,0,0);
        layoutMain.setGravity(Gravity.CENTER_VERTICAL);
        setContentView(layoutMain);

        //Titulo da tela
        final TextView nomeTela = new TextView(this);
        nomeTela.setText("Editar informações");
        nomeTela.setTextSize(30f);
        nomeTela.setPadding(100,50,0,0);
        nomeTela.setTextColor(Color.rgb(7,8,8));

        //Pegar informações da tela do dispositivo
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        //Inputs para edição das informações
        final InputField nome = new InputField(this,"Nome", InputType.TYPE_CLASS_TEXT,width);
        final InputField email = new InputField(this, "Email", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,width);
        final InputField senha = new InputField(this, "Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD,width);

        //Botões
        final ButtonComponent salvar = new ButtonComponent(this,"Salvar",width/2,Color.rgb(0,128,0));
        final ButtonComponent cancelar = new ButtonComponent(this,"Cancelar",width/2, Color.rgb(255,0,0));

        layoutInputs.addView(nome);
        layoutInputs.addView(email);
        layoutInputs.addView(senha);

        layoutButtons.addView(cancelar);
        layoutButtons.addView(salvar);

        layoutMain.addView(nomeTela);
        layoutMain.addView(layoutInputs);
        layoutMain.addView(layoutButtons);


    }
}
