package com.ntnu.swipeitagain.Views;

import android.content.res.Resources;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.game.State;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class GameOver extends State implements WidgetListener {
    private BoardController boardController;

    public GameOver(BoardController boardController, sheep.game.Game game, Resources resources, int screenWidth, int screenHeight){
        this.boardController = boardController;

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {

    }
}
