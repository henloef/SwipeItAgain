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

public class CreateMultiPlayer extends AbstractMenuView{

    private TextButton createGame, joinGame;

    public CreateMultiPlayer(BoardController boardController, int screenWidth, int screenHeight){
        super(boardController, screenWidth, screenHeight);

        createGame = new TextButton(100, (float)screenHeight*2/7, "Create new game", buttonStyle);
        joinGame = new TextButton(100, (float)screenHeight*4/7, "Join game", buttonStyle);

        createGame.addWidgetListener(this);
        joinGame.addWidgetListener(this);

        addTouchListener(createGame);
        addTouchListener(joinGame);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        createGame.draw(canvas);
        joinGame.draw(canvas);
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: go back to main menu");
            boardController.goToMainMenu();
        }else if (widgetAction.getSource() == createGame) {
            Log.d(TAG, "actionPerformed: createGame");
            boardController.createGameState(true, true);
            boardController.pushState(new CreateNewGame(boardController, screenWidth, screenHeight));
        } else if (widgetAction.getSource() == joinGame) {
            Log.d(TAG, "actionPerformed: joinGame");
            boardController.createGameState(true, false);
            boardController.pushState(new JoinGame(boardController, screenWidth, screenHeight));
        }

    }
}
