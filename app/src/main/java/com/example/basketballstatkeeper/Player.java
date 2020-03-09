package com.example.basketballstatkeeper;

import java.io.Serializable;
import java.util.ArrayList;

public class Player {

    private ArrayList<Game> games;


    public Player(Game game) {
        games = new ArrayList<>();
        games.add(game);
    }

    public Game getGame(int index){
        return games.get(index);
    }

    public int getNumGames(){
        return games.size();
    }

    public void addGame(Game game){
        games.add(game);
    }




    //below are getter methods for average and totals of all stat categories
    public double getAveragePoints(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getPoints();
        }
        return sum/ games.size();
    }

    public int getTotalPoints(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getPoints();
        }
        return sum;
    }

    public double getAverageRebounds(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getRebounds();
        }
        return sum/ games.size();
    }

    public int getTotalRebounds(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getRebounds();
        }
        return sum;
    }

    public double getAverageMinutesPlayed(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getMinutesPlayed();
        }
        return sum/ games.size();
    }

    public int getTotalMinutesPlayed(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getMinutesPlayed();
        }
        return sum;
    }

    public double getAverageAssists(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getAssists();
        }
        return sum/ games.size();
    }

    public int getTotalAssists(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getAssists();
        }
        return sum;
    }

    public double getAverageSteals(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getSteals();
        }
        return sum/ games.size();
    }

    public int getTotalSteals(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getSteals();
        }
        return sum;
    }

    public double getAverageBlocks(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getBlocks();
        }
        return sum/ games.size();
    }

    public int getTotalBlocks(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getBlocks();
        }
        return sum;
    }

    public double getAverageTurnovers(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getTurnovers();
        }
        return sum/ games.size();
    }

    public int getTotalTurnovers(){
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getTurnovers();
        }
        return sum;
    }

}
