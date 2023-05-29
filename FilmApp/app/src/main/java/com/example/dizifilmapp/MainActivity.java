package com.example.dizifilmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText txtKullanici, txtSifre;
    private Button btnGiris, btnKayit;
    private String kullaniciAdi,sifre;
    private FirebaseAuth mAuth;

    private Button adminGiris;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtKullanici = findViewById(R.id.kullaniciAdi);
        txtSifre = findViewById(R.id.sifre);
        btnGiris = findViewById(R.id.buttonGiris);
        btnKayit = findViewById(R.id.buttonKayit);
        adminGiris = findViewById(R.id.buttonAdmin);

        mAuth = FirebaseAuth.getInstance();

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kullaniciAdi = txtKullanici.getText().toString();
                sifre = txtSifre.getText().toString();
                if (!TextUtils.isEmpty(kullaniciAdi) && !TextUtils.isEmpty(sifre)) {
                    mAuth.signInWithEmailAndPassword(kullaniciAdi, sifre)
                            .addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser kullanici = mAuth.getCurrentUser();
                                    if (kullanici != null) {
                                        Toast.makeText(MainActivity.this, "Giriş yapıldı: " + kullanici.getEmail(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Giriş yapıldı", Toast.LENGTH_SHORT).show();
                                    }
                                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(MainActivity.this, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "Giriş yapılamadı ", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kullaniciAdi = txtKullanici.getText().toString();
                sifre = txtSifre.getText().toString();
                if(!TextUtils.isEmpty(kullaniciAdi) && !TextUtils.isEmpty(sifre)){
                    mAuth.createUserWithEmailAndPassword(kullaniciAdi,sifre)
                            .addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(MainActivity.this, "Kayıt olundu" , Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


        adminGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

    }
}