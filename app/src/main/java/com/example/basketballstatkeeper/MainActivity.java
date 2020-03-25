package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner playerNamesSpinner;

    EditText minutesField;
    EditText pointsField;
    EditText assistsField;
    EditText reboundsField;
    EditText stealsField;
    EditText blocksField;
    EditText turnoversField;

    Button submitButton;
    Button addGameButton;
    EditText gameNumberField;


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");

    // added in order to make your TextChangedListener work
    int numGames;
    ArrayList<String> playerNames = new ArrayList<>();
    int playerIndex;

    Boolean initialLoadCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO:
        /**
         * This was me adding a player / games to a DB. When you first pull this version you will need
         * to un-comment this and run this once to setup your DB how I had this working
         */
        playerNamesSpinner = findViewById(R.id.playerNamesSpinnerMain);

        initializeTextFields();

        pullDBData();

        initializeData();

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 changed this call to updateDB to take the gameIndex with -1 on the end because array
                 lists start at 0
                 */
                updateDatabase(Integer.parseInt(gameNumberField.getText().toString()) - 1);
            }
        });

        addGameButton = findViewById(R.id.addGameButton);
        addGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("NUM GAMES BEFORE UDB: " + numGames);
                updateDatabase(numGames);
                Toast.makeText(MainActivity.this, "Success! You now have " + (numGames+1) + " games saved.", Toast.LENGTH_SHORT).show();
                System.out.println("NUM GAMES AFTER UDB: " + numGames);

            }
        });


        /*
        Made this block of code so that the user cannot put an input in that would crash the app
        and this allows for the screen to be updated dynamically
         */
        gameNumberField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                if (!c.toString().matches("")) {
                    int gameIndex = Integer.parseInt(c.toString());
                    if (gameIndex <= numGames) {
                        initializeData();
                    } else {
                        Toast.makeText(MainActivity.this, "You only have " + numGames + " saved games.", Toast.LENGTH_SHORT).show();
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

        playerNamesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = playerNamesSpinner.getSelectedItem().toString();
                if(!name.matches("")){
                    initializeData();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        System.out.println("DONE WITH ONCREATE METHOD");
    }

    public void initializeTextFields() {
        minutesField = findViewById(R.id.editTextMinutes);
        pointsField = findViewById(R.id.editTextPoints);
        assistsField = findViewById(R.id.editTextAssists);
        reboundsField = findViewById(R.id.editTextRebounds);
        stealsField = findViewById(R.id.editTextSteals);
        blocksField = findViewById(R.id.editTextBlocks);
        turnoversField = findViewById(R.id.editTextTO);
        gameNumberField = findViewById(R.id.gameNumber);
    }


    public void pullDBData(){
        final DatabaseReference teamRef = dbRef.child("My Team");
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(initialLoadCheck){ //should only run once.  Will add duplicates to spinner any time data is changed
                    Team team = dataSnapshot.getValue(Team.class);
                    for (int p = 0; p < team.getNumPlayers(); p++){
                        playerNames.add(team.getPlayer(p).getName());
                    }
                    ArrayAdapter<String> adp = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_spinner_dropdown_item,playerNames);
                    playerNamesSpinner.setAdapter(adp);
                    initialLoadCheck = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initializeData() {
        /*
         making a reference to the current player..which can be anything later on and you would need
         to pass a string value into where "Max" currently is..same for the team value
         "Max" -> currentPlayer
         "My Team" -> currentTeam
         */
        DatabaseReference teamRef = dbRef.child("My Team");
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                Player player = team.getPlayer(playerNamesSpinner.getSelectedItemPosition());
                playerIndex = playerNamesSpinner.getSelectedItemPosition();
                numGames = player.getGames().size();
                // offset by 1 because of arrays starting at 0 and passing the game corresponding to the game number
                if(Integer.parseInt(gameNumberField.getText().toString()) > numGames){
                    gameNumberField.setText(numGames+"");
                    updateTextFields(player.getGame(numGames - 1));

                }
                else {
                    updateTextFields(player.getGame(Integer.parseInt(gameNumberField.getText().toString()) - 1));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*
    pretty simple to understand and EditText requires a string value so I just added "" + on the front
     */
    public void updateTextFields(Game p) {
        minutesField.setText("" + p.getMinutesPlayed());
        pointsField.setText("" + p.getPoints());
        assistsField.setText("" + p.getAssists());
        reboundsField.setText("" + p.getRebounds());
        stealsField.setText("" + p.getSteals());
        blocksField.setText("" + p.getBlocks());
        turnoversField.setText("" + p.getTurnovers());

    }


    /*
    This is how you can update the current team player Max's values of the current game
     */
    public void updateDatabase(int gameIndex) {
        DatabaseReference playerRef = dbRef.child("My Team").child("players").child(String.valueOf(playerIndex));
        Game game;
        if(gameIndex > numGames || gameIndex < 0){
            Toast.makeText(MainActivity.this, "Invalid Game Index", Toast.LENGTH_SHORT).show();

        }
        if(gameIndex < numGames) {
            game = new Game(Integer.parseInt(minutesField.getText().toString()),
                    Integer.parseInt(pointsField.getText().toString()),
                    Integer.parseInt(assistsField.getText().toString()),
                    Integer.parseInt(reboundsField.getText().toString()),
                    Integer.parseInt(stealsField.getText().toString()),
                    Integer.parseInt(blocksField.getText().toString()),
                    Integer.parseInt(turnoversField.getText().toString()));
        }
        else{
            game = new Game(0,0,0,0,0,0,0);
        }

        playerRef.child("games").child(String.valueOf(gameIndex)).setValue(game);

    }


}
