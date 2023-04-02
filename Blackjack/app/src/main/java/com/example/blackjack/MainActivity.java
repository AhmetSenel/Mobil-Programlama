package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button buttonPas,buttonKart;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8;
    ImageView image9,image10,image11,image12,image13,image14,image15,image16;
    TextView textComputer,textPlayer,textWinning,textDevam;

    ConstraintLayout clayout;

    int[] kartlar = new int[]{R.drawable.karo2,R.drawable.sinek2,R.drawable.kupa2,R.drawable.maca2,
            R.drawable.karo3,R.drawable.sinek3,R.drawable.kupa3,R.drawable.maca3,R.drawable.karo4,
            R.drawable.sinek4,R.drawable.kupa4,R.drawable.maca4,R.drawable.karo5,R.drawable.sinek5,
            R.drawable.kupa5,R.drawable.maca5,R.drawable.karo6,R.drawable.sinek6,R.drawable.kupa6,
            R.drawable.maca6,R.drawable.karo7,R.drawable.sinek7,R.drawable.kupa7,R.drawable.maca7,
            R.drawable.karo8,R.drawable.sinek8,R.drawable.kupa8,R.drawable.maca8,R.drawable.karo9,
            R.drawable.sinek9,R.drawable.kupa9,R.drawable.maca9,R.drawable.karo10,R.drawable.sinek10,
            R.drawable.kupa10,R.drawable.maca10,R.drawable.karo_vale,R.drawable.sinek_vale,
            R.drawable.kupa_vale,R.drawable.karo_kiz,R.drawable.sinek_kiz,R.drawable.kupa_kiz,
            R.drawable.maca_kiz,R.drawable.karo_papaz,R.drawable.sinek_papaz,R.drawable.kupa_papaz,
            R.drawable.maca_papaz,R.drawable.karo_as,R.drawable.sinek_as,R.drawable.kupa_as,
            R.drawable.maca_as,

            R.drawable.karo2,R.drawable.sinek2,R.drawable.kupa2,R.drawable.maca2,
            R.drawable.karo3,R.drawable.sinek3,R.drawable.kupa3,R.drawable.maca3,R.drawable.karo4,
            R.drawable.sinek4,R.drawable.kupa4,R.drawable.maca4,R.drawable.karo5,R.drawable.sinek5,
            R.drawable.kupa5,R.drawable.maca5,R.drawable.karo6,R.drawable.sinek6,R.drawable.kupa6,
            R.drawable.maca6,R.drawable.karo7,R.drawable.sinek7,R.drawable.kupa7,R.drawable.maca7,
            R.drawable.karo8,R.drawable.sinek8,R.drawable.kupa8,R.drawable.maca8,R.drawable.karo9,
            R.drawable.sinek9,R.drawable.kupa9,R.drawable.maca9,R.drawable.karo10,R.drawable.sinek10,
            R.drawable.kupa10,R.drawable.maca10,R.drawable.karo_vale,R.drawable.sinek_vale,
            R.drawable.kupa_vale,R.drawable.karo_kiz,R.drawable.sinek_kiz,R.drawable.kupa_kiz,
            R.drawable.maca_kiz,R.drawable.karo_papaz,R.drawable.sinek_papaz,R.drawable.kupa_papaz,
            R.drawable.maca_papaz,R.drawable.karo_as,R.drawable.sinek_as,R.drawable.kupa_as,
            R.drawable.maca_as,

            R.drawable.karo2,R.drawable.sinek2,R.drawable.kupa2,R.drawable.maca2,
            R.drawable.karo3,R.drawable.sinek3,R.drawable.kupa3,R.drawable.maca3,R.drawable.karo4,
            R.drawable.sinek4,R.drawable.kupa4,R.drawable.maca4,R.drawable.karo5,R.drawable.sinek5,
            R.drawable.kupa5,R.drawable.maca5,R.drawable.karo6,R.drawable.sinek6,R.drawable.kupa6,
            R.drawable.maca6,R.drawable.karo7,R.drawable.sinek7,R.drawable.kupa7,R.drawable.maca7,
            R.drawable.karo8,R.drawable.sinek8,R.drawable.kupa8,R.drawable.maca8,R.drawable.karo9,
            R.drawable.sinek9,R.drawable.kupa9,R.drawable.maca9,R.drawable.karo10,R.drawable.sinek10,
            R.drawable.kupa10,R.drawable.maca10,R.drawable.karo_vale,R.drawable.sinek_vale,
            R.drawable.kupa_vale,R.drawable.karo_kiz,R.drawable.sinek_kiz,R.drawable.kupa_kiz,
            R.drawable.maca_kiz,R.drawable.karo_papaz,R.drawable.sinek_papaz,R.drawable.kupa_papaz,
            R.drawable.maca_papaz,R.drawable.karo_as,R.drawable.sinek_as,R.drawable.kupa_as,
            R.drawable.maca_as};
    int i = 4;
    int kontrol = 1,kontrol2 = 1;
    int kartDeger,kartDeger2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPas = findViewById(R.id.buttonPas);
        buttonKart = findViewById(R.id.buttonKart);
        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image5 = findViewById(R.id.imageView5);
        image6 = findViewById(R.id.imageView6);
        image7 = findViewById(R.id.imageView7);
        image8 = findViewById(R.id.imageView8);
        image9 = findViewById(R.id.imageView9);
        image10 = findViewById(R.id.imageView10);
        image11 = findViewById(R.id.imageView11);
        image12 = findViewById(R.id.imageView12);
        image13 = findViewById(R.id.imageView13);
        image14 = findViewById(R.id.imageView14);
        image15 = findViewById(R.id.imageView15);
        image16 = findViewById(R.id.imageView16);

        textWinning = findViewById(R.id.textViewWinning);
        textComputer = findViewById(R.id.textViewComputer);
        textPlayer = findViewById(R.id.textViewPlayer);
        textDevam = findViewById(R.id.textViewDevam);

        clayout = findViewById(R.id.clayout);

        shuffleArray(kartlar);

        image1.setVisibility(View.VISIBLE);
        image1.setImageDrawable(getDrawable(kartlar[0]));

        image9.setVisibility(View.VISIBLE);
        image9.setImageDrawable(getDrawable(kartlar[1]));

        image2.setVisibility(View.VISIBLE);
        image2.setImageDrawable(getDrawable(kartlar[2]));

        image10.setVisibility(View.VISIBLE);

        textPlayer.setText(toplam(0)+toplam(2)+"");
        textComputer.setText(toplam(1)+"");


        buttonKart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(textPlayer.getText().toString())<=21){
                switch(kontrol) {
                    case 1:
                        image3.setVisibility(View.VISIBLE);
                        image3.setImageDrawable(getDrawable(kartlar[i]));
                        textPlayer.setText(Integer.parseInt(textPlayer.getText().toString())+toplam(4)+"");
                        break;
                    case 2:
                        image4.setVisibility(View.VISIBLE);
                        image4.setImageDrawable(getDrawable(kartlar[i]));
                        textPlayer.setText(Integer.parseInt(textPlayer.getText().toString())+toplam(5)+"");
                        break;
                    case 3:
                        image5.setVisibility(View.VISIBLE);
                        image5.setImageDrawable(getDrawable(kartlar[i]));
                        textPlayer.setText(Integer.parseInt(textPlayer.getText().toString())+toplam(6)+"");
                        break;
                    case 4:
                        image6.setVisibility(View.VISIBLE);
                        image6.setImageDrawable(getDrawable(kartlar[i]));
                        textPlayer.setText(Integer.parseInt(textPlayer.getText().toString())+toplam(7)+"");
                        break;
                    case 5:
                        image7.setVisibility(View.VISIBLE);
                        image7.setImageDrawable(getDrawable(kartlar[i]));
                        textPlayer.setText(Integer.parseInt(textPlayer.getText().toString())+toplam(8)+"");
                        break;
                    case 6:
                        image8.setVisibility(View.VISIBLE);
                        image8.setImageDrawable(getDrawable(kartlar[i]));
                        textPlayer.setText(Integer.parseInt(textPlayer.getText().toString())+toplam(9)+"");
                        break;
                    }
                }
                if(Integer.parseInt(textPlayer.getText().toString())>21){
                    textWinning.setText("Bilgisayar Kazandı");
                    textDevam.setText("Devam etmek için herhangi bir yere basın");
                }

                i++;
                kontrol++;
            }
        });

        buttonPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image10.setImageDrawable(getDrawable(kartlar[3]));
                textComputer.setText(Integer.parseInt(textComputer.getText().toString()) + toplam(3) + "");

                for (int i = 0; i < 6; i++) {
                    if (Integer.parseInt(textComputer.getText().toString()) <= 16) {
                        switch (kontrol2) {
                            case 1:
                                image11.setVisibility(View.VISIBLE);
                                image11.setImageDrawable(getDrawable(kartlar[i]));
                                textComputer.setText(Integer.parseInt(textComputer.getText().toString()) + toplam(i) + "");
                                break;
                            case 2:
                                image12.setVisibility(View.VISIBLE);
                                image12.setImageDrawable(getDrawable(kartlar[i]));
                                textComputer.setText(Integer.parseInt(textComputer.getText().toString()) + toplam(i) + "");
                                break;
                            case 3:
                                image13.setVisibility(View.VISIBLE);
                                image13.setImageDrawable(getDrawable(kartlar[i]));
                                textComputer.setText(Integer.parseInt(textComputer.getText().toString()) + toplam(i) + "");
                                break;
                            case 4:
                                image14.setVisibility(View.VISIBLE);
                                image14.setImageDrawable(getDrawable(kartlar[i]));
                                textComputer.setText(Integer.parseInt(textComputer.getText().toString()) + toplam(i) + "");
                                break;
                            case 5:
                                image15.setVisibility(View.VISIBLE);
                                image15.setImageDrawable(getDrawable(kartlar[i]));
                                textComputer.setText(Integer.parseInt(textComputer.getText().toString()) + toplam(i) + "");
                                break;
                            case 6:
                                image16.setVisibility(View.VISIBLE);
                                image16.setImageDrawable(getDrawable(kartlar[i]));
                                textComputer.setText(Integer.parseInt(textComputer.getText().toString()) + toplam(i) + "");
                                break;
                        }
                        kontrol2++;
                    }
                    i++;
                }
                if (Integer.parseInt(textComputer.getText().toString()) > Integer.parseInt(textPlayer.getText().toString()) && Integer.parseInt(textComputer.getText().toString()) <= 21) {
                    textWinning.setText("Bilgisayar Kazandı");
                    textDevam.setText("Devam etmek için herhangi bir yere basın");
                } else if (Integer.parseInt(textComputer.getText().toString()) == Integer.parseInt(textPlayer.getText().toString())) {
                    textWinning.setText("Berabere");
                    textDevam.setText("Devam etmek için herhangi bir yere basın");
                } else{
                    textWinning.setText("Oyuncu Kazandı");
                    textDevam.setText("Devam etmek için herhangi bir yere basın");
                }
            }
        });

        clayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffleArray(kartlar);

                textWinning.setText("");
                textDevam.setText("");

                image1.setVisibility(View.VISIBLE);
                image1.setImageDrawable(getDrawable(kartlar[0]));

                image9.setVisibility(View.VISIBLE);
                image9.setImageDrawable(getDrawable(kartlar[1]));

                image2.setVisibility(View.VISIBLE);
                image2.setImageDrawable(getDrawable(kartlar[2]));

                image10.setVisibility(View.VISIBLE);
                image10.setImageDrawable(getDrawable(R.drawable.joker));

                textPlayer.setText(toplam(0)+toplam(2)+"");
                textComputer.setText(toplam(1)+"");

                image3.setVisibility(View.INVISIBLE);
                image4.setVisibility(View.INVISIBLE);
                image5.setVisibility(View.INVISIBLE);
                image6.setVisibility(View.INVISIBLE);
                image7.setVisibility(View.INVISIBLE);
                image8.setVisibility(View.INVISIBLE);
                image11.setVisibility(View.INVISIBLE);
                image12.setVisibility(View.INVISIBLE);
                image13.setVisibility(View.INVISIBLE);
                image14.setVisibility(View.INVISIBLE);
                image15.setVisibility(View.INVISIBLE);
                image16.setVisibility(View.INVISIBLE);

                i = 4;
                kontrol = 1;
                kontrol2 = 1;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public int toplam(int i){
        switch (kartlar[i]){
            case R.drawable.karo2:
            case R.drawable.sinek2:
            case R.drawable.maca2:
            case R.drawable.kupa2:
                kartDeger =2;
                break;
            case R.drawable.karo3:
            case R.drawable.sinek3:
            case R.drawable.maca3:
            case R.drawable.kupa3:
                kartDeger =3;
                break;
            case R.drawable.karo4:
            case R.drawable.sinek4:
            case R.drawable.maca4:
            case R.drawable.kupa4:
                kartDeger =4;
                break;
            case R.drawable.karo5:
            case R.drawable.sinek5:
            case R.drawable.maca5:
            case R.drawable.kupa5:
                kartDeger =5;
                break;
            case R.drawable.karo6:
            case R.drawable.sinek6:
            case R.drawable.maca6:
            case R.drawable.kupa6:
                kartDeger =6;
                break;
            case R.drawable.karo7:
            case R.drawable.sinek7:
            case R.drawable.maca7:
            case R.drawable.kupa7:
                kartDeger =7;
                break;
            case R.drawable.karo8:
            case R.drawable.sinek8:
            case R.drawable.maca8:
            case R.drawable.kupa8:
                kartDeger =8;
                break;
            case R.drawable.karo9:
            case R.drawable.sinek9:
            case R.drawable.maca9:
            case R.drawable.kupa9:
                kartDeger =9;
                break;
            case R.drawable.karo10:
            case R.drawable.karo_vale:
            case R.drawable.karo_kiz:
            case R.drawable.karo_papaz:
            case R.drawable.sinek10:
            case R.drawable.sinek_vale:
            case R.drawable.sinek_kiz:
            case R.drawable.sinek_papaz:
            case R.drawable.maca10:
            case R.drawable.maca_vale:
            case R.drawable.maca_kiz:
            case R.drawable.maca_papaz:
            case R.drawable.kupa10:
            case R.drawable.kupa_vale:
            case R.drawable.kupa_kiz:
            case R.drawable.kupa_papaz:
                kartDeger =10;
                break;
            case R.drawable.karo_as:
            case R.drawable.sinek_as:
            case R.drawable.maca_as:
            case R.drawable.kupa_as:
                kartDeger =11;
                kartDeger2 +=1;
                break;
        }
        return kartDeger;
    }

    static void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}

