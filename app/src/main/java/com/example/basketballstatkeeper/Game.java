package com.example.basketballstatkeeper;

public class Game {

    public int minutesPlayed;
    public int points;
    public int rebounds;
    public int assists;
    public int steals;
    public int blocks;
    public int turnovers;

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getPoints() {
        return points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public int getSteals() {
        return steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }
}
