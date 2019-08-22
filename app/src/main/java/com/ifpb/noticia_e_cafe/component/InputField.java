package com.ifpb.noticia_e_cafe.component;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class InputField extends LinearLayout {
    private TextView textView;
    private EditText editText;
    private String label;
    private Integer width;
    private int inputType;

    public InputField(Context context, String label,int inputType) {
        super(context);
        this.label = label;
        this.inputType = inputType;
        init();
    }

    public InputField(Context context, String label,int inputType, Integer width) {
        super(context);
        this.label = label;
        this.inputType = inputType;
        this.width = width;
        init();
    }

    private void init() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
        textView = new TextView(getContext());
        textView.setText(label);
        addView(textView);

        editText = new EditText(getContext());
        //Limitar unica linha input
        editText.setSingleLine();
        editText.setInputType(InputType.TYPE_CLASS_TEXT | inputType);
        editText.setWidth(width);

        addView(editText);
    }

    public String getValue(){
        return editText.getText().toString();
    }
    public void setValue(String value){editText.setText(value);}


}
