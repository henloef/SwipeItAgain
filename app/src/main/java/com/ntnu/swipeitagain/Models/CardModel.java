package com.ntnu.swipeitagain.Models;

import android.util.Log;

import sheep.game.Sprite;
import sheep.graphics.Image;

import static android.content.ContentValues.TAG;

public class CardModel extends Sprite{
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public CardModel(Image image, Direction direction){
        super(image);
        Log.d(TAG, "Make new card model");
        this.direction = direction;
    }
}

