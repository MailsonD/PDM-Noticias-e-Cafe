package com.ifpb.noticia_e_cafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.LinearLayout;

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
    }
}
