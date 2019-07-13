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
    private int inputType;

    public InputField(Context context, String label,int inputType) {
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
        textView = new TextView(getContext());
        textView.setText(label);
        addView(textView);

        editText = new EditText(getContext());
        //Limitar unica linha input
        editText.setSingleLine();
        editText.setInputType(InputType.TYPE_CLASS_TEXT | inputType);
        editText.setPadding(10, 0, 10, 0);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(!(s.toString().matches("[\\w]{1,}@[\\w]{1,}+(.[\\w]{2,})?"))){
//                    Log.d("DEVELOPER", s+" não é um email válido!");
//                }
//            }
//        });
        addView(editText);
    }

    public String getValue(){
        return editText.getText().toString();
    }



}
