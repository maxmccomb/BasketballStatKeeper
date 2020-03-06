package com.example.basketballstatkeeper;

import java.util.ArrayList;

public class Player {

    private Game game;

    public Player(Game game) {
        this.game = game;
    }

    public Game getGame(){
        return game;
    }



    /*public double getAveragePoints(){
        int sum = 0;
        for (int i = 0; i < game.size(); i++){
            sum += game.get(i).getPoints();
        }
        return sum/ game.size();
    }

    public double getAverageRebounds(){
        int sum = 0;
        for (int i = 0; i < game.size(); i++){
            sum += game.get(i).getRebounds();
        }
        return sum/ game.size();
    }

    public double getAverageMinutesPlayed(){
        int sum = 0;
        for (int i = 0; i < game.size(); i++){
            sum += game.get(i).getMinutesPlayed();
        }
        return sum/ game.size();
    }

    public double getAverageAssists(){
        int sum = 0;
        for (int i = 0; i < game.size(); i++){
            sum += game.get(i).getAssists();
        }
        return sum/ game.size();
    }

    public double getAverageSteals(){
        int sum = 0;
        for (int i = 0; i < game.size(); i++){
            sum += game.get(i).getSteals();
        }
        return sum/ game.size();
    }

    public double getAverageBlocks(){
        int sum = 0;
        for (int i = 0; i < game.size(); i++){
            sum += game.get(i).getBlocks();
        }
        return sum/ game.size();
    }

    public double getAverageTurnovers(){
        int sum = 0;
        for (int i = 0; i < game.size(); i++){
            sum += game.get(i).getTurnovers();
        }
        return sum/ game.size();
    }
     */
}
