package com.ifpb.noticia_e_cafe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.ifpb.noticia_e_cafe.component.ButtonComponent;
import com.ifpb.noticia_e_cafe.component.InputField;
import com.ifpb.noticia_e_cafe.rss.MyIntentService;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle salvedInstanceState){
        super.onCreate(salvedInstanceState);
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
//        stopService(intent);
    }
}
