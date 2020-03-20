package com.example.basketballstatkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChoosePlayerActivity extends AppCompatActivity {

    Button gameLogButton;
    EditText playerNameEditText;
    Button addPlayerButton;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);


        // TODO:
        /**
         * This was me adding a player / games to a DB. When you first pull this version you will need
         * to un-comment this and run this once to setup your DB how I had this working
         */
        ArrayList<Game> games = new ArrayList<>();
        Game game1 = new Game(1,1,1,1,1,1,1);
        Game game2 = new Game(0,0,0,0,0,0,0);
        Game game3 = new Game(1,1,1,1,1,1,1);
        games.add(game1);
        games.add(game2);
        games.add(game3);
        Player player = new Player(games, "Max");
        //dbRef.child("Augustana Vikings").child("Players").child("Max").setValue(player);

       ArrayList<Game> games2 = new ArrayList<>();
        Game game11 = new Game(1,1,1,1,1,1,1);
        Game game22 = new Game(0,0,0,0,0,0,0);
        games2.add(game11);
        games2.add(game22);
        Player player2 = new Player(games2, "Dylan");
        //dbRef.child("Augustana Vikings").child("Players").child("Dylan").setValue(player2);

        ArrayList<Player> ps = new ArrayList<>();
        ps.add(player);
        ps.add(player2);
        Team team = new Team(ps);

        dbRef.child("Augustana Vikings").setValue(team);


        playerNameEditText = findViewById(R.id.playerNameEditText);

        gameLogButton = findViewById(R.id.goToGameLogsButton);
        gameLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoosePlayerActivity.this, MainActivity.class);
                String playerName = playerNameEditText.getText().toString();
                if(playerName.matches("")){
                    Toast.makeText(ChoosePlayerActivity.this, "Please enter in a valid player name", Toast.LENGTH_SHORT).show();
                }
                else{
                    i.putExtra("playerName", playerName);
                    startActivity(i);
                }
            }
        });

        //Check for intent index here

        addPlayerButton = findViewById(R.id.addPlayerButton);
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
