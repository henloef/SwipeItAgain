package com.ntnu.swipeitagain.Views;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.game.State;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class GameOver extends WidgetListener implements State {
    private BoardController boardController;

    public GameOver(BoardController boardController){
        this.boardController = boardController;

    }

    private void ActionPerformed(WidgetAction widgetAction){

    }
}
