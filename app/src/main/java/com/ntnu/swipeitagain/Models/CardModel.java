package com.ntnu.swipeitagain.Models;

import sheep.game.Sprite;

/**
 * Created by Lars on 26.03.2017.
 */

public class CardModel extends Sprite{
    private Direction direction;
//    private Texture arrowTexture;

//    public Texture getTexture() {
//        return arrowTexture;
//    }

    public Direction getDirection() {
        return direction;
    }

    public CardModel(Direction direction){

        //Try-catch to be able to test without instanitating game
        try {
//            this.arrowTexture = new Texture("pil.gif");
//          setTexture(arrowTexture);
        }catch(NullPointerException e){
            System.out.println("Error creating texture");
        }
        this.direction = direction;
//        scaleDirection(direction);

    }

    // Scales the texture according to the direction

 /*
    private void scaleDirection(Direction direction){
        if(direction == Direction.LEFT){
            this.setScale(-1, 1);
        }else if (direction == Direction.DOWN){
            this.rotate90(true);
        }else if (direction == Direction.UP){
            this.rotate90(false);
        }

    }*/
    private void setTexture(){

    }



    //

    /*
    private int value;
    private String suit;
    */
}

