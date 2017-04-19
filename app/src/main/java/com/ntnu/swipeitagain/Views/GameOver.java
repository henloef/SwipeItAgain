package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.util.Log;
import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.GameModel;
import com.ntnu.swipeitagain.States.MultiPlayerState;

import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class GameOver extends AbstractMenuView {

    private TextButton retry;
    private GameModel gameModel;

    public GameOver(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel){
        super(boardController, screenWidth, screenHeight);
        this.boardController = boardController;

        retry = new TextButton(100, (float)screenHeight*5/7, "Retry", buttonStyle);

        retry.addWidgetListener(this);

        addTouchListener(retry);
        this.gameModel = gameModel;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        retry.draw(canvas);
        canvas.drawText("Score: "+ gameModel.getPlayer().getScore(), 100, 2*screenHeight/7, bigFont);
        if(boardController.getGameState() instanceof MultiPlayerState){
            canvas.drawText("Opponent: " + gameModel.getOpponent().getScore(), 100, 3*screenHeight/7, bigFont);
            if(gameModel.getPlayer().getScore() > gameModel.getOpponent().getScore()){
                canvas.drawText("You won!", 100, screenHeight/7, bigFont);
            }else if(gameModel.getPlayer().getScore() < gameModel.getOpponent().getScore()){
                canvas.drawText("You lost!", 100, screenHeight/7, bigFont);
            }else{
                canvas.drawText("It's a tie!", 100, screenHeight/7, bigFont);
            }
        }
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == retry) {
            Log.d(TAG, "actionPerformed: retry");
            boardController.retry();
        } else if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: main menu");
            boardController.goToMainMenu();
        }
    }

}
