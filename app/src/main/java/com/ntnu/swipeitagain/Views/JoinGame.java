package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.util.Log;
import com.ntnu.swipeitagain.Controllers.BoardController;
import java.util.ArrayList;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class JoinGame extends AbstractMenuView {

    private ArrayList<TextButton> buttons;
    private TextButton backSpace, enter;
    private String enteredGameKey;
    private String instructionText;
    private int offset = 25;


    public JoinGame(BoardController boardController, int screenWidth, int screenHeigth){
        super(boardController, screenWidth, screenHeigth);

        buttons = new ArrayList<TextButton>();
        enteredGameKey = new String();
        instructionText = "Enter game key:";


        makeKeyButtons();
        enter = new TextButton(screenWidth*3/4-offset, (float)screenHeight*6/8, "Enter", buttonStyle);
        backSpace = new TextButton(screenWidth/4-offset, (float)screenHeight*6/8, "<-", buttonStyle);

        enter.addWidgetListener(this);
        backSpace.addWidgetListener(this);

        addTouchListener(enter);
        addTouchListener(backSpace);
    }

    private void makeKeyButtons(){
        for(int i = 0; i <10; i++){
            if (i == 0){
                float x = screenWidth*2/4-offset;
                float y = screenHeight*6/8;
                buttons.add(new TextButton(x,y, Integer.toString(i), buttonStyle));
            }else {
                float x = (((i-1) % 3)+1)*screenWidth/4 -offset;
                float y = ((float)Math.ceil(((float)i)/3) + 2)*screenHeight/8;
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
        enter.draw(canvas);
        backSpace.draw(canvas);
        for (TextButton button: buttons){
            button.draw(canvas);
        }
        canvas.drawText(instructionText, 100, screenHeight*1/8, bigFont);

        if(!enteredGameKey.isEmpty()) {
            canvas.drawText(enteredGameKey, screenWidth / 3, screenHeight * 2 / 8, gameKeyFont);
        }
    }

    public void tryNewGameKey(){
        instructionText = "Try new game key:";
        enteredGameKey = "";
        boardController.createGameState(true, false);

    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == mainMenu) {
            Log.d(TAG, "actionPerformed: mainMenu");
            boardController.goToMainMenu();
        } else if (widgetAction.getSource() == enter){
            Log.d(TAG, "trying game key");
            if(enteredGameKey.length() > 0) {
                boardController.tryEnteredGameKey(Integer.parseInt(enteredGameKey));
                //TODO Get connected to the game with the same gamepin
            }
        }else if(widgetAction.getSource() == backSpace){
            if(enteredGameKey.length() != 0) enteredGameKey = enteredGameKey.substring(0, enteredGameKey.length()-1);
        }else if (buttons.contains(widgetAction.getSource()) ){
            if(enteredGameKey.length() < 4){
                enteredGameKey = enteredGameKey + Integer.toString(buttons.indexOf(widgetAction.getSource()));
            }else{
                Log.d(TAG, "Not room for more digit");
            }

        }

    }


}
