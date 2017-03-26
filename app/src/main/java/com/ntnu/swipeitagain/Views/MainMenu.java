package com.ntnu.swipeitagain.Views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;


import sheep.game.*;
import sheep.game.Game;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public final class MainMenu extends State implements WidgetListener {

    private TextButton singleplayer, multiplayer, instructions;
    private Game game;
    private int screenWidth, screenHeight;

    public MainMenu(sheep.game.Game game, Resources resources, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.game = game;

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        singleplayer = new TextButton(100, (float)screenHeight*2/7, "Singleplayer", buttonStyle);
        multiplayer = new TextButton(100, (float)screenHeight*4/7, "Multiplayer", buttonStyle);
        instructions = new TextButton(100, (float)screenHeight*6/7, "Instructions", buttonStyle);

        singleplayer.addWidgetListener(this);
        multiplayer.addWidgetListener(this);
        instructions.addWidgetListener(this);

        addTouchListener(singleplayer);
        addTouchListener(multiplayer);
        addTouchListener(instructions);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        singleplayer.draw(canvas);
        multiplayer.draw(canvas);
        instructions.draw(canvas);

        Font font = new Font(255, 255, 255, 130, Typeface.SANS_SERIF, Typeface.NORMAL);
        canvas.drawText("Main Menu", 100, 230, font);
    }



    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == singleplayer){
            //TODO What happens when singleplayer is touched
            Log.d(TAG, "actionPerformed: singleplayer");
        }
        else if (widgetAction.getSource() == multiplayer){
            //TODO what happens when multiplayer is touched
            Log.d(TAG, "actionPerformed: multiplayer");
        }
        else if (widgetAction.getSource() == instructions){
            //Todo what happens when instructions is pushed
            Log.d(TAG, "actionPerformed: instructions");
        }
    }
}
