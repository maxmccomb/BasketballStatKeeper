package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText minutesField;
    EditText pointsField;
    EditText assistsField;
    EditText reboundsField;
    EditText stealsField;
    EditText blocksField;
    EditText turnoversField;

    Button submitButton;
    EditText gameNumberField;


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference playerNumRef = db.getReference("Players");

    ArrayList<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeTextFields();
        initializeData();
        updateTextFields(0);

        /*System.out.println(" Min: "+player.getGame(0).getMinutesPlayed());
        System.out.println("Point: "+player.getGame(0).getPoints());
        System.out.println("A: "+player.getGame(0).getAssists());
        System.out.println("R: "+player.getGame(0).getRebounds());
        System.out.println("S: "+player.getGame(0).getSteals());
        System.out.println("B: "+player.getGame(0).getBlocks());
        System.out.println("T: "+player.getGame(0).getTurnovers());*/


        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateDatabase();
            }
        });

        gameNumberField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                if (!c.toString().matches("")){
                    int gameIndex = Integer.parseInt(c.toString());
                    if (gameIndex < playerList.get(0).getNumGames()) {
                        updateTextFields(gameIndex-1);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "You only have " + playerList.get(0).getNumGames() + " saved games.", Toast.LENGTH_SHORT).show();
                    }
                }
            }


            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        System.out.println("DONE WITH ONCREATE METHOD");
    }

    public void initializeTextFields(){
        minutesField = findViewById(R.id.editTextMinutes);
        pointsField = findViewById(R.id.editTextPoints);
        assistsField = findViewById(R.id.editTextAssists);
        reboundsField = findViewById(R.id.editTextRebounds);
        stealsField = findViewById(R.id.editTextSteals);
        blocksField = findViewById(R.id.editTextBlocks);
        turnoversField = findViewById(R.id.editTextTO);
        gameNumberField = findViewById(R.id.gameNumber);
    }

    public void initializeData() {
        playerNumRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.getValue(Player.class);
                playerList.add(player);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateTextFields(int gameIndex){
        minutesField.setText(""+ playerList.get(0).getGame(gameIndex).getMinutesPlayed());
        pointsField.setText(""+ playerList.get(0).getGame(gameIndex).getPoints());
        assistsField.setText(""+ playerList.get(0).getGame(gameIndex).getAssists());
        reboundsField.setText(""+ playerList.get(0).getGame(gameIndex).getRebounds());
        stealsField.setText(""+ playerList.get(0).getGame(gameIndex).getSteals());
        blocksField.setText(""+ playerList.get(0).getGame(gameIndex).getBlocks());
        turnoversField.setText(""+ playerList.get(0).getGame(gameIndex).getTurnovers());
    }


    public void updateDatabase(){
        //this might be wrong
        playerNumRef.setValue(playerList.get(0));
        Toast.makeText(this, "Database Updated!", Toast.LENGTH_SHORT).show();
    }


}
