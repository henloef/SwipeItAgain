package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.game.State;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Created by Henrik on 26.03.2017.
 */

public class SinglePlayerGameView extends State implements WidgetListener{

    BoardController boardController;

    public void GameView(BoardController boardController, int screenWidth, int screenHeight){
        this.boardController = boardController;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {

    }
}
