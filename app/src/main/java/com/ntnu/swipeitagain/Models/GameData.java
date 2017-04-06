package com.ntnu.swipeitagain.Models;

/**
 * Created by hafskolt on 06/04/17.
 */

public class GameData {

    public int gameKey;
    public int games = 0;
    public String beskrivelse = "Wow sykt kult spill";

    public GameData(int key){
        this.gameKey = key;
    }

    public GameData(){
        this.gameKey = 42;
    }


}
