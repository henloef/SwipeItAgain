package com.ntnu.swipeitagain.Views;

import android.content.res.Resources;
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

public class GameOver extends State implements WidgetListener {
    private BoardController boardController;
    private TextButton retry, menu;



    public GameOver(BoardController boardController, sheep.game.Game game, Resources resources, int screenWidth, int screenHeight){
        this.boardController = boardController;

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        retry = new TextButton(100, (float)screenHeight*3/7, "Retry", buttonStyle);
        menu = new TextButton(100, (float)screenHeight*5/7, "Main menu", buttonStyle);

        retry.addWidgetListener(this);
        menu.addWidgetListener(this);

        addTouchListener(retry);
        addTouchListener(menu);

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == retry) {
            //TODO What happens when retry is touched
            Log.d(TAG, "actionPerformed: retry");
        } else if (widgetAction.getSource() == menu) {
            //TODO what happens when menu is touched
        }
    }

}
