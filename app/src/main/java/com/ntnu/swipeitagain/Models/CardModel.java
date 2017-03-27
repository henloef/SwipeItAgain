package com.ntnu.swipeitagain.Models;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Lars on 26.03.2017.
 */

public class CardModel extends Sprite{
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public CardModel(Image image, Direction direction){
        super(image);

        this.direction = direction;
        scaleDirection(direction);

    }

    // Scales the texture according to the direction


    private void scaleDirection(Direction direction){
        if(direction == Direction.LEFT){
            this.setScale(-1, 1);
        }else if (direction == Direction.DOWN){
            this.rotate(90);
        }else if (direction == Direction.UP){
            this.rotate(270);
        }

    }




    //

    /*
    private int value;
    private String suit;
    */
}

