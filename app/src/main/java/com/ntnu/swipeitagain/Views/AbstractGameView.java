package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.Direction;
import com.ntnu.swipeitagain.Models.GameModel;
import sheep.game.State;
import sheep.graphics.Font;

import static android.content.ContentValues.TAG;

/**
 * Created by Group 22 on 27.03.2017.
 */

public abstract class AbstractGameView extends State  {

    BoardController boardController;
    protected int screenWidth, screenHeight;
    protected GameModel gameModel;
    protected Font scoreFont;

    public AbstractGameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel1) {
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameModel = gameModel1;
        gameModel.getCurrentCard().setPosition((float)screenWidth/2, (float)screenHeight/2);
        scoreFont = new Font(100, 100, 100, screenHeight/34, Typeface.SANS_SERIF, Typeface.NORMAL);
    }

    private MotionEvent endEvent;
    private boolean swiped;
    private Direction swipeDirection;

    @Override
    public boolean onTouchDown(MotionEvent motionEvent) {
        if (gameModel.getCurrentCard().getBoundingBox().contains(motionEvent.getX(),  motionEvent.getY())) {
            swiped = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchUp(MotionEvent motionEvent) {
        if (swiped) {
            endEvent = motionEvent;
            //lets boardController decide direction
            swipeDirection = boardController.decideDirection(endEvent);
            boolean swipedCorrect = boardController.tryDirection(swipeDirection);
            if(swipedCorrect){
                gameModel.nextCard();
                gameModel.getCurrentCard().setPosition((float)screenWidth/2, (float)screenHeight/2);
                Log.d(TAG, "Swiped correctly");
                gameModel.getPlayer().addTime();
                gameModel.getPlayer().newPoint();
                update(0.1f);
            }else{
                gameModel.getCurrentCard().setPosition((float)screenWidth/2, (float)screenHeight/2);
                Log.d(TAG, "Swiped incorrectly");
            }
        }
        return false;
    }


    @Override
    public boolean onTouchMove(MotionEvent motionEvent) {
        if(gameModel.getCurrentCard().getBoundingBox().contains(motionEvent.getX(),  motionEvent.getY())){

            gameModel.getCurrentCard().setPosition(motionEvent.getX(), motionEvent.getY());
            gameModel.getCurrentCard().setScale(1,1);
            gameModel.getCurrentCard().update(0.1f);
            swiped = true;
            return true;

        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.LTGRAY);
        drawProgressBars(canvas);
        drawCard(canvas);
        canvas.drawText(Integer.toString(gameModel.getPlayer().getScore()), 30, screenHeight-170, scoreFont);

    }

    @Override
    public void update(float dt) {
        super.update(dt);
        boardController.updateGame();
        // TODO boardController.doYourThing()
    }

    private void drawCard(Canvas canvas){
        //positions card in the middle
        gameModel.getCurrentCard().setScale(1,1);
        gameModel.getCurrentCard().update(0.2f);
        gameModel.getCurrentCard().draw(canvas);
    }

    protected void drawProgressBars(Canvas canvas){
        //draws player progress bar
        int yourTime = gameModel.getCurrentTime(true);
        Log.d(TAG, "current time: " + yourTime);
        double yourProgress = (1.0 * yourTime)/100;
        int yourBarEnd = screenWidth/8+(int)(screenWidth * (yourProgress * ((1.0 * screenWidth - screenWidth/4) / screenWidth)));
        Rect rect = new Rect(screenWidth/8, screenHeight - 200, yourBarEnd , screenHeight - 170);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Color.argb(255,255 - (int)(yourProgress*255),(int) (yourProgress *255),0)); //gradually from green to red
        canvas.drawRect(rect, paint);
    }
}

