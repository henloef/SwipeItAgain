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
 * Created by Sigrid on 27.03.2017.
 */

public abstract class AbstractMenuView extends State implements WidgetListener  {

    protected int screenWidth, screenHeight;
    protected BoardController boardController;
    protected Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
    protected Font gameKeyFont = new Font(10, 10, 10, 130, Typeface.SANS_SERIF, Typeface.NORMAL);
    protected Font bigFont = new Font(255, 255, 255, 130, Typeface.SANS_SERIF, Typeface.NORMAL);
    protected Paint[] buttonStyle = {buttonFont, buttonFont};
    protected TextButton mainMenu;

    public AbstractMenuView(BoardController boardController, int screenWidth, int screenHeight) {
        Log.d(TAG, "screenheight: " + screenHeight);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.boardController = boardController;

        mainMenu = new TextButton(100, (float)screenHeight*7/8, "Main menu", buttonStyle);
        mainMenu.addWidgetListener(this);
        addTouchListener(mainMenu);

    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.CYAN);
        mainMenu.draw(canvas);

    }

    public abstract void actionPerformed(WidgetAction widgetAction);

}
