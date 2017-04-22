package com.ntnu.swipeitagain.Views;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.Difficulty;

import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;

import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 27.03.2017.
 */

public class InstructionsView extends AbstractMenuView {

    private TextButton easy, medium, hard;
    private Font instructionsFont;

    public InstructionsView(BoardController boardController, int screenWidth, int screenHeight, Resources resources){
        super(boardController, screenWidth, screenHeight);
        instructionsFont = new Font(10, 10, 10, screenHeight/22, Typeface.SANS_SERIF, Typeface.NORMAL);

        mainMenu =  new TextButton(screenWidth/12, (float)screenHeight*9/10, "Go back to main menu", buttonStyle);
        easy = new TextButton(screenWidth/12, (float)screenHeight*8/10, "Easy", buttonStyle);
        medium = new TextButton(screenWidth*4/12, (float)screenHeight*8/10, "Medium", buttonStyle);
        hard = new TextButton(screenWidth*9/12, (float)screenHeight*8/10, "Hard", buttonStyle);
        mainMenu.addWidgetListener(this);
        easy.addWidgetListener(this);
        medium.addWidgetListener(this);
        hard.addWidgetListener(this);
        addTouchListener(mainMenu);
        addTouchListener(easy);
        addTouchListener(medium);
        addTouchListener(hard);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        easy.draw(canvas);
        medium.draw(canvas);
        hard.draw(canvas);
        drawDifficulcyBox(canvas);
        drawInstructions(canvas);
    }

    private void drawInstructions(Canvas canvas){
        canvas.drawText("When the game is started", 30, screenHeight*2/20, instructionsFont);
        canvas.drawText("the time ticks down.", 30, screenHeight*3/20, instructionsFont);
        canvas.drawText("Swipe cards in the ", 30, screenHeight*5/20, instructionsFont);
        canvas.drawText("direction of the arrow.", 30, screenHeight*6/20, instructionsFont);
        canvas.drawText("to regain time.", 30, screenHeight*7/20, instructionsFont);
        canvas.drawText("The game is finished when", 30, screenHeight*9/20, instructionsFont);
        canvas.drawText("you have no time left.", 30, screenHeight*10/20, instructionsFont);
        canvas.drawText("Made by: Hans, Henrik, Lars, Marie, Sigrid og Synne.", 30, screenHeight*12/20, new Font(10,10,10,screenHeight/45,Typeface.SANS_SERIF, Typeface.NORMAL));
    }
    private void drawDifficulcyBox(Canvas canvas){
        canvas.drawText("Difficulty:", screenWidth/12, screenHeight*7/10, buttonFont);
        int x1 = 0, x2=0;
        if(boardController.getDifficulty() == Difficulty.easy){
            x1 = screenWidth/12;
            x2 = screenWidth*3/12;
        }else if(boardController.getDifficulty() == Difficulty.medium){
            Log.d(TAG, "drawing medium difficulcy box");
            x1 = screenWidth*4/12;
            x2 = screenWidth*6/12;
        }else if(boardController.getDifficulty() == Difficulty.hard){
            x1 = screenWidth*9/12;
            x2 = screenWidth*11/12;
        }
        Rect rect = new Rect(x1, screenHeight*8/10+20, x2 , screenHeight*8/10+30);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setARGB(255,255,255,255);
        canvas.drawRect(rect, paint);

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: go back to main menu");
            boardController.goToMainMenu();
        }else if(widgetAction.getSource() == easy){
            boardController.setDifficulty(Difficulty.easy);
            update(0.1f);
        }else if(widgetAction.getSource() == medium){
            boardController.setDifficulty(Difficulty.medium);
            update(0.1f);
        }else if(widgetAction.getSource() == hard) {
            boardController.setDifficulty(Difficulty.hard);
            update(0.1f);
        }

    }
}
