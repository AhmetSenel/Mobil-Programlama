package com.example.dizifilmapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Context context;
    private GridView gridView;
    private ImageAdapter imageAdapter;
    private List<String> imageUrls = new ArrayList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = view.findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(context, imageUrls);
        gridView.setAdapter(imageAdapter);

        // Firebase Storage referansını alın
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://filmapp1-a7328.appspot.com/");

        // "filmler" klasöründeki tüm resimleri listelemek için bir referans oluşturun
        StorageReference filmlerRef = storageRef.child("filmler");

        // "filmler" klasöründeki tüm resimleri almak için listAll() yöntemini kullanın
        filmlerRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                // ListResult'dan resimlerin referanslarını alın ve URL'lerini oluşturun
                for (StorageReference item : listResult.getItems()) {
                    item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // URL'yi alın
                            String imageUrl = uri.toString();

                            // URL'yi imageUrls listesine ekleyin
                            imageUrls.add(imageUrl);

                            // Adapteri güncelleyin
                            imageAdapter.notifyDataSetChanged();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // İndirme başarısız ise hata mesajı
                            Toast.makeText(context, "Resim indirme hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Listeleme başarısız ise hata meesajı
                Toast.makeText(context, "Resim listeleme hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}


