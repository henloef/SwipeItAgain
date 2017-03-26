package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class CreateNewGame extends State implements WidgetListener {

    BoardController boardController;
    private int screenWidth, screenHeigth;
    private int gameKey;
    private TextButton mainMenu;

    public CreateNewGame(BoardController boardController, int screenWidth, int screenHeight){
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeight;
        this.gameKey = boardController.getGeneratedGameKey();


        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        mainMenu = new TextButton(100, (float)screenHeight*6/7, "Main menu", buttonStyle);
        mainMenu.addWidgetListener(this);
        addTouchListener(mainMenu);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.CYAN);

        Font font = new Font(255, 255, 255, 130, Typeface.SANS_SERIF, Typeface.NORMAL);
        canvas.drawText("Game key:", 100, screenHeigth*2/7, font);

        Font gameKeyFont = new Font(10, 10, 10, 130, Typeface.SANS_SERIF, Typeface.NORMAL);
        canvas.drawText(Integer.toString(gameKey), screenWidth/2, screenHeigth*3/7, gameKeyFont);
        canvas.drawText("Waiting for opponent", 100, screenHeigth*5/7, font);

        mainMenu.draw(canvas);
    }

    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: go back to main menu");
            boardController.goToMainMenu(2);
        }
    }
}
