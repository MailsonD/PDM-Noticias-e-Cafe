package com.ifpb.noticia_e_cafe.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonComponent extends LinearLayout {
    private String label;
    private Integer cor;
    private Integer width;
    private Button button;
    public ButtonComponent(Context context, String label){
        super(context);
        this.label = label;
        init();
    }

    public ButtonComponent(Context context, String label, Integer width){
        super(context);
        this.label = label;
        this.width = width;
        init();
    }

    public ButtonComponent(Context context, String label, Integer width, Integer cor) {
        super(context);
        this.label = label;
        this.cor = cor;
        this.width = width;
        init();
    }


    public void init() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
        button = new Button(getContext());
        button.setText(label);
        if(this.width!=null){
            button.setWidth(width);
        }
        if(this.cor!=null){
            button.getBackground().setColorFilter(cor, PorterDuff.Mode.MULTIPLY);
        }
        addView(button);
    }

    public void setOnClickAction(OnClickListener onClickListener){
        button.setOnClickListener(onClickListener);
    }
    public void setOnTouchAction(OnTouchListener onTouchListener){
        button.setOnTouchListener(onTouchListener);
    }



}
