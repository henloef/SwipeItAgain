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
 * Created by Sigrid on 26.03.2017.
 */

public class MultiPlayerGameView extends State implements WidgetListener {

    BoardController boardController;
    private int screenWidth, screenHeigth;
    private TextButton goDirectlyToGameOver;

    public MultiPlayerGameView(BoardController boardController, int screenWidth, int screenHeight){
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeight;

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        goDirectlyToGameOver = new TextButton(100, (float)screenHeight*3/7, "GoToGameOver", buttonStyle);

        goDirectlyToGameOver.addWidgetListener(this);
        goDirectlyToGameOver.addWidgetListener(this);

        addTouchListener(goDirectlyToGameOver);

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
            boardController.pushState(new GameOver(boardController, screenWidth, screenHeigth));
        }
    }
}
