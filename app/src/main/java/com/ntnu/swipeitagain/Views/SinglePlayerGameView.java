package com.ntnu.swipeitagain.Views;

import android.graphics.Paint;
import android.graphics.Typeface;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.GameModel;

import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetListener;

/**
 * Created by Henrik on 26.03.2017.
 */

public class SinglePlayerGameView extends GameView implements WidgetListener{

    public SinglePlayerGameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel){
        super(boardController, screenWidth, screenHeight, gameModel);

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        goDirectlyToGameOver = new TextButton(100, (float)screenHeight*6/7, "GoToGameOver", buttonStyle);

        goDirectlyToGameOver.addWidgetListener(this);
        goDirectlyToGameOver.addWidgetListener(this);

        addTouchListener(goDirectlyToGameOver);
    }
}
