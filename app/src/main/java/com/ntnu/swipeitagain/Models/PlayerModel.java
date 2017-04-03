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

    public void addTime(){
        addTime(5);
    }

    public void addTime(int t){
        currentTime += t;
        if(currentTime > maxTime) currentTime = maxTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        if (currentTime > maxTime){
            this.currentTime = maxTime;
        } else if (currentTime < 0){
            this.currentTime = 0;
        } else{
            this.currentTime = currentTime;
        }
    }

    public boolean timeLeft(){
        return currentTime > 0;
    }
}
