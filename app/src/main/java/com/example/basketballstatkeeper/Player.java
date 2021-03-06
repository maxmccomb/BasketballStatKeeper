package com.example.basketballstatkeeper;

import java.io.Serializable;
import java.util.ArrayList;

public class Player {

    private ArrayList<Game> games; //a player has a list of games played
    private String name; //name of the player

    //empty constructor for Firebase
    public Player(){
    }


    //constructor
    public Player(ArrayList<Game> g, String n) {
        games = g;
        name = n;
    }

    //returns a list of game objects
    public ArrayList<Game> getGames(){
        return games;
    }

    //returns a game at a specified index
    public Game getGame(int index){
        return games.get(index);
    }

    //returns the number of Game objects in the games ArrayList
    public int getNumGames(){
        return games.size();
    }

    //adds a game to the ArrayList
    public void addGame(Game game){
        games.add(game);
    }

    //returns the name of the player
    public String getName() {
        return name;
    }

    //sets the name of the player
    public void setName(String name) {
        this.name = name;
    }

    //below are getter methods for average and totals of all stat categories
    public double getAveragePoints(){
        System.out.println("GAMES SIZE: " + games.size());
         double sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getPoints();
        }
        return sum/games.size();
    }

    public int getTotalPoints(){
        System.out.println("GAMES SIZE: " + games.size());
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getPoints();
        }
        return sum;
    }

    public double getAverageRebounds(){
        System.out.println("GAMES SIZE: " + games.size());
        double sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getRebounds();
        }
        return sum/games.size();
    }

    public int getTotalRebounds(){
        System.out.println("GAMES SIZE: " + games.size());
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getRebounds();
        }
        return sum;
    }

    public double getAverageMinutesPlayed(){
        System.out.println("GAMES SIZE: " + games.size());
        double sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getMinutesPlayed();
        }
        return sum/games.size();
    }

    public int getTotalMinutesPlayed(){
        System.out.println("GAMES SIZE: " + games.size());
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getMinutesPlayed();
        }
        return sum;
    }

    public double getAverageAssists(){
        System.out.println("GAMES SIZE: " + games.size());
        double sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getAssists();
        }
        return sum/games.size();
    }

    public int getTotalAssists(){
        System.out.println("GAMES SIZE: " + games.size());
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getAssists();
        }
        return sum;
    }

    public double getAverageSteals(){
        System.out.println("GAMES SIZE: " + games.size());
        double sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getSteals();
        }
        return sum/games.size();
    }

    public int getTotalSteals(){
        System.out.println("GAMES SIZE: " + games.size());
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum +=games.get(i).getSteals();
        }
        return sum;
    }

    public double getAverageBlocks(){
        System.out.println("GAMES SIZE: " + games.size());
        double sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getBlocks();
        }
        return sum/games.size();
    }

    public int getTotalBlocks(){
        System.out.println("GAMES SIZE: " + games.size());
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getBlocks();
        }
        return sum;
    }

    public double getAverageTurnovers(){
        System.out.println("GAMES SIZE: " + games.size());
        double sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getTurnovers();
        }
        return sum/games.size();
    }

    public int getTotalTurnovers(){
        System.out.println("GAMES SIZE: " + games.size());
        int sum = 0;
        for (int i = 0; i < games.size(); i++){
            sum += games.get(i).getTurnovers();
        }
        return sum;
    }

}
