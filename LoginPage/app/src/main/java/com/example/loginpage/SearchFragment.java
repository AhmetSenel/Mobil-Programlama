package com.example.loginpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private SearchView searchView;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.searchView);
        imageView = view.findViewById(R.id.imageView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchImages(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    private void searchImages(String query) {
        // Arama sorgusuyla eşleşen drawable kaynağını bulun
        int resourceId = getResources().getIdentifier(query, "drawable", requireContext().getPackageName());

        if (resourceId != 0) {
            // Drawable kaynağı bulundu
            imageView.setImageResource(resourceId);
        } else {
            // Drawable kaynağı bulunamadı
            imageView.setImageDrawable(null);
        }
    }
}
