package com.zain.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView museums;
    String[] museumsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        museums = findViewById(R.id.museums);
        museumsList = new String[]{getString(R.string.battleship_name)};
//        ArrayAdapter<String> museumAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,museumsList);
        ArrayAdapter<String> museumAdapter = new ArrayAdapter<>(this, R.layout.list_item, museumsList);
        museums.setAdapter(museumAdapter);
        museums.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String museum = parent.getItemAtPosition(position).toString();
//        String museum = museumsList[position];
//        String museum = ((TextView) view).getText().toString();
//        Toast.makeText(getApplicationContext(), "Clicked: " + museum, Toast.LENGTH_SHORT).show();
        Intent newView = new Intent(MainActivity.this, Battleship.class);
        MainActivity.this.startActivity(newView);
    }
}