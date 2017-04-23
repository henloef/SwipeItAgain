package com.ntnu.swipeitagain.Models;

/**
 * Created by Group 22 on 06/04/17.
 */

public class GameData {

    public int gameKey;
    public boolean waitingForOponent = true;
    public PlayerModel player;
    public PlayerModel opponent;

//    public int numberOfGames= 0;

    public GameData(int numberOfGames){
        this.gameKey = numberOfGames;
    }

    public GameData(){
        this.gameKey = 42;
    }

    public boolean hasOpponent(){
        if(opponent != null){
            return true;
        }
        return false;
    }

    public String toString(){
        return "Game key to gameData:" + gameKey;//+ " games: " +numberOfGames;
    }


}
