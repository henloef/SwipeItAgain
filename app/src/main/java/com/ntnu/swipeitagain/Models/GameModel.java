package com.ntnu.swipeitagain.Models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lars on 26.03.2017.
 */

public class GameModel {

    private ArrayList<CardModel> cards;
    private CardModel currentCard;
    private PlayerModel player;

    //Constructor
    public GameModel(){
        createCards();
        player = new PlayerModel();
    }

    //gets true if still time left
    public boolean timeLeft(){
        return player.timeLeft();
    }


    //Makes card for each direction
    private void createCards(){
        cards = new ArrayList<CardModel>();
        for(Direction dir : Direction.values()){
            cards.add(new CardModel(dir));
        }
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
        return currentCard;
    }

    public ArrayList<CardModel> getCards(){
        return cards;
    }
}
