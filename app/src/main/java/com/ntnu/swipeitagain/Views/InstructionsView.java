package com.ntnu.swipeitagain.Views;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.Difficulcy;

import sheep.gui.TextButton;
import sheep.gui.WidgetAction;

import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 27.03.2017.
 */

public class InstructionsView extends AbstractMenuView {

    private TextButton easy, medium, hard;

    private Resources resources;
    public InstructionsView(BoardController boardController, int screenWidth, int screenHeight, Resources resources){
        super(boardController, screenWidth, screenHeight);
        this.resources = resources;

        mainMenu =  new TextButton(100, (float)screenHeight*2/10, "Main menu", buttonStyle);
        easy = new TextButton(screenWidth/8, (float)screenHeight*9/10, "Easy", buttonStyle);
        medium = new TextButton(screenWidth*3/8, (float)screenHeight*9/10, "Medium", buttonStyle);
        hard = new TextButton(screenWidth*6/8, (float)screenHeight*9/10, "Hard", buttonStyle);
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
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: go back to main menu");
            boardController.goToMainMenu();
        }else if(widgetAction.getSource() == easy){
            //Testkode ikke bra måte å gjøre det på
            //easy = new TextButton(screenWidth/8, (float)screenHeight*9/10, "Easy", pressedButtonStyle);
            boardController.setDifficulcy(Difficulcy.easy);
        }else if(widgetAction.getSource() == medium){
            boardController.setDifficulcy(Difficulcy.medium);
        }else if(widgetAction.getSource() == hard) {
            boardController.setDifficulcy(Difficulcy.hard);
        }

    }
}
