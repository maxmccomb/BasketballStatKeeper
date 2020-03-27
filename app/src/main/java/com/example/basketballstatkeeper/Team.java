package com.example.basketballstatkeeper;

import java.util.ArrayList;

public class Team {
    ArrayList <Player> players = new ArrayList<>();

    //empty constructor for Firebase
    public Team (){
    }

    public Team(ArrayList<Player> players) {
        this.players = players;
    }

    //returns the list of players
    public ArrayList<Player> getPlayers() {
        return players;
    }

    //returns a player object given a a name of the player and null if the player is not in the list
    public Player getPlayer(String name){
        for (int i = 0; i < players.size(); i++){
            if(players.get(i).getName().equals(name)){
                return players.get(i);
            }
        }
        return null;
    }

    //returns a player given an index
    public Player getPlayer(int index){
        return players.get(index);
    }

    //returns the index of the player given a name, returns -1 if that name is not in the list
    public int getPlayerIndex(String name){
        for (int i = 0; i < players.size(); i++){
            if(players.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public int getNumPlayers(){
        return players.size();
    }


    //below are getter methods for average and totals of all stat categories
    public int getTotalMinutes(){
        int sum = 0;
        for (int i = 0; i < players.size(); i++){
            sum += players.get(i).getTotalMinutesPlayed();
        }
        return sum;
    }

    public double getAverageMinutes(){
        double sum = 0;
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).getNumGames(); j++){
                sum += players.get(i).getGame(j).getMinutesPlayed();
            }
        }
        return sum/((double)players.get(0).getNumGames());
    }


    public int getTotalPoints(){
        int sum = 0;
        for (int i = 0; i < players.size(); i++){
            sum += players.get(i).getTotalPoints();
        }
        return sum;
    }

    public double getAveragePoints(){
        double sum = 0;
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).getNumGames(); j++){
                sum += players.get(i).getGame(j).getPoints();
            }
        }
        return sum/((double)players.get(0).getNumGames());
    }

    public int getTotalAssists(){
        int sum = 0;
        for (int i = 0; i < players.size(); i++){
            sum += players.get(i).getTotalAssists();
        }
        return sum;
    }

    public double getAverageAssists(){
        double sum = 0;
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).getNumGames(); j++){
                sum += players.get(i).getGame(j).getAssists();
            }
        }
        return sum/((double)players.get(0).getNumGames());
    }

    public int getTotalRebounds(){
        int sum = 0;
        for (int i = 0; i < players.size(); i++){
            sum += players.get(i).getTotalRebounds();
        }
        return sum;
    }

    public double getAverageRebounds(){
        double sum = 0;
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).getNumGames(); j++){
                sum += players.get(i).getGame(j).getAssists();
            }
        }
        return sum/((double)players.get(0).getNumGames());
    }

    public int getTotalSteals(){
        int sum = 0;
        for (int i = 0; i < players.size(); i++){
            sum += players.get(i).getTotalSteals();
        }
        return sum;
    }

    public double getAverageSteals(){
        double sum = 0;
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).getNumGames(); j++){
                sum += players.get(i).getGame(j).getSteals();
            }
        }
        return sum/((double)players.get(0).getNumGames());
    }

    public int getTotalBlocks(){
        int sum = 0;
        for (int i = 0; i < players.size(); i++){
            sum += players.get(i).getTotalBlocks();
        }
        return sum;
    }

    public double getAverageBlocks(){
        double sum = 0;
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).getNumGames(); j++){
                sum += players.get(i).getGame(j).getBlocks();
            }
        }
        return sum/((double)players.get(0).getNumGames());
    }

    public int getTotalTurnovers(){
        int sum = 0;
        for (int i = 0; i < players.size(); i++){
            sum += players.get(i).getTotalTurnovers();
        }
        return sum;
    }

    public double getAverageTurnovers(){
        double sum = 0;
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < players.get(i).getNumGames(); j++){
                sum += players.get(i).getGame(j).getTurnovers();
            }
        }
        return sum/((double)players.get(0).getNumGames());
    }


}
