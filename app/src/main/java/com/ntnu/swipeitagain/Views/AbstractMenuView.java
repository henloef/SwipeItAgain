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
 * Created by Group 22 on 27.03.2017.
 */

public abstract class AbstractMenuView extends State implements WidgetListener  {

    protected int screenWidth, screenHeight;
    protected BoardController boardController;
    protected Font buttonFont, gameKeyFont, bigFont;
    protected Paint[] buttonStyle = new Font[2];
    protected TextButton mainMenu;

    public AbstractMenuView(BoardController boardController, int screenWidth, int screenHeight) {
        Log.d(TAG, "screenheight: " + screenHeight);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.boardController = boardController;

        buttonFont = new Font(255, 255, 255, screenHeight/19, Typeface.SANS_SERIF, Typeface.NORMAL);
        bigFont = new Font(255, 255, 255, screenHeight/15, Typeface.SANS_SERIF, Typeface.NORMAL);
        gameKeyFont = new Font(10, 10, 10, screenHeight/12, Typeface.SANS_SERIF, Typeface.NORMAL);
        buttonStyle[0] = buttonFont;
        buttonStyle[1] = buttonFont;

        mainMenu = new TextButton(screenWidth/12, (float)screenHeight*7/8, "Back to main menu", buttonStyle);
        mainMenu.addWidgetListener(this);
        addTouchListener(mainMenu);

    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.CYAN);
        mainMenu.draw(canvas);
    }

    public abstract void actionPerformed(WidgetAction widgetAction);

}
