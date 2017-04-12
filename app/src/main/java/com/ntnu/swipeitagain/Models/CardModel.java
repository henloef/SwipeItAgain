package com.ntnu.swipeitagain.Models;

import android.util.Log;

import sheep.game.Sprite;
import sheep.graphics.Image;

import static android.content.ContentValues.TAG;

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
        Log.d(TAG, "MAke new card model");
        this.direction = direction;

    }


}

