package com.zain.project5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Battleship extends AppCompatActivity {
    private final String WEBSITE = "https://www.battleshipnewjersey.org";
    ImageView image;
    TextView desc;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.museum_view);
        image = findViewById(R.id.museum_image);
        desc = findViewById(R.id.museum_desc);
        location = findViewById(R.id.museum_loc);

        location.setText(getString(R.string.battleship_loc));
        desc.setText(getString(R.string.battleship_desc));
        image.setImageResource(R.drawable.battleship);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(WEBSITE);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}
