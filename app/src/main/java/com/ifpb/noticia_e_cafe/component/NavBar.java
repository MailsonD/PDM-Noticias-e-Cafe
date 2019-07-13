package com.ifpb.noticia_e_cafe.component;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;


public class NavBar extends LinearLayout {
    private TextView textView;
    private EditText editText;
    private String label;
    private int inputType;

    public NavBar(Context context, String label, int inputType) {
        super(context);
        this.label = label;
        this.inputType = inputType;
        init();
    }

    private void init() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
        setPadding(30, 0, 30, 0);
//        Toolbar myToolbar = new Toolbar(this, );
    }

    public String getValue(){
        return editText.getText().toString();
    }



}
