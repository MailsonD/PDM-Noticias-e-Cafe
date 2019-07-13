package com.ifpb.noticia_e_cafe.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonComponent extends LinearLayout {
    private String label;
    private Integer cor;
    private Integer marginLeft;
    private Integer marginRight;
    private Button button;
    public ButtonComponent(Context context, String label){
        super(context);
        this.label = label;
        init();
    }

    public ButtonComponent(Context context, String label, int cor){
        super(context);
        this.label = label;
        this.cor = cor;
        init();
    }
    public ButtonComponent(Context context, String label, int cor, int marginLeft, int marginRight){
        super(context);
        this.label = label;
        this.cor = cor;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        init();
    }

    public ButtonComponent(Context context, String label, int marginLeft, int marginRight){
        super(context);
        this.label = label;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        init();
    }

    public void init() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
        if(this.marginLeft!=null || this.marginRight!=null){
            setPadding(marginLeft, 0, marginRight, 0);
        }
        button = new Button(getContext());
        button.setText(label);
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
