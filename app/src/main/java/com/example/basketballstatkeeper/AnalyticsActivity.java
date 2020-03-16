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

    DecimalFormat df = new DecimalFormat("###.00");


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    // beginning the DB with "Teams" for future expanding
    DatabaseReference dbRef = db.getReference("Teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        initializeTextFields();


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
        DatabaseReference playerRef = dbRef.child("Augustana Vikings").child("Players").child("Max");
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

    public void setAverages(){
        DatabaseReference playerRef = dbRef.child("Augustana Vikings").child("Players").child("Max");
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
