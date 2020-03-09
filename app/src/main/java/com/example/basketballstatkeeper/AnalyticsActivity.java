package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class AnalyticsActivity extends AppCompatActivity {

    Spinner spinner;

    TextView minutesDisplay;
    TextView pointsDisplay;
    TextView assistsDisplay;
    TextView reboundsDisplay;
    TextView stealsDisplay;
    TextView blocksDisplay;
    TextView turnoversDisplay;

    DecimalFormat df = new DecimalFormat("##0.00");


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

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        //NEED TO INITIALIZE THE TEXT FIELDS
        //NEED TO CREATE TEXT FIELDS
        initializeData();
        setTotals();

        spinner = findViewById(R.id.analyticsSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dataToDisplay = spinner.getSelectedItem().toString();
                if(dataToDisplay.equals("Totals")){
                    setTotals();
                }
                if(dataToDisplay.equals("Averages")){
                    setAverages();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setTotals(){
        minutesDisplay.setText(""+ player.getTotalMinutesPlayed());
        pointsDisplay.setText("" + player.getTotalPoints());
        assistsDisplay.setText("" + player.getTotalAssists());
        reboundsDisplay.setText("" + player.getTotalRebounds());
        stealsDisplay.setText("" + player.getTotalSteals());
        blocksDisplay.setText("" + player.getTotalBlocks());
        turnoversDisplay.setText(""+player.getTotalTurnovers());
    }

    public void setAverages(){
        minutesDisplay.setText("" + df.format(player.getAverageMinutesPlayed()));
        pointsDisplay.setText("" + df.format(player.getAveragePoints()));
        assistsDisplay.setText("" + df.format(player.getAverageAssists()));
        reboundsDisplay.setText("" + df.format(player.getAverageRebounds()));
        stealsDisplay.setText("" + df.format(player.getAverageSteals()));
        blocksDisplay.setText("" + df.format(player.getAverageBlocks()));
        turnoversDisplay.setText("" + df.format(player.getAverageTurnovers()));

    }

    public void initializeData() {
        System.out.println("NUM GAMES AFTER " + numGames);
        Game game = new Game(0, 0, 0, 0, 0, 0, 0);
        player = new Player(game);
        player.addGame(game);
        initializeGameStats(0);
        if (numGames > 1) {
            for (int i = 1; i < numGames; i++) {
                player.addGame(new Game (0,0,0,0,0,0,0));
                updateDatabaseReferences(i + 1);
                initializeGameStats(i);
            }
        }
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

    public void initializeGameStats(final int gameIndex){
        minutesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setMinutesPlayed(Integer.parseInt(""+dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        pointsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setPoints(Integer.parseInt(""+dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        assistsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setAssists(Integer.parseInt(""+dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reboundsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setRebounds(Integer.parseInt(""+dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        stealsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setSteals(Integer.parseInt(""+dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        blocksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setBlocks(Integer.parseInt(""+dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        turnoversRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player.getGame(gameIndex).setTurnovers(Integer.parseInt(""+dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initializeTextFields(){

    }
}
