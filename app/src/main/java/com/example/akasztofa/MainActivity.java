package com.example.akasztofa;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.security.cert.CertificateParsingException;

public class MainActivity extends AppCompatActivity {

    private ImageView akasztofaImg;
    private Button btn_visszaFele, btn_elore,  btn_tippel;
    private TextView text_tippeltBetu, text_betuk;
    private String[] szavak = {"SZÁMÍTÓGÉP", "ALMA", "TESZT", "TÁSKA", "ABSZTRAKT", "MONITOR", "TELEFON", "EGÉR", "BÖGRE", "FEJHALLGATÓ"};
    private char[] betuk = {'A','Á','B','C','D','E','É','F','G','H','I','Í','J','K','L','M','N','O','Ó','Ö','Ő','P','R','S','T','U','Ú','Ü','Ű','V','Z'};
    private char betu;
    private Random rng;
    private int randomSzam, lepkedes, korokFalse;
    private String szo;
    private List<Character> voltBetu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        randomSzam = rng.nextInt(10) + 1;
        szoHosszMegjelenites();

        btn_visszaFele.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                lepkedes--;
                if (lepkedes >= 0){
                    text_tippeltBetu.setText(betuk[lepkedes]+"");
                }
                else{
                    lepkedes = 30;
                    text_tippeltBetu.setText(betuk[lepkedes]+"");
                }
                betuTiltas(betuDontes(betu));

            }
        });
        btn_elore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lepkedes++;
                if (lepkedes <= 30){
                    text_tippeltBetu.setText(betuk[lepkedes]+"");
                }
                else{
                    lepkedes = 0;
                    text_tippeltBetu.setText(betuk[lepkedes]+"");
                }
                betuTiltas(betuDontes(betu));

            }
        });
        btn_tippel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;
                betu = betuk[lepkedes];

                text_betuk.setText(betuCsere());

                betuTiltas(betuDontes(betu));

                if(hibaKuld() == false){
                    korokFalse++;
                    text= "Helytelen betű!";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    text= "Helyes betű!";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                hiba(korokFalse);

                if(korokFalse == 13){
                    final AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Nem sikerült kitalálni!");
                    builder.setMessage("Szeretnél még egyet játszani?");
                    builder.setNegativeButton("Igen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
                    builder.setPositiveButton("Nem", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else if(betuCsere().equals(szavak[randomSzam])){
                    final AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Helyes megfejtés!");
                    builder.setMessage("Szeretnél még egyet játszani?");
                    builder.setNegativeButton("Igen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
                    builder.setPositiveButton("Nem", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
    private boolean betuDontes(char betu){

        voltBetu.add(betu);
        boolean dont = true;
        for(Character c : voltBetu){
            if (c.equals(betuk[lepkedes])){
                dont = false;
            }
        }
        return dont;
    }
    private void betuTiltas(boolean dont){
        if(dont == false){
            btn_tippel.setEnabled(false);
            text_tippeltBetu.setTextColor(Color.parseColor("#000000"));
        }
        else{
            btn_tippel.setEnabled(true);
            text_tippeltBetu.setTextColor(Color.parseColor("#FF0000"));
        }
    }
    private void szoHosszMegjelenites() {

            for(int i = 0; i < szavak[randomSzam].length(); i++){
                    szo = szo + "_";

            }

        text_betuk.setText(szo);
    }
    private String betuCsere(){
        char[] tomb = szavak[randomSzam].toCharArray();
        char[] tombSzo = szo.toCharArray();
        for (int i = 0; i < tomb.length; i++){
            if (tomb[i] == betuk[lepkedes]) {
                for (int j = 0; j < tombSzo.length; j++){
                    tombSzo[i] = betuk[lepkedes];
                }
            }
        }
        szo = new String(tombSzo);
        return szo;
    }
    private boolean hibaKuld(){
        boolean dont = false;
        char[] tomb = szavak[randomSzam].toCharArray();
        for (int i = 0; i < tomb.length; i++){
            if (tomb[i] == betuk[lepkedes]) {
                dont = true;
            }
        }
        return dont;
    }
    private void hiba(int korokFalse){

        switch (korokFalse){
            case 1: akasztofaImg.setImageResource(R.drawable.akasztofa01);break;
            case 2: akasztofaImg.setImageResource(R.drawable.akasztofa02);break;
            case 3: akasztofaImg.setImageResource(R.drawable.akasztofa03);break;
            case 4: akasztofaImg.setImageResource(R.drawable.akasztofa04);break;
            case 5: akasztofaImg.setImageResource(R.drawable.akasztofa05);break;
            case 6: akasztofaImg.setImageResource(R.drawable.akasztofa06);break;
            case 7: akasztofaImg.setImageResource(R.drawable.akasztofa07);break;
            case 8: akasztofaImg.setImageResource(R.drawable.akasztofa08);break;
            case 9: akasztofaImg.setImageResource(R.drawable.akasztofa09);break;
            case 10: akasztofaImg.setImageResource(R.drawable.akasztofa10);break;
            case 11: akasztofaImg.setImageResource(R.drawable.akasztofa11);break;
            case 12: akasztofaImg.setImageResource(R.drawable.akasztofa12);break;
            case 13: akasztofaImg.setImageResource(R.drawable.akasztofa13);break;

        }
    }


    private void init(){
        akasztofaImg = findViewById(R.id.akasztofaImage);
        btn_visszaFele = findViewById(R.id.btn_visszaFele);
        btn_elore = findViewById(R.id.btn_elore);
        btn_tippel = findViewById(R.id.btn_tippel);
        text_tippeltBetu = findViewById(R.id.text_tippelt_betu);
        text_betuk = findViewById(R.id.text_betuk);
        rng = new Random();
        lepkedes = 0;
        szo = "";
        korokFalse = 0;
        voltBetu = new ArrayList<>();
    }
}


