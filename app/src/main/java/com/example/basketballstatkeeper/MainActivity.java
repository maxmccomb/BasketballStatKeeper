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

    Player player;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference gameRef = db.getReference("Game"+"1");
    DatabaseReference minutesRef = gameRef.child("Minutes");
    DatabaseReference pointsRef = gameRef.child("Points");
    DatabaseReference assistsRef = gameRef.child("Assists");
    DatabaseReference reboundsRef = gameRef.child("Rebounds");
    DatabaseReference stealsRef = gameRef.child("Steals");
    DatabaseReference blocksRef = gameRef.child("Blocks");
    DatabaseReference turnoversRef = gameRef.child("Turnovers");

    DatabaseReference ngf = db.getReference("ng");
    DatabaseReference numGamesRef = ngf.child("numGame");
    final int numGames = 2;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pullNumGames();
        initializeTextFields();
        initializeData();



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
                    if (gameIndex < numGames+1) {
                        updateDatabaseReferences(gameIndex);
                        updateLocalData(gameIndex - 1);
                    }
                    else{
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

    public void initializeData(){
        System.out.println("NUM GAMES AFTER "+ numGames);
        Game game = new Game(0, 0, 0, 0, 0, 0, 0);
        player = new Player(game);
        updateLocalData(0);
        if(numGames > 1) {
            for (int i = 1; i < numGames; i++) {
                player.addGame(new Game (0,0,0,0,0,0,0));
                updateDatabaseReferences(i+1);
                updateLocalData(i);
            }
        }
        System.out.println("DONE WITH INITIALIZE DATA");
    }


    /*public void pullNumGames(){
        numGamesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("" + dataSnapshot.getValue());
                //numGames = (Integer.parseInt(""+dataSnapshot.getValue()));
                System.out.println("NUM GAMES INITIALIZED TO "+numGames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

    public void updateLocalData(final int gameIndex) {

        minutesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setMinutesPlayed(Integer.parseInt(""+dataSnapshot.getValue()));
                minutesField.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        pointsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setPoints(Integer.parseInt(""+dataSnapshot.getValue()));
                pointsField.setText(""+dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        assistsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setAssists(Integer.parseInt(""+dataSnapshot.getValue()));
                assistsField.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reboundsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setRebounds(Integer.parseInt(""+dataSnapshot.getValue()));
                reboundsField.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        stealsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setSteals(Integer.parseInt(""+dataSnapshot.getValue()));
                stealsField.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        blocksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setBlocks(Integer.parseInt(""+dataSnapshot.getValue()));
                blocksField.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        turnoversRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setTurnovers(Integer.parseInt(""+dataSnapshot.getValue()));
                turnoversField.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateDatabaseReferences(int index){
         gameRef = db.getReference("Game"+index);
         minutesRef = gameRef.child("Minutes");
         pointsRef = gameRef.child("Points");
         assistsRef = gameRef.child("Assists");
         reboundsRef = gameRef.child("Rebounds");
         stealsRef = gameRef.child("Steals");
         blocksRef = gameRef.child("Blocks");
         turnoversRef = gameRef.child("Turnovers");
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
