package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.gui.TextButton;
import sheep.gui.WidgetAction;

import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class CreateNewGame extends AbstractMenuView {


    private int gameKey;

    public CreateNewGame(BoardController boardController, int screenWidth, int screenHeight){
        super(boardController, screenWidth,screenHeight);
        this.gameKey = boardController.getGeneratedGameKey();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawText("Game key:", 100, screenHeight*2/7, bigFont);
        canvas.drawText(Integer.toString(gameKey), screenWidth/2, screenHeight*3/7, gameKeyFont);
        canvas.drawText("Waiting for opponent", 100, screenHeight*5/7, bigFont);
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: go back to main menu");
            boardController.goToMainMenu();
        }
    }
}
