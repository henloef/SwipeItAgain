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

public class CreateMultiPlayer extends State implements WidgetListener {

    BoardController boardController;
    private int screenWidth, screenHeigth;
    private TextButton createGame, joinGame, mainMenu;

    public CreateMultiPlayer(BoardController boardController, int screenWidth, int screenHeight){
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeight;

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        createGame = new TextButton(100, (float)screenHeight*2/7, "Create new game", buttonStyle);
        joinGame = new TextButton(100, (float)screenHeight*4/7, "Join game", buttonStyle);
        mainMenu = new TextButton(100, (float)screenHeight*6/7, "Main menu", buttonStyle);

        createGame.addWidgetListener(this);
        joinGame.addWidgetListener(this);
        mainMenu.addWidgetListener(this);

        addTouchListener(createGame);
        addTouchListener(joinGame);
        addTouchListener(mainMenu);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        createGame.draw(canvas);
        joinGame.draw(canvas);
        mainMenu.draw(canvas);
    }

    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: go back to main menu");
            boardController.goToMainMenu(1);
        }else if (widgetAction.getSource() == createGame) {
            //TODO What happens when retry is touched
            Log.d(TAG, "actionPerformed: createGame");
            boardController.createGameState(true, true);
            boardController.pushState(new CreateNewGame(boardController, screenWidth, screenHeigth));
        } else if (widgetAction.getSource() == joinGame) {
            Log.d(TAG, "actionPerformed: joinGame");
            boardController.createGameState(true, false);
            boardController.pushState(new JoinGame(boardController, screenWidth, screenHeigth));
            //TODO what happens when menu is touched
        }

    }
}
