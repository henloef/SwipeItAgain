package com.ntnu.swipeitagain.Models;

/**
 * Created by Lars on 26.03.2017.
 */

public class PlayerModel {
    private int maxTime, currentTime;
    public PlayerModel(){
        maxTime = 100;
        currentTime = 95;
    }

    public void timeTick() {
        currentTime--;

    }

    private void addTime(){
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
        if (currentTime > 100){
            this.currentTime = 100;
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
