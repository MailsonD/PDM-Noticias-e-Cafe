package com.ifpb.noticia_e_cafe.component;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;


public class NavBar extends LinearLayout {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
//    private NavigationView navigationView;

    public NavBar(Context context, String label, int inputType) {
        super(context);
        init();
    }

    private void init() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
//        toolbar.
    }

}
