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

public class SinglePlayerGameView extends GameView {

    public SinglePlayerGameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel){
        super(boardController, screenWidth, screenHeight, gameModel);


    }
}
