package com.example.dizifilmapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PersonFragment extends Fragment {

    private EditText txtAd, txtSoyad, txtKullaniciAdi, txtTelefon, txtDogumTarihi;
    private Button btnGuncelle;
    private DatabaseReference kullaniciRef;
    private FirebaseUser currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        txtAd = view.findViewById(R.id.ad);
        txtSoyad = view.findViewById(R.id.soyad);
        txtKullaniciAdi = view.findViewById(R.id.kullaniciAdi);
        txtTelefon = view.findViewById(R.id.telefon);
        txtDogumTarihi = view.findViewById(R.id.dogumTarihi);
        btnGuncelle = view.findViewById(R.id.guncelle);

        // Firebase kullanıcısı ve veritabanı referansını al
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        kullaniciRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());

        // Kullanıcının mevcut bilgilerini alıp doldur
        getUserInfo();

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bilgileriGüncelle();
            }
        });

        return view;
    }

    private void getUserInfo() {
        kullaniciRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String ad = snapshot.child("ad").getValue().toString();
                    String soyad = snapshot.child("soyad").getValue().toString();
                    String kullaniciAdi = snapshot.child("kullaniciAdi").getValue().toString();
                    String telefon = snapshot.child("telefon").getValue().toString();
                    String dogumTarihi = snapshot.child("dogumTarihi").getValue().toString();

                    txtAd.setText(ad);
                    txtSoyad.setText(soyad);
                    txtKullaniciAdi.setText(kullaniciAdi);
                    txtTelefon.setText(telefon);
                    txtDogumTarihi.setText(dogumTarihi);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Veritabanı işlemi iptal edildiğinde yapılacak işlemler
            }
        });
    }

    private void bilgileriGüncelle() {
        String ad = txtAd.getText().toString();
        String soyad = txtSoyad.getText().toString();
        String kullaniciAdi = txtKullaniciAdi.getText().toString();
        String telefon = txtTelefon.getText().toString();
        String dogumTarihi = txtDogumTarihi.getText().toString();

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("ad", ad);
        userMap.put("soyad", soyad);
        userMap.put("kullaniciAdi", kullaniciAdi);
        userMap.put("telefon", telefon);
        userMap.put("dogumTarihi", dogumTarihi);

        kullaniciRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Veriler başarıyla güncellendiğinde yapılacak işlemler
                    Toast.makeText(getActivity(), "Bilgiler güncellendi.", Toast.LENGTH_SHORT).show();
                } else {
                    // Veri güncelleme başarısız oldu, hata durumunu işleyin
                    Toast.makeText(getActivity(), "Bilgiler güncellenirken hata oluştu.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
