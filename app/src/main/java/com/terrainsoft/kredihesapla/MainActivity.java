package com.terrainsoft.kredihesapla;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.terrainsoft.kredihesapla.Formula;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Formula formula;

    EditText editText;
    TextView textView;
    EditText rateText;
    EditText vadeText;
    double rate=1;
    int vade=1;
    double tutar=1;
    double deger=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        rateText=findViewById(R.id.rateText);
        vadeText=findViewById(R.id.vadeText);


        formula=new Formula();


        editText.addTextChangedListener(new TextWatcher() {

            //boolean kontrol=false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable view) {
                String s = "0";
                if (editText.getText().toString()==null)
                    return;

                try {

                    s=new DecimalFormat("###,###").format(Double.parseDouble(view.toString().replace(",","").replace(".","")));

                    editText.removeTextChangedListener(this); // kitlenmemesi için önce listener siliniyor

                    editText.setText(s);

                    editText.setSelection(editText.getText().length()); // cursor en sona götürülüyor
                    editText.addTextChangedListener(this); //listener tekrar aktif ediliyor

                }
                catch (NumberFormatException e)
                {
                    textView.setText(e.toString());
                }


            }
        });



    }


    public void hesapla(View view){

        try {
            if (rateText.getText().toString().matches("")||vadeText.getText().toString().matches("")||editText.getText().toString().matches("")) {

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Eksik Veri Girişi");
                alert.setMessage("Lütfen alanları doldurunuz");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                alert.show();
                return;

            }

            rate=Double.parseDouble(rateText.getText().toString().replace(",","."));
            vade=Integer.parseInt(vadeText.getText().toString());
            tutar=Double.parseDouble(editText.getText().toString().replace(",","").replace(".","").replace(" TL",""));



            deger = formula.TaksitTuatarı(tutar,rate,vade);
            double maliyet=deger*vade;


            String d=new DecimalFormat("#,##0.00").format(deger);
            String m=new DecimalFormat("#,##0.00").format(maliyet);
            String f=new DecimalFormat("#,##0.00").format(maliyet-tutar);


            textView.setText(" Taksit Tutarı : "+d+ " TL"+ " \n" + " Toplam Maliyet : "+m+ " TL"+"\n"+" Toplam Faiz : "+f);



        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }



    }

    public void getExtre(View view){

        if (deger==1){
            hesapla(view);
        }

        Intent intent=new Intent(this,ShowExtre.class);

        intent.putExtra("tutar",tutar);
        intent.putExtra("vade",vade);
        intent.putExtra("rate",rate);
        intent.putExtra("taksit",deger);

        startActivity(intent);

    }
}
