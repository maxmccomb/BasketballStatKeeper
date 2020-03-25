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
    TextView playerOptionsText;


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


        playerNameEditText = findViewById(R.id.playerNameEditText);
        playerOptionsText = findViewById(R.id.playerNamesOptions);


        /*public void updatePlayerOptions(ArrayList<String> names){
            String newOptions = "";
            for (String s : names){
                newOptions = newOptions  + "-" + s + "\n";
            }
            //playerOptionsText.setText(newOptions);
        }*/

        gameLogButton = findViewById(R.id.goToGameLogsButton);
        gameLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoosePlayerActivity.this, MainActivity.class);
                String playerName = playerNameEditText.getText().toString();
                if (playerName.matches("")) {
                    Toast.makeText(ChoosePlayerActivity.this, "Please enter in a valid player name", Toast.LENGTH_SHORT).show();
                } else {
                    i.putExtra("playerName", playerName);
                    startActivity(i);
                }
            }
        });


    }
}

