package com.ifpb.noticia_e_cafe.tela;

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

        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(9,9,9,9);
//        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        setContentView(linearLayout);

        //Titulo da tela
        final TextView nomeTela = new TextView(this);
        nomeTela.setText("Editar informações");
        nomeTela.setTextSize(30f);
        nomeTela.setPadding(5,5,0,0);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        //Inputs para edição das informações
        final InputField nome = new InputField(this,"Nome", InputType.TYPE_CLASS_TEXT,width/3);
        final InputField email = new InputField(this, "Email", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,width//3);
        final InputField senha = new InputField(this, "Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD,width/3);



        final ButtonComponent salvar = new ButtonComponent(this,"Salvar",width/2);
        final ButtonComponent cancelar = new ButtonComponent(this,"Cancelar",width/2);

        linearLayout.addView(nomeTela);
        linearLayout.addView(nome);
        linearLayout.addView(email);
        linearLayout.addView(senha);
        linearLayout.addView(cancelar);
        linearLayout.addView(salvar);


    }
}
