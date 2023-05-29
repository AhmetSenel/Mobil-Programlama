package com.example.dizifilmapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private Context context;
    private GridView gridView;
    private ImageAdapter imageAdapter;
    private List<String> favoriteImageUrls = new ArrayList<>();

    private FirebaseDatabase database;
    private DatabaseReference favorilerRef;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        gridView = view.findViewById(R.id.gridView1);

        // Firebase Realtime Database referansını alın
        database = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        favorilerRef = database.getReference().child("favoriler").child(uid);

        // Favori resimleri al
        favorilerRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String imageUrl = snapshot.child("imageUrl").getValue(String.class);
                favoriteImageUrls.add(imageUrl);
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Favori resimlerde değişiklik olduğunda yapılacak işlemler
                String imageUrl = snapshot.child("imageUrl").getValue(String.class);
                if (favoriteImageUrls.contains(imageUrl)) {
                    // Favori resim güncellendi
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Favori resim silindiğinde yapılacak işlemler
                String imageUrl = snapshot.child("imageUrl").getValue(String.class);
                if (favoriteImageUrls.contains(imageUrl)) {
                    favoriteImageUrls.remove(imageUrl);
                    imageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Favori resim taşındığında yapılacak işlemler
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Favori resimler alınamadı: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // ImageAdapter'i oluşturun ve GridView'e atayın
        imageAdapter = new ImageAdapter(context, favoriteImageUrls);
        gridView.setAdapter(imageAdapter);

        return view;
    }
}



