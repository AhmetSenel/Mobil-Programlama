package com.example.loginpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.fethiye
 */

public class HomeFragment extends Fragment {

    ListView listem;
    private Integer[] resim = {R.drawable.bolu,R.drawable.anitkabir,R.drawable.fethiye,R.drawable.kapadokya,R.drawable.kizkulesi,R.drawable.salda,R.drawable.vodafonepark,R.drawable.efes,R.drawable.pamukkale,R.drawable.yerebatansarnici,R.drawable.efes2};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listem = view.findViewById(R.id.listem);

        ResimAdapter adapter = new ResimAdapter(getContext(), resim);

        listem.setAdapter(adapter);

        return view;
    }
}