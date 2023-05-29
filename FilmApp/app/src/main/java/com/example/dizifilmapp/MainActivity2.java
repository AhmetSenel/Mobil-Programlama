package com.example.dizifilmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavi = findViewById(R.id.bnavi);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new HomeFragment()).commit();

        bottomNavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new HomeFragment()).commit();
                } else if (item.getItemId()==R.id.list) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new ListFragment()).commit();
                } else if (item.getItemId()==R.id.person) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new PersonFragment()).commit();
                }
                return true;
            }

        });
    }

    public void onBackPressed(){
        Intent geriIntent=new Intent(MainActivity2.this,MainActivity.class);
        finish();
        startActivity(geriIntent);
    }
}