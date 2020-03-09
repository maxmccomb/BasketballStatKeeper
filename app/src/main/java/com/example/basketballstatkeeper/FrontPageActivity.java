package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FrontPageActivity extends AppCompatActivity {

    Button gameLogButton;
    Button analyticsButton;


    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);


        gameLogButton = findViewById(R.id.gameLogButton);
        gameLogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontPageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        analyticsButton = findViewById(R.id.analyticsButton);
        analyticsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontPageActivity.this, AnalyticsActivity.class);
                startActivity(i);
            }
        });
    }

}

