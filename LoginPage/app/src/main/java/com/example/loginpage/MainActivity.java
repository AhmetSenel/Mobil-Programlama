package com.example.loginpage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText kullaniciAdi, sifre;
    private Button btnKayit, btnGiris, buttonReset;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Veritabanı bağlantısı için SQLiteOpenHelper kullanıyoruz
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        kullaniciAdi = findViewById(R.id.kullanıcıAdi);
        sifre = findViewById(R.id.sifre);
        btnKayit = findViewById(R.id.buttonKayit);
        btnGiris = findViewById(R.id.buttonGiris);
        buttonReset = findViewById(R.id.buttonAdmin);

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitOl();
            }
        });

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girisYap();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetData();
            }
        });
    }

    private void kayitOl() {
        String username = kullaniciAdi.getText().toString().trim();
        String password = sifre.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result != -1) {
            Toast.makeText(this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
            kullaniciAdi.setText("");
            sifre.setText("");
        } else {
            Toast.makeText(this, "Kayıt Hatası", Toast.LENGTH_SHORT).show();
        }
    }

    private void girisYap() {
        String username = kullaniciAdi.getText().toString().trim();
        String password = sifre.getText().toString().trim();

        String[] projection = {"username"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("users", projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            String savedUsername = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            Toast.makeText(this, savedUsername + " kullanıcısı giriş yaptı", Toast.LENGTH_SHORT).show();

            // Şifre kontrolü başarılı olduğunda MainActivity2'ye geçiş yap
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Giriş Başarısız", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }

    private void resetData() {
        db.delete("users", null, null);
        Toast.makeText(this, "Veriler Sıfırlandı", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "users.db";
        private static final int DATABASE_VERSION = 1;

        DatabaseHelper(MainActivity context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE users (username TEXT PRIMARY KEY, password TEXT)";
            db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS users");
            onCreate(db);
        }
    }
}
