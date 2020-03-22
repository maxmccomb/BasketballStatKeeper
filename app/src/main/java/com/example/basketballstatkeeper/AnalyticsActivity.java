package com.example.basketballstatkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AnalyticsActivity extends AppCompatActivity {

    Spinner teamVsPlayerSpinner;
    Spinner playerNamesSpinner;
    Spinner totalsVsAverageSpinner;


    TextView minutesDisplay;
    TextView pointsDisplay;
    TextView assistsDisplay;
    TextView reboundsDisplay;
    TextView stealsDisplay;
    TextView blocksDisplay;
    TextView turnoversDisplay;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");

    DecimalFormat df = new DecimalFormat("###.00");

    int playerIndex;
    ArrayList<String> playerNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        initializeTextFields();
        pullDBData();

        teamVsPlayerSpinner = findViewById(R.id.teamVsPlayerSpinner);
        playerNamesSpinner = findViewById(R.id.playerNameSpinner);
        playerNamesSpinner.setVisibility(View.INVISIBLE);
        totalsVsAverageSpinner = findViewById(R.id.totalsVsAveragesSpinner);

        teamVsPlayerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleSpinnerChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        playerNamesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleSpinnerChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        totalsVsAverageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleSpinnerChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    public void handleSpinnerChange(){
        String tvp = teamVsPlayerSpinner.getSelectedItem().toString();
        String tva = totalsVsAverageSpinner.getSelectedItem().toString();
        if(tvp.equals("Player")){
            playerNamesSpinner.setVisibility(View.VISIBLE);
            String playerName = playerNamesSpinner.getSelectedItem().toString();
            playerIndex = playerNames.indexOf(playerName);
            if (!playerName.matches("")){
                if(tva.equals("Totals")){
                    setPlayerTotals(playerIndex);
                }
                if(tva.equals("Averages")){
                    setPlayerAverages(playerIndex);
                }
            }
        }
        if (tvp.equals("Team")){
            playerNamesSpinner.setVisibility(View.INVISIBLE);
            if(tva.equals("Totals")){
                setTeamTotals();
            }
            if(tva.equals("Averages")){
                setTeamAverages();
            }
        }
    }

    public void setPlayerTotals(int i){
        DatabaseReference playerRef = dbRef.child("My Team").child("players").child(String.valueOf(i));
        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.getValue(Player.class);
                minutesDisplay.setText(""+ player.getTotalMinutesPlayed());
                pointsDisplay.setText("" + player.getTotalPoints());
                assistsDisplay.setText("" + player.getTotalAssists());
                reboundsDisplay.setText("" + player.getTotalRebounds());
                stealsDisplay.setText("" + player.getTotalSteals());
                blocksDisplay.setText("" + player.getTotalBlocks());
                turnoversDisplay.setText(""+player.getTotalTurnovers());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setPlayerAverages(int i){
        DatabaseReference playerRef = dbRef.child("My Team").child("players").child(String.valueOf(i));
        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.getValue(Player.class);
                minutesDisplay.setText("" + df.format(player.getAverageMinutesPlayed()));
                pointsDisplay.setText("" + df.format(player.getAveragePoints()));
                assistsDisplay.setText("" + df.format(player.getAverageAssists()));
                reboundsDisplay.setText("" + df.format(player.getAverageRebounds()));
                stealsDisplay.setText("" + df.format(player.getAverageSteals()));
                blocksDisplay.setText("" + df.format(player.getAverageBlocks()));
                turnoversDisplay.setText("" + df.format(player.getAverageTurnovers()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setTeamTotals(){
        DatabaseReference playerRef = dbRef.child("My Team");
        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                minutesDisplay.setText(""+ team.getTotalMinutes());
                pointsDisplay.setText("" + team.getTotalPoints());
                assistsDisplay.setText("" + team.getTotalAssists());
                reboundsDisplay.setText("" + team.getTotalRebounds());
                stealsDisplay.setText("" + team.getTotalSteals());
                blocksDisplay.setText("" + team.getTotalBlocks());
                turnoversDisplay.setText(""+team.getTotalTurnovers());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setTeamAverages(){
        DatabaseReference playerRef = dbRef.child("My Team");
        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                minutesDisplay.setText("" + df.format(team.getAverageMinutes()));
                pointsDisplay.setText("" + df.format(team.getAveragePoints()));
                assistsDisplay.setText("" + df.format(team.getAverageAssists()));
                reboundsDisplay.setText("" + df.format(team.getAverageRebounds()));
                stealsDisplay.setText("" + df.format(team.getAverageSteals()));
                blocksDisplay.setText("" + df.format(team.getAverageBlocks()));
                turnoversDisplay.setText("" + df.format(team.getAverageTurnovers()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void pullDBData(){
        final DatabaseReference teamRef = dbRef.child("My Team");
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                for (int p = 0; p < team.getNumPlayers(); p++){
                    playerNames.add(team.getPlayer(p).getName());
                }
                ArrayAdapter<String> adp = new ArrayAdapter<String> (AnalyticsActivity.this,android.R.layout.simple_spinner_dropdown_item,playerNames);
                playerNamesSpinner.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void initializeTextFields(){
         minutesDisplay = findViewById(R.id.minutesStatView);
         pointsDisplay = findViewById(R.id.pointsStatView);
         assistsDisplay = findViewById(R.id.assistsStatView);
         reboundsDisplay = findViewById(R.id.reboundsStatView);
         stealsDisplay = findViewById(R.id.stealsStatView);
         blocksDisplay = findViewById(R.id.blocksStatView);
         turnoversDisplay = findViewById(R.id.TOsStatView);
    }
}
