package com.example.loginpage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class PersonFragment extends Fragment {
    private EditText adEditText, soyadEditText, kullaniciAdiEditText, sifreEditText, telefonEditText, dogumTarihiEditText;
    private Button guncelleButton;
    private SharedPreferences sharedPreferences;

    public PersonFragment() {
        // Boş bir yapıcı metot gerekiyor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        adEditText = view.findViewById(R.id.ad);
        soyadEditText = view.findViewById(R.id.soyad);
        kullaniciAdiEditText = view.findViewById(R.id.kullaniciAdi);
        sifreEditText = view.findViewById(R.id.sifre);
        telefonEditText = view.findViewById(R.id.telefon);
        dogumTarihiEditText = view.findViewById(R.id.dogumTarihi);
        guncelleButton = view.findViewById(R.id.guncelle);

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", 0);

        guncelleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guncelle butonuna tıklanınca yapılacak işlemler
                String ad = adEditText.getText().toString();
                String soyad = soyadEditText.getText().toString();
                String kullaniciAdi = kullaniciAdiEditText.getText().toString();
                String sifre = sifreEditText.getText().toString();
                String telefon = telefonEditText.getText().toString();
                String dogumTarihi = dogumTarihiEditText.getText().toString();

                // SharedPreferences kullanarak verileri kaydet
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ad", ad);
                editor.putString("soyad", soyad);
                editor.putString("kullaniciAdi", kullaniciAdi);
                editor.putString("sifre", sifre);
                editor.putString("telefon", telefon);
                editor.putString("dogumTarihi", dogumTarihi);
                editor.apply();

                Toast.makeText(requireContext(), "Bilgiler kaydedildi.", Toast.LENGTH_SHORT).show();
            }
        });

        // Kaydedilmiş verileri al ve EditText alanlarına yerleştir
        String ad = sharedPreferences.getString("ad", "");
        String soyad = sharedPreferences.getString("soyad", "");
        String kullaniciAdi = sharedPreferences.getString("kullaniciAdi", "");
        String sifre = sharedPreferences.getString("sifre", "");
        String telefon = sharedPreferences.getString("telefon", "");
        String dogumTarihi = sharedPreferences.getString("dogumTarihi", "");

        adEditText.setText(ad);
        soyadEditText.setText(soyad);
        kullaniciAdiEditText.setText(kullaniciAdi);
        sifreEditText.setText(sifre);
        telefonEditText.setText(telefon);
        dogumTarihiEditText.setText(dogumTarihi);

        return view;
    }
}





