package com.ifpb.noticia_e_cafe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle salvedInstanceState){
        super.onCreate(salvedInstanceState);

        final LinearLayout linearLayout= new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(9,9,9,9);
        setContentView(linearLayout);

        final InputField inputField = new InputField(this,"Password:", InputType.TYPE_TEXT_VARIATION_PASSWORD);
        linearLayout.addView(inputField);

        final ButtonComponent buttonComponent1 = new ButtonComponent(this, "Teste1", Color.rgb(50,205,50), 60, 60);
        linearLayout.addView(buttonComponent1);

        final ButtonComponent buttonComponent2 = new ButtonComponent(this, "Teste2", Color.rgb(50,205,50), 60, 60);
        linearLayout.addView(buttonComponent2);

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
