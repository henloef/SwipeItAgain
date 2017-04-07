package com.ntnu.swipeitagain.Views;

import android.graphics.Paint;
import android.graphics.Typeface;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.GameModel;

import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetListener;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class MultiPlayerGameView extends GameView {

    public MultiPlayerGameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel){
        super(boardController, screenWidth, screenHeight, gameModel);

    }

    public void update(float dt){
        super.update(dt);
        //boardController.getOpponentScore();
    }
}