package com.example.basketballstatkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText minutesField;
    EditText pointsField;
    EditText assistsField;
    EditText reboundsField;
    EditText stealsField;
    EditText blocksField;
    EditText turnoversField;
    TextView titleText;


    Button submitButton;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference gameRef = db.getReference("Game");
    DatabaseReference minutesRef = gameRef.child("Minutes");
    DatabaseReference pointsRef = gameRef.child("Points");
    DatabaseReference assistsRef = gameRef.child("Assists");
    DatabaseReference reboundsRef = gameRef.child("Rebounds");
    DatabaseReference stealsRef = gameRef.child("Steals");
    DatabaseReference blocksRef = gameRef.child("Blocks");
    DatabaseReference turnoversRef = gameRef.child("Turnovers");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeTextFields();


        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateDatabase();
            }
        });

    }

    public void initializeTextFields(){
        minutesField = findViewById(R.id.editTextMinutes);
        pointsField = findViewById(R.id.editTextPoints);
        assistsField = findViewById(R.id.editTextAssists);
        reboundsField = findViewById(R.id.editTextRebounds);
        stealsField = findViewById(R.id.editTextSteals);
        blocksField = findViewById(R.id.editTextBlocks);
        turnoversField = findViewById(R.id.editTextTO);
        titleText = findViewById(R.id.titleText);

        initializeTextFieldValues();
    }

    public void initializeTextFieldValues(){
        
    }

    public void updateDatabase(){
        int minutes = Integer.parseInt(minutesField.getText().toString());
        int points = Integer.parseInt(pointsField.getText().toString());
        int assists = Integer.parseInt(assistsField.getText().toString());
        int rebounds = Integer.parseInt(reboundsField.getText().toString());
        int steals = Integer.parseInt(stealsField.getText().toString());
        int blocks = Integer.parseInt(blocksField.getText().toString());
        int turnovers = Integer.parseInt(turnoversField.getText().toString());

        minutesRef.setValue(minutes);
        pointsRef.setValue(points);
        assistsRef.setValue(assists);
        reboundsRef.setValue(rebounds);
        stealsRef.setValue(steals);
        blocksRef.setValue(blocks);
        turnoversRef.setValue(turnovers);

        Toast.makeText(this, "Database Updated!", Toast.LENGTH_SHORT).show();
    }
}
