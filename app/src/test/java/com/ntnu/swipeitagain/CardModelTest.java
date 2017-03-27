package com.ntnu.swipeitagain;


import com.ntnu.swipeitagain.Models.CardModel;
import com.ntnu.swipeitagain.Models.Direction;

import org.junit.Assert;
import org.junit.Test;

import sheep.graphics.Image;

/**
 * Created by Henrik on 27.03.2017.
 */

public class CardModelTest {

    public Image image = new Image(R.drawable.right_arrow);

    CardModel cardModelUp = new CardModel(image, Direction.UP);
/*    CardModel cardModelDown = new CardModel(Direction.DOWN);
    CardModel cardModelLeft = new CardModel(Direction.LEFT);
    CardModel cardModelRight = new CardModel(Direction.RIGHT);*/


    @Test
    public void cardDirectionValidator_CorrectDirection(){
        Assert.assertEquals(cardModelUp.getDirection(), Direction.UP);
/*        Assert.assertEquals(cardModelDown.getDirection(), Direction.DOWN);
        Assert.assertEquals(cardModelLeft.getDirection(), Direction.LEFT);
        Assert.assertEquals(cardModelRight.getDirection(), Direction.RIGHT);*/
    }

}
