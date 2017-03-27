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

public class GameOver extends AbstractMenuView {

    private TextButton retry;

    public GameOver(BoardController boardController, int screenWidth, int screenHeight){
        super(boardController, screenWidth, screenHeight);
        this.boardController = boardController;

        retry = new TextButton(100, (float)screenHeight*3/7, "Retry", buttonStyle);

        retry.addWidgetListener(this);

        addTouchListener(retry);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        retry.draw(canvas);
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == retry) {
            //TODO What happens when retry is touched
            Log.d(TAG, "actionPerformed: retry");
            boardController.retry();
        } else if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: main menu");
            boardController.goToMainMenu();
        }
    }

}
