package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Created by Lars on 27.03.2017.
 */

public abstract class PlayerGameView extends State implements WidgetListener {

    BoardController boardController;
    protected int screenWidth, screenHeight;
    protected TextButton goDirectlyToGameOver;

    public PlayerGameView(BoardController boardController, int screenWidth, int screenHeight) {
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLUE);
        goDirectlyToGameOver.draw(canvas);
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if(widgetAction.getSource() == goDirectlyToGameOver){
            boardController.pushState(new GameOver(boardController, screenWidth, screenHeight));
        }
    }
}

