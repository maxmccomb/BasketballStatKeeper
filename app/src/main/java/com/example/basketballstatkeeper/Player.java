package com.example.basketballstatkeeper;

import java.util.ArrayList;

public class Player {

    ArrayList<Game> games;

    public Player() {
        games = new ArrayList();
    }

    public double getAveragePoints(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getPoints();
        }
        return sum/games.size();
    }

    public double getAverageRebounds(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getRebounds();
        }
        return sum/games.size();
    }

    public double getAverageMinutesPlayed(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getMinutesPlayed();
        }
        return sum/games.size();
    }

    public double getAverageAssists(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getAssists();
        }
        return sum/games.size();
    }

    public double getAverageSteals(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getSteals();
        }
        return sum/games.size();
    }

    public double getAverageBlocks(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getBlocks();
        }
        return sum/games.size();
    }

    public double getAverageTurnovers(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getTurnovers();
        }
        return sum/games.size();
    }
}
