package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChoosePlayerActivity extends AppCompatActivity {

    Button gameLogButton;
    EditText playerNameEditText;
    Button addPlayerButton;
    TextView playerOptionsText;


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
         * Initializes to Test data
         */
        ArrayList<Game> games = new ArrayList<>();
        Game game1 = new Game(41,22,2,7,1,0,4);
        Game game2 = new Game(42,37,7,4,2,0,5);
        Game game3 = new Game(43,25,6,8,3,0,3);
        games.add(game1);
        games.add(game2);
        games.add(game3);
        Player player = new Player(games, "Stephen Curry");
        //dbRef.child("My Team").child("Players").child("Max").setValue(player);

       ArrayList<Game> games2 = new ArrayList<>();
        Game game11 = new Game(39,9,2,2,0,1,1);
        Game game22 = new Game(40,12,3,2,0,0,1);
        Game game23 = new Game(24,5,5,2,0,1,2);

        games2.add(game11);
        games2.add(game22);
        games2.add(game23);
        Player player2 = new Player(games2, "Klay Thompson");

        ArrayList<Game> games3 = new ArrayList<>();
        Game game21 = new Game(39,22,8,0,1,0,1);
        Game game77 = new Game(42,14,8,7,3,0,0);
        Game game8 = new Game(25,25,5,5,2,0,0);

        games3.add(game21);
        games3.add(game77);
        games3.add(game8);
        Player player3 = new Player(games3, "Andre Iguodala");
        //dbRef.child("My Team").child("Players").child("Dylan").setValue(player2);

        ArrayList<Player> ps = new ArrayList<>();
        ps.add(player);
        ps.add(player2);
        ps.add(player3);
        Team team = new Team(ps);

        dbRef.child("My Team").setValue(team);


        playerNameEditText = findViewById(R.id.playerNameEditText);
        playerOptionsText = findViewById(R.id.playerNamesOptions);
        pullDBData(); //initializes numPlayers and the options for players


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
        final DatabaseReference teamRef = dbRef.child("My Team");
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                numPlayers = team.getNumPlayers();
                ArrayList <String> playerNames = new ArrayList<>();
                for (int p = 0; p < team.getNumPlayers(); p++){
                    playerNames.add(team.getPlayer(p).getName());
                }
                updatePlayerOptions(playerNames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updatePlayerOptions(ArrayList<String> names){
        String newOptions = "";
        for (String s : names){
            newOptions = newOptions  + "-" + s + "\n";
        }
        playerOptionsText.setText(newOptions);
    }

    public void addPlayerToDB(final String n){
        DatabaseReference teamRef = dbRef.child("My Team");
        ArrayList<Game> gs = new ArrayList<>();
        gs.add(new Game(0,0,0,0,0,0,0));
        Player p = new Player(gs, n);
        teamRef.child("players").child(String.valueOf(numPlayers)).setValue(p);
    }
}
