package com.example.basketballstatkeeper;

import java.util.ArrayList;

public class Team {
    ArrayList <Player> players = new ArrayList<>();

    public Team (){

    }

    public Team(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String name){
        for (int i = 0; i < players.size(); i++){
            if(players.get(i).getName().equals(name)){
                return players.get(i);
            }
        }
        return null;
    }

    public Player getPlayer(int index){
        return players.get(index);
    }

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


}
