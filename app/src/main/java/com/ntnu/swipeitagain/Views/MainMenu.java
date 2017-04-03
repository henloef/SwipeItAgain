package com.ntnu.swipeitagain.Views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;


import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.gui.TextButton;
import sheep.gui.WidgetAction;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public final class MainMenu extends AbstractMenuView {

    private Resources resources;

    private TextButton singleplayer, multiplayer, instructions;

    public MainMenu(BoardController boardController, sheep.game.Game game, Resources resources, int screenWidth, int screenHeight) {
        super(boardController, screenWidth,screenHeight);

        this.resources = resources;

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

    //draws main menu. Overrides because it is the only one without main menu-button
    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        singleplayer.draw(canvas);
        multiplayer.draw(canvas);
        instructions.draw(canvas);
        canvas.drawText("Main menu", 100, 230, bigFont);
    }


    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == singleplayer){
            Log.d(TAG, "actionPerformed: singleplayer");
            boardController.createGameState(false, false);
        }
        else if (widgetAction.getSource() == multiplayer){
            Log.d(TAG, "actionPerformed: multiplayer");
            boardController.pushState(new CreateMultiPlayer(boardController, screenWidth, screenHeight));
        }
        else if (widgetAction.getSource() == instructions){
            Log.d(TAG, "actionPerformed: instructions");
            boardController.pushState(new InstructionsView(boardController, screenWidth, screenHeight, resources));
        }
    }
}
