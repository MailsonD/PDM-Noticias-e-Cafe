package com.ifpb.noticia_e_cafe.tela;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;

public class TelaCadastro extends AppCompatActivity {
    @Override
    public void onCreate(Bundle salvedInstanceState){
        super.onCreate(salvedInstanceState);

        final LinearLayout linearLayoutButtons= new LinearLayout(this);
        linearLayoutButtons.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayoutButtons.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutButtons.setPadding(9,9,9,9);
        linearLayoutButtons.setGravity(Gravity.CENTER_HORIZONTAL);

        final LinearLayout linearLayoutImputs= new LinearLayout(this);
        linearLayoutImputs.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayoutImputs.setOrientation(LinearLayout.VERTICAL);
        linearLayoutImputs.setPadding(9,9,9,9);
        linearLayoutImputs.setGravity(Gravity.CENTER_HORIZONTAL);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        final InputField nome = new InputField(this,"Nome:", InputType.TYPE_CLASS_TEXT, width);
        linearLayoutImputs.addView(nome);

        final InputField email = new InputField(this,"Email:", InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS, width);
        linearLayoutImputs.addView(email);

        final InputField senha = new InputField(this,"Password:", InputType.TYPE_TEXT_VARIATION_PASSWORD, width);
        linearLayoutImputs.addView(senha);

        final ButtonComponent cancelar = new ButtonComponent(this, "Cancelar", width/3, Color.rgb(255,0,0));
        linearLayoutButtons.addView(cancelar);

        final ButtonComponent cadastrar = new ButtonComponent(this, "Cadastrar", width/3, Color.rgb(0,128,0));
        linearLayoutButtons.addView(cadastrar);

        cancelar.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Oi", "Cancelou!");
            }
        });

        cadastrar.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Oi", "Cadastrou");
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() { // não usar este método ele é o onClickListener do layout e não do botão
            @Override
            public void onClick(View v) {
                Log.d("Ola", "Foi");
            }
        });

        final TextView titulo = new TextView(this);
        titulo.setText("Cadastro\n\n");
        titulo.setTextSize(30f);
        titulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        final LinearLayout linearLayout= new LinearLayout(this);
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
