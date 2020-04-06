package com.example.basketballstatkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class aboutActivity extends AppCompatActivity {

    TextView text;

    String message = "\nTask and Description: \n" +
            "This was an individual project for CSC 490 (Computer Science Senior Capstone Class).  We were tasked with creating an app using any platform of our choosing (in this case the choices were narrowed down to Android Studio, Unity, or a Web Application using JavaScript).  I chose to develop using Android Studio.\n\n" +
            "Description and Use Case: \n" +
            "I chose to create an application that would keep track of basketball stats.  The application currently keeps track of minutes, points, assists, rebounds, steals, blocks, and turnovers.  The user can navigate to a Game Log screen where they can edit all of this information and add new games to each individual player.  Additionally, the user can look at totals and averages for both the team as a whole and player by player using the analytics screen.  The use case I had in mind while designing this would be for a coach of a park district team or a travel team.  The coach (or most likely one of the parents) would be able to keep stats on their phone and then have a place to store that data in the cloud.\n\n"+
            "Contributors and Special Thanks: \n" +
            "\t\tLead Developer: Max McComb \n" +
            "\t\t\t\t maxwellmccomb16@augustana.edu \n" +
            "\t\tDatabase Consultant: Dylan Hart \n" +
            "\t\tTesting Consultant: Kevin Phoenix \n" +
            "\t\tProfessor: Dr. Forrest Stonedahl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        text = findViewById(R.id.aboutScreenText);
        text.setText(message);


    }
}
