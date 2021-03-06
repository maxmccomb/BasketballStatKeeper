package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class FrontPageActivity extends AppCompatActivity {

    //buttons that bring the user to a new activity
    Button gameLogButton;
    Button analyticsButton;

    //button to navigate to the about screen
    Button aboutButton;

    //buttons used to add a new player
    Button addPlayerButton;
    Button addNewPlayerButton;
    Button cancelButton;
    EditText newPlayerNameEditText;

    //number of players on your team
    int numPlayers;

    //References to Firebase
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        /*ArrayList<Game> games = new ArrayList<>();
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

        dbRef.child("My Team").setValue(team);*/

        //functionality to navigate to the about screen
        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontPageActivity.this, aboutActivity.class);
                startActivity(i);
            }
        });

        //must initialize them early so they can be set to invisible
        gameLogButton = findViewById(R.id.gameLogButton);
        analyticsButton = findViewById(R.id.analyticsButton);

        pullDBData();


        //starts the GameLog Activity (MainActivity.java)
        gameLogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontPageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //starts the Analytics Activity (AnalyticsActivity.java)
        analyticsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontPageActivity.this, AnalyticsActivity.class);
                startActivity(i);
            }
        });

        //initialization for buttons to add players.  Starts off invisible
        newPlayerNameEditText = findViewById(R.id.newPlayerNameEditText);
        newPlayerNameEditText.setVisibility(View.INVISIBLE);
        addNewPlayerButton = findViewById(R.id.addPlayerSubmit);
        addNewPlayerButton.setVisibility(View.INVISIBLE);
        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setVisibility(View.INVISIBLE);


        /*
            calls method to add a player to the database and sets the add player buttons to invisible
         */
        addNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = newPlayerNameEditText.getText().toString();
                if(enteredName.matches("")){
                    Toast.makeText(FrontPageActivity.this, "Please enter a player name", Toast.LENGTH_SHORT).show();
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

        /*
            hides the add player buttons.  Reverts to normal
         */
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPlayerNameEditText.setVisibility(View.INVISIBLE);
                addNewPlayerButton.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.INVISIBLE);
                addPlayerButton.setVisibility(View.VISIBLE);
            }
        });

        /*
            shows the add player buttons
         */
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



    /*
        Determines whether the app should allow the user to see the game logs and analytics
         depending on if there is valid data within Firebase
     */
    public void pullDBData(){
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //there is no data in Firebase
                if(dataSnapshot.child("My Team").exists() == false){
                    gameLogButton.setVisibility(View.INVISIBLE);
                    analyticsButton.setVisibility(View.INVISIBLE);
                }
                //there is existing data in Firebase
                else{
                    Team team = dataSnapshot.child("My Team").getValue(Team.class);
                    numPlayers = team.getNumPlayers();
                    gameLogButton.setVisibility(View.VISIBLE);
                    analyticsButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // this space intentionally left blank
            }
        });
    }


    /*
        adds a player the database
        @param n - the name of the new player
    */
    public void addPlayerToDB(final String n){
        DatabaseReference teamRef = dbRef.child("My Team");
        ArrayList<Game> games = new ArrayList<>();
        games.add(new Game(0,0,0,0,0,0,0));
        Player player = new Player(games, n);

        //if this is the first player being added we have to push a whole team to the database
        if(numPlayers == 0){
            ArrayList<Player> ps = new ArrayList<>();
            ps.add(player);
            Team team = new Team(ps);
            dbRef.child("My Team").setValue(team);
            gameLogButton.setVisibility(View.VISIBLE);
            analyticsButton.setVisibility(View.VISIBLE);
        }
        else{
            teamRef.child("players").child(String.valueOf(numPlayers)).setValue(player);
        }
    }


}

