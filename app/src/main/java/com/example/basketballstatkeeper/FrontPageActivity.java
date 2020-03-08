package com.example.basketballstatkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FrontPageActivity extends AppCompatActivity {

    Button gameLogButton;
    Button analyticsButton;

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

            }
        });
    }
}
