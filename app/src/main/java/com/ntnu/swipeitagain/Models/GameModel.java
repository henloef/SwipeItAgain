package com.ntnu.swipeitagain.Models;

import android.util.Log;

import com.ntnu.swipeitagain.R;

import java.util.ArrayList;
import java.util.Random;

import sheep.graphics.Image;

import static android.content.ContentValues.TAG;

/**
 * Created by Lars on 26.03.2017.
 */

public class GameModel {

    private ArrayList<CardModel> cards;
    private CardModel currentCard;
    private PlayerModel player;
    private Image leftArrowImage;
    private Image rightArrowImage;
    private Image upArrowImage;
    private Image downArrowImage;

    //Constructor
    public GameModel(){
        Log.d(TAG, "MAke new game model");
        createCards();
        player = new PlayerModel();
    }

    public PlayerModel getPlayer(){
        return player;
    }

    //gets true if still time left
    public boolean timeLeft(){
        return player.timeLeft();
    }


    //Makes card for each direction
    private void createCards(){
        cards = new ArrayList<CardModel>();
        leftArrowImage = new Image(R.drawable.transp_arrow_left);
        rightArrowImage = new Image(R.drawable.transp_arrow_right);
        upArrowImage = new Image(R.drawable.transp_arrow_up);
        downArrowImage = new Image(R.drawable.transp_arrow_down);

        for(Direction dir : Direction.values()){
            switch (dir){
                case UP: cards.add(new CardModel(upArrowImage,dir));
                case DOWN: cards.add(new CardModel(downArrowImage,dir));
                case LEFT: cards.add(new CardModel(leftArrowImage,dir));
                case RIGHT: cards.add(new CardModel(rightArrowImage,dir));
            }
             //TODO create arrowImage
        }
        Log.d(TAG, "cards stack length now: "+ cards.size());
    }

    //compares direction from user with card-direction. if same, return true and go to next card
    public boolean checkDirection(Direction playerDirection){
        Direction cardDirection = currentCard.getDirection();
        if(cardDirection == playerDirection){
            nextCard();
            return true;
        }
        else return false;
    }

    //sets current card to a new, random card
    public void nextCard(){
        Random r = new Random();
        currentCard = cards.get(r.nextInt(Direction.values().length));
    }

    public CardModel getCurrentCard(){
        return cards.get(0);
        //return currentCard;
    }

    public void timeTick(){
        player.timeTick();
    }

    public ArrayList<CardModel> getCards(){
        return cards;
    }

    public int getCurrentTime(){
        return getPlayer().getCurrentTime();
    }
}
