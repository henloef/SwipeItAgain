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
 * Created by Henrik on 26.03.2017.
 */

public class SinglePlayerGameView extends PlayerGameView implements WidgetListener{

    public SinglePlayerGameView(BoardController boardController, int screenWidth, int screenHeight){
        super(boardController, screenWidth, screenHeight);

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        goDirectlyToGameOver = new TextButton(100, (float)screenHeight*3/7, "GoToGameOver", buttonStyle);

        goDirectlyToGameOver.addWidgetListener(this);
        goDirectlyToGameOver.addWidgetListener(this);

        addTouchListener(goDirectlyToGameOver);
    }
}
