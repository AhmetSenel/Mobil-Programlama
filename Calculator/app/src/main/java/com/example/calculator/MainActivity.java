package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton button0,buttonComma,buttonEqual;
    ImageButton button1,button2,button3,buttonPlus;
    ImageButton button4,button5,button6,buttonMinus;
    ImageButton button7,button8,button9,buttonMulti;
    ImageButton buttonClear,buttonDelete,buttonDiv;
    TextView sonuc,islem;
    boolean div,multi,plus,minus;
    boolean delete=false;
    float equalKontrol, sonucKontrol = 0, birinciSayi, ikinciSayi;
    String islemString, ikinciString ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonComma = findViewById(R.id.buttonComma);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDiv = findViewById(R.id.buttonDiv);
        sonuc = findViewById(R.id.sonuc);
        islem = findViewById(R.id.islem);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonucKontrol =Float.parseFloat(sonuc.getText()+"");
                if(sonucKontrol ==0) {
                    sonuc.setText("0");
                }
                else if(plus==true) {
                    islemString +="0";
                    islem.setText(islemString);
                    ikinciString +="0";
                    ikinciSayi =Float.parseFloat(ikinciString +"");
                    sonuc.setText(birinciSayi + ikinciSayi +"");
                }
                else if(minus==true) {
                    islemString += "0";
                    islem.setText(islemString);
                    ikinciString += "0";
                    ikinciSayi = Float.parseFloat(ikinciString + "");
                    sonuc.setText(birinciSayi - ikinciSayi + "");
                }
                else if(div==true) {
                    islemString +="0";
                    islem.setText(islemString);
                    ikinciString +="0";
                    ikinciSayi =Float.parseFloat(ikinciString + "");
                    sonuc.setText(birinciSayi / ikinciSayi + "");
                }
                else if(multi==true) {
                    islemString += "0";
                    islem.setText(islemString);
                    ikinciString += "0";
                    ikinciSayi = Float.parseFloat(ikinciString + "");
                    sonuc.setText(birinciSayi * ikinciSayi + "");
                }
                else{
                    sonuc.setText(sonuc.getText()+"0");
                    islemString += "0";
                    islem.setText(islemString);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {rakam("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam("9");
            }
        });

        buttonComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rakam(".");
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonuc.setText("0");
                islem.setText("");
                islem.setTextSize(50);
                sonuc.setTextSize(40);
                sonucKontrol =0;
                islemString ="";
                ikinciSayi =0;
                div=false;
                multi=false;
                plus=false;
                minus=false;
                delete=false;
                ikinciString ="";
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonucKontrol = Float.parseFloat(sonuc.getText() + "");
                if(sonucKontrol ==0 || sonuc.getText().length()==1){
                    islem.setText("");
                    sonuc.setText("0");
                }
                else if(sonucKontrol !=0 && delete==false) {
                    islemString = removeLastChar(islem.getText().toString());
                    islem.setText(islemString);
                    sonuc.setText(islem.getText());
                    birinciSayi = Float.parseFloat(sonuc.getText() + "");
                    plus=false;
                    minus=false;
                    multi=false;
                    div=false;
                }
                else {
                    islemString = removeLastChar(islem.getText().toString());
                    islem.setText(islemString);
                    if(ikinciString.length()==1){
                        ikinciString ="0";
                        delete=false;
                        plus=false;
                        minus=false;
                        multi=false;
                        div=false;
                        sonuc.setText(birinciSayi +"");
                    }
                    else {
                        ikinciString = removeLastChar(ikinciString);;
                    }
                    ikinciSayi = Float.parseFloat(ikinciString + "");

                    if(plus==true) {
                        sonuc.setText(birinciSayi + ikinciSayi + "");
                    }
                    else if(minus==true) {
                        sonuc.setText(birinciSayi - ikinciSayi + "");
                    }
                    else if(multi==true) {
                        if(ikinciSayi ==0){
                            sonuc.setText(birinciSayi +"");
                        }
                        else {
                            sonuc.setText(birinciSayi * ikinciSayi + "");
                        }
                    }
                    else if(div==true) {
                        if(ikinciSayi ==0){
                            sonuc.setText(birinciSayi +"");
                        }
                        else {
                            sonuc.setText(birinciSayi / ikinciSayi + "");
                        }
                    }
                }
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birinciSayi = Float.parseFloat(sonuc.getText() + "");
                plus = true;
                islem.setText(islem.getText()+"+");
                islemString = islem.getText().toString();
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birinciSayi = Float.parseFloat(sonuc.getText() + "");
                minus = true;
                islem.setText(islem.getText()+"-");
                islemString = islem.getText().toString();
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birinciSayi = Float.parseFloat(sonuc.getText() + "");
                div = true;
                islem.setText(islem.getText()+"/");
                islemString = islem.getText().toString();
            }
        });

        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birinciSayi = Float.parseFloat(sonuc.getText() + "");
                multi = true;
                islem.setText(islem.getText()+"x");
                islemString = islem.getText().toString();
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equalKontrol = Float.parseFloat(sonuc.getText() + "");
                if(equalKontrol !=0) {
                    islem.setTextSize(35);
                    sonuc.setTextSize(60);
                }
            }
        });
    }

    public void rakam(String number) {
        sonucKontrol = Float.parseFloat(sonuc.getText() + "");
        if (sonucKontrol == 0) {
            islemString =number;
            islem.setText(islemString);
            sonuc.setText(number);
        } else if (plus == true) {
            delete=true;
            islemString += number;
            islem.setText(islemString);
            ikinciString += number;
            ikinciSayi = Float.parseFloat(ikinciString + "");
            sonuc.setText(birinciSayi + ikinciSayi + "");
        } else if (minus == true) {
            delete=true;
            islemString += number;
            islem.setText(islemString);
            ikinciString += number;
            ikinciSayi = Float.parseFloat(ikinciString + "");
            sonuc.setText(birinciSayi - ikinciSayi + "");
        } else if (div == true) {
            delete=true;
            islemString += number;
            islem.setText(islemString);
            ikinciString += number;
            ikinciSayi = Float.parseFloat(ikinciString + "");
            sonuc.setText(birinciSayi / ikinciSayi + "");
        } else if (multi == true) {
            delete=true;
            islemString += number;
            islem.setText(islemString);
            ikinciString += number;
            ikinciSayi = Float.parseFloat(ikinciString + "");
            sonuc.setText(birinciSayi * ikinciSayi + "");
        } else {
            sonuc.setText(sonuc.getText() + number);
            islemString += number;
            islem.setText(islemString);
        }
    }
    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }
    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }
}



