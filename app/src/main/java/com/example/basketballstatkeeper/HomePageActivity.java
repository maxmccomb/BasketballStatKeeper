package com.example.basketballstatkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePageActivity extends AppCompatActivity {

    Button continueButton;
    Button newTeamButton;
    Button aboutButton;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        continueButton = findViewById(R.id.continueWithOldTeamButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, FrontPageActivity.class);
                startActivity(i);
            }
        });

        newTeamButton = findViewById(R.id.createANewTeamButton);
        newTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.setValue(null);
                Intent i = new Intent(HomePageActivity.this, FrontPageActivity.class);
                startActivity(i);
            }
        });

        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, aboutActivity.class);
                startActivity(i);
            }
        });

    }
}
