package com.ntnu.swipeitagain.Models;



/**
 * Created by hafskolt on 06/04/17.
 */

public class GameData {

    public int gameKey;
    public boolean waitingForOponent = true;
    public PlayerModel player;
    public PlayerModel opponent;
    public boolean start = false;
    public long childrenCount;

    // @JsonIgnore
    private String key;

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

    public void setPlayer(PlayerModel player){
          this.player = player;
    }

    public int getGameKey(){
        return this.gameKey;
    }
    public void setGameKey(int gameKey){
        this.gameKey = gameKey;
    }

    public String toString(){
        return "Game key to gameData:" + gameKey;//+ " games: " +numberOfGames;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getKey(){
        return this.key;
    }

    public void Start(boolean start){
        this.start = start;
    }

    public boolean getStart(){
        return this.start;
    }
    public void setChildrenCount(long count){
        this.childrenCount = count;
    }

    public long getChildrenCount(){
        return this.childrenCount;
    }


}
