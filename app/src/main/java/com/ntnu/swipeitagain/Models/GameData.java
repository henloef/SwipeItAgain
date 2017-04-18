package com.ntnu.swipeitagain.Models;

/**
 * Created by hafskolt on 06/04/17.
 */

public class GameData {

    public int gameKey;
    public boolean waitingForOponent = true;
    public PlayerModel player;
    public PlayerModel opponent;

//    public int numberOfGames= 0;

    public GameData(int numberOfGames, PlayerModel player){

        this.gameKey = numberOfGames;
        this.player = player;
    }

    //public GameData(){
     //   this.gameKey = 42;
    //}

    public boolean hasOpponent(){
        if(opponent != null){
            return true;
        }
        return false;
    }
    public void setOpponent(PlayerModel opponent){
        this.opponent = opponent;
    }

    public String toString(){
        return "Game key to gameData:" + gameKey;//+ " games: " +numberOfGames;
    }


}
