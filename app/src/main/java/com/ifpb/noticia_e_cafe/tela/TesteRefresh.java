package com.ifpb.noticia_e_cafe.tela;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TesteRefresh extends Activity {

    private SwipeRefreshLayout srp;
    private List<String> mensagens;
    private ListView listView;
    private RecyclerView recyclerView;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mensagens= new ArrayList<>();
        mensagens.add("testerson");
        mensagens.add("jobson");
        mensagens.add("arison");

        srp = new SwipeRefreshLayout(this);

        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        srp.setLayoutParams(layoutParams);

        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutParams(layoutParams);

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mensagens);
        listView = new ListView(this);
        listView.setAdapter(adapter);

//        srp.addView(recyclerView);
//        recyclerView.setAdapter(adapter);
//        recyclerView.addView(listView);
        srp.addView(listView);
        setContentView(srp);

        srp.setOnRefreshListener(() -> {
            Log.d("APP_DEBUG",++contador+"");
            mensagens.add("teste Foi");
            ListAdapter ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mensagens);
            listView.setAdapter(ad);
            srp.setRefreshing(false);
        });

    }

}
