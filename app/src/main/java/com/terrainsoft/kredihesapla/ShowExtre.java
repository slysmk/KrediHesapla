package com.terrainsoft.kredihesapla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.terrainsoft.kredihesapla.Formula;

public class ShowExtre extends AppCompatActivity {

    Formula formula;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_extre);

        listView=findViewById(R.id.listView);

        formula=new Formula();
        Intent intent=this.getIntent();
        double taksit=intent.getDoubleExtra("taksit",1);
        int vade=intent.getIntExtra("vade",1);
        double rate=intent.getDoubleExtra("rate",1);
        double tutar=intent.getDoubleExtra("tutar",1);


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,formula.TaksitDokum(taksit,rate,tutar,vade));
        listView.setAdapter(arrayAdapter);


    }
}

