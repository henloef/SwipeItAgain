package com.ntnu.swipeitagain.Models;

/**
 * Created by hafskolt on 06/04/17.
 */

public class GameData {

    public int gameKey;
    public int numberOfGames= 0;
    public String beskrivelse = "Wow sykt kult spill";

    public GameData(int numberOfGames){
        this.gameKey = numberOfGames;
    }

    public GameData(){
        this.gameKey = 42;
    }

    public String toString(){
        return "Game key:" + gameKey+ " games: " +numberOfGames + " beskrivelse: " + beskrivelse;
    }


}
