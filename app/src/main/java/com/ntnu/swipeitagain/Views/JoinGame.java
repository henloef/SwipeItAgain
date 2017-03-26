package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;

import java.util.ArrayList;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class JoinGame extends State implements WidgetListener {

    BoardController boardController;
    private int screenWidth, screenHeigth;
    private ArrayList<TextButton> buttons;
    private TextButton backSpace, enter, mainMenu;
    private String enteredGameKey;
    private String instructionText;

    public JoinGame(BoardController boardController, int screenWidth, int screenHeight){
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeigth = screenHeight;

        buttons = new ArrayList<TextButton>();
        enteredGameKey = new String();
        instructionText = "Enter game key:";

        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};

        makeKeyButtons();
        enter = new TextButton(screenWidth*3/4, (float)screenHeight*6/8, "Enter", buttonStyle);
        backSpace = new TextButton(screenWidth/4, (float)screenHeight*6/8, "<-", buttonStyle);
        mainMenu = new TextButton(100, (float)screenHeight*7/8, "Main menu", buttonStyle);

        enter.addWidgetListener(this);
        backSpace.addWidgetListener(this);
        mainMenu.addWidgetListener(this);

        addTouchListener(enter);
        addTouchListener(backSpace);
        addTouchListener(mainMenu);

    }

    private void makeKeyButtons(){
        Font buttonFont = new Font(255, 255, 255, 100, Typeface.SANS_SERIF, Typeface.NORMAL);
        Paint[] buttonStyle = {buttonFont, buttonFont};
        for(int i = 0; i <10; i++){
            if (i == 0){
                float x = screenWidth*2/4;
                float y = screenHeigth*6/8;
                buttons.add(new TextButton(x,y, Integer.toString(i), buttonStyle));
            }else {
                float x = (((i-1) % 3)+1)*screenWidth/4;
                float y = ((float)Math.ceil(((float)i)/3) + 2)*screenHeigth/8;
                buttons.add(new TextButton(x,y, Integer.toString(i), buttonStyle));
            }
        }
        for (TextButton button: buttons){
            button.addWidgetListener(this);
            addTouchListener(button);

        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.CYAN);
        mainMenu.draw(canvas);
        enter.draw(canvas);
        backSpace.draw(canvas);
        for (TextButton button: buttons){
            button.draw(canvas);
        }
        Font font = new Font(255, 255, 255, 130, Typeface.SANS_SERIF, Typeface.NORMAL);
        canvas.drawText(instructionText, 100, screenHeigth*1/8, font);

        Font gameKeyFont = new Font(10, 10, 10, 130, Typeface.SANS_SERIF, Typeface.NORMAL);
        if(!enteredGameKey.isEmpty()) {
            canvas.drawText(enteredGameKey, screenWidth / 3, screenHeigth * 2 / 8, gameKeyFont);
        }
    }

    public void tryNewGameKey(){
        instructionText = "Try new game key:";
        enteredGameKey = "";

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: mainMenu");
            boardController.goToMainMenu(2);
        } else if (widgetAction.getSource() == enter){
            Log.d(TAG, "trying game key");
            boardController.tryEnteredGameKey(Integer.parseInt(enteredGameKey));
        }else if(widgetAction.getSource() == backSpace){
            enteredGameKey = enteredGameKey.substring(0, enteredGameKey.length()-1);
        }else if (buttons.contains(widgetAction.getSource()) ){
            enteredGameKey = enteredGameKey + Integer.toString(buttons.indexOf(widgetAction.getSource()));
        }

    }


}
