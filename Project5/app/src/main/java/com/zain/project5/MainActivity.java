package com.zain.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView museums;
    String[] museumsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        museums = findViewById(R.id.museums);
        museumsList = getResources().getStringArray(R.array.museum_list);
        ArrayAdapter<String> museumAdapter = new ArrayAdapter<>(this, R.layout.list_item, museumsList);
        museums.setAdapter(museumAdapter);
        museums.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent newView;
        switch(position){
            case 0:
                newView = new Intent(MainActivity.this, Battleship.class);
                MainActivity.this.startActivity(newView);
                break;
            case 1:
                newView = new Intent(MainActivity.this, Sculpture.class);
                MainActivity.this.startActivity(newView);
                break;
            case 2:
                newView = new Intent(MainActivity.this, Maritime.class);
                MainActivity.this.startActivity(newView);
                break;
            case 3:
                newView = new Intent(MainActivity.this, Newark.class);
                MainActivity.this.startActivity(newView);
                break;
            case 4:
                newView = new Intent(MainActivity.this, Allaire.class);
                MainActivity.this.startActivity(newView);
                break;
            case 5:
                newView = new Intent(MainActivity.this, Lighthouse.class);
                MainActivity.this.startActivity(newView);
                break;
        }
    }
}