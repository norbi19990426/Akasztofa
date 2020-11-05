package com.example.akasztofa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView akasztofaImg;
    private Button btn_visszaFele, btn_elore,  btn_tippel;
    private TextView text_tippeltBetu, text_betuk;
    private String[] szavak = {"SZÁMÍTÓGÉP", "ALMA", "TESZT", "TÁSKA", "ABSZTRAKT", "MONITOR", "TELEFON", "EGÉR", "BÖGRE", "FEJHALLGATÓ"};
    private char[] betuk = {'A','Á','B','C','D','E','É','F','G','H','I','Í','J','K','L','M','N','O','Ó','Ö','Ő','P','R','S','T','U','Ú','Ü','Ű','V','Z'};
    private Random rng;
    private int randomSzam;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btn_visszaFele.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(int i = betuk.length; i >  0; i-- ){
                    text_tippeltBetu.setText(i);
                }
            }
        });
        btn_elore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < betuk.length;i++){
                    text_tippeltBetu.setText(i);
                }
            }
        });
    }


    private void init(){
        akasztofaImg = findViewById(R.id.akasztofaImage);
        btn_visszaFele = findViewById(R.id.btn_visszaFele);
        btn_elore = findViewById(R.id.btn_elore);
        btn_tippel = findViewById(R.id.btn_tippel);
        text_tippeltBetu = findViewById(R.id.text_tippelt_betu);
        text_betuk = findViewById(R.id.text_betuk);
        rng = new Random();
    }
}


