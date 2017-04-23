package com.ntnu.swipeitagain.Models;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Group 22 on 26.03.2017.
 */

public class PlayerModel {
    private int maxTime, currentTime, score;

    public PlayerModel(){
        maxTime = 100;
        currentTime = 90;
        this.score = 0;
        Log.d(TAG, "Make new player model. Current time: "+currentTime);
    }

    public void timeTick() {
        if (currentTime <= 0) {
            currentTime = 0;
        } else {
            currentTime--;
        }
    }

    public void addTime(){
        addTime(5);
    }

    public void addTime(int t){
        currentTime += t;
        if(currentTime > maxTime) currentTime = maxTime;
    }


    public int getCurrentTime() {
        return currentTime;
    }


    public void newPoint(){
        this.score ++;
    }

    public int getScore(){
        return this.score;
    }

    public boolean timeLeft(){
        return currentTime > 0;
    }
}
