package com.ntnu.swipeitagain.Models;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Lars on 26.03.2017.
 */

public class PlayerModel {
    private int maxTime, currentTime;

    public PlayerModel(){
        maxTime = 100;
        currentTime = 90;
        Log.d(TAG, "Make new player model. Current time: "+currentTime);
    }

    public boolean timeTick() {
        if(currentTime <= 0){
            currentTime = 0;
            return false;
        }
        currentTime--;
        return true;
    }

    public void addTime(int t){
        currentTime += t;
        if(currentTime > maxTime) currentTime = maxTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

}
