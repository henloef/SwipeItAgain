package com.ntnu.swipeitagain.Views;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.gui.TextButton;
import sheep.gui.WidgetAction;

import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 27.03.2017.
 */

public class InstructionsView extends AbstractMenuView {

    private Resources resources;
    public InstructionsView(BoardController boardController, int screenWidth, int screenHeight, Resources resources){
        super(boardController, screenWidth, screenHeight);
        this.resources = resources;

        mainMenu =  new TextButton(100, (float)screenHeight*2/10, "Main menu", buttonStyle);
        mainMenu.addWidgetListener(this);
        addTouchListener(mainMenu);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: go back to main menu");
            boardController.goToMainMenu();
        }
    }
}
