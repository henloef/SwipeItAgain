package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.game.State;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Created by Henrik on 26.03.2017.
 */

public class SinglePlayerGameView extends State implements WidgetListener{

    BoardController boardController;
    private int screenWidth, screenHeigth;

    public SinglePlayerGameView(BoardController boardController, int screenWidth, int screenHeight){
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeight;

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLUE);

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {

    }
}
