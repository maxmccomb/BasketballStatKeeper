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

    //displays the player names on the team
    Spinner playerNamesSpinner;

    //fields to edit players stats in each category
    EditText minutesField;
    EditText pointsField;
    EditText assistsField;
    EditText reboundsField;
    EditText stealsField;
    EditText blocksField;
    EditText turnoversField;

    Button submitButton;
    Button addGameButton;

    //allows the user to edit different games.  Text field for game numbers
    EditText gameNumberField;

    //Firebase references
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");

    //number of games the player has played
    int numGames;
    //
    ArrayList<String> playerNames = new ArrayList<>();
    int playerIndex;

    //only used in pullDBData() method
    Boolean initialLoadCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerNamesSpinner = findViewById(R.id.playerNamesSpinnerMain);

        initializeTextFields();

        pullDBData();

        initializeData();

        //when called, calls updateDatabase method to change values to what was in the EditTexts
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gameIndex = Integer.parseInt(gameNumberField.getText().toString()) - 1;
                if(gameIndex <= numGames){
                    updateDatabase(gameIndex);
                }
                else{
                    Toast.makeText(MainActivity.this, "You only have " + numGames + " saved games.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
            calls update database with an index out of bounds to create a new entry in the database
             then displays a message saying the operation was successful
         */
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
            when the game index is changed, calls the initializeData method to update what is
             displayed.  if the desired game index is out of bounds, a message will display
             telling the user that they only have ___ saved games.
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
                // this space intentionally left blank
            }
        });

        /*
            when the selected item changes in the spinner, calls the initializeData method to
             change what is displayed
         */
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
                // this space intentionally left blank
            }
        });

        System.out.println("DONE WITH ONCREATE METHOD");
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
                // this space intentionally left blank
            }
        });
    }

    /*
        Any time a value is changed in player name or game index, this method is called.  Grabs
         data from the database and passes it on to methods to update the text fields. Offset
         by -1 for a better display to the user.
     */
    public void initializeData() {
        DatabaseReference teamRef = dbRef.child("My Team");
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                Player player = team.getPlayer(playerNamesSpinner.getSelectedItemPosition());
                playerIndex = playerNamesSpinner.getSelectedItemPosition();
                numGames = player.getGames().size();
                /*when switching players...  They may not have the same number of games
                    if the new player and the current game index do not match up, then
                    the game stats of their latest game will be displayed
                */
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
                // this space intentionally left blank
            }
        });
    }

    /*
        sets the text fields to the correct values when the player or game number changes
        @param g - the game to be displayed
     */
    public void updateTextFields(Game g) {
        minutesField.setText("" + g.getMinutesPlayed());
        pointsField.setText("" + g.getPoints());
        assistsField.setText("" + g.getAssists());
        reboundsField.setText("" + g.getRebounds());
        stealsField.setText("" + g.getSteals());
        blocksField.setText("" + g.getBlocks());
        turnoversField.setText("" + g.getTurnovers());

    }


    /*
        updates data in the database to what is entered in the text fields.
        @param gameIndex - the index of the current game to be updated.  If the index is
         greater than the number of games, it will create a new entry in the database
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
        else{  //a new entry will be created here with a game object initialized to 0s for stats
            game = new Game(0,0,0,0,0,0,0);
        }

        playerRef.child("games").child(String.valueOf(gameIndex)).setValue(game);

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
}
