package com.ifpb.noticia_e_cafe.tela;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;

public class TelaCadastro extends AppCompatActivity {
    @Override
    public void onCreate(Bundle salvedInstanceState){
        super.onCreate(salvedInstanceState);


        final LinearLayout linearLayout1= new LinearLayout(this);
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setPadding(9,9,9,9);
        linearLayout1.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(linearLayout1);

//        final InputField inputField = new InputField(this,"Password:", InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        linearLayout1.addView(inputField);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;



        final ButtonComponent buttonComponent1 = new ButtonComponent(this, "Teste1", width/3, Color.rgb(50,205,50));
        linearLayout1.addView(buttonComponent1);

        final ButtonComponent buttonComponent2 = new ButtonComponent(this, "Teste2", width/3, Color.rgb(50,205,50));
        linearLayout1.addView(buttonComponent2);

        buttonComponent1.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Oi", "foi");
            }
        });

        buttonComponent2.setOnClickListener(new View.OnClickListener() { // não usar este método ele é o onClickListener do layout e não do botão
            @Override
            public void onClick(View v) {
                Log.d("Ola", "Foi");
            }
        });
    }
}
