package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class ChoosePlayerActivity extends AppCompatActivity {

    Button gameLogButton;
    EditText playerNameEditText;
    Button addPlayerButton;


    Button addNewPlayerButton;
    Button cancelButton;
    EditText newPlayerNameEditText;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");

    int numPlayers;

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
        pullDBData(); //initializes numPlayers

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

        newPlayerNameEditText = findViewById(R.id.newPlayerNameEditText);
        newPlayerNameEditText.setVisibility(View.INVISIBLE);

        addNewPlayerButton = findViewById(R.id.addPlayerSubmit);
        addNewPlayerButton.setVisibility(View.INVISIBLE);

        cancelButton = findViewById(R.id.cancelAddingPlayerButton);
        cancelButton.setVisibility(View.INVISIBLE);

        addNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = newPlayerNameEditText.getText().toString();
                if(enteredName.matches("")){
                    Toast.makeText(ChoosePlayerActivity.this, "Please enter a player name", Toast.LENGTH_SHORT).show();
                }
                else{
                    addPlayerToDB(enteredName);
                    newPlayerNameEditText.setVisibility(View.INVISIBLE);
                    addNewPlayerButton.setVisibility(View.INVISIBLE);
                    cancelButton.setVisibility(View.INVISIBLE);
                    addPlayerButton.setVisibility(View.VISIBLE);
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPlayerNameEditText.setVisibility(View.INVISIBLE);
                addNewPlayerButton.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.INVISIBLE);
                addPlayerButton.setVisibility(View.VISIBLE);
            }
        });

        //Check for intent index here to see if it came from the analytics activity

        addPlayerButton = findViewById(R.id.addPlayerButton);
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayerButton.setVisibility(View.INVISIBLE);
                newPlayerNameEditText.setVisibility(View.VISIBLE);
                addNewPlayerButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);
            }
        });

    }

    public void pullDBData(){
        final DatabaseReference teamRef = dbRef.child("Augustana Vikings");
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                numPlayers = team.getNumPlayers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addPlayerToDB(final String n){
        DatabaseReference teamRef = dbRef.child("Augustana Vikings");
        ArrayList<Game> gs = new ArrayList<>();
        gs.add(new Game(0,0,0,0,0,0,0));
        Player p = new Player(gs, n);
        teamRef.child("players").child(String.valueOf(numPlayers)).setValue(p);
    }
}
