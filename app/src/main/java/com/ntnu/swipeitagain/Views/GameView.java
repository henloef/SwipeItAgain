package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ProgressBar;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.CardModel;
import com.ntnu.swipeitagain.Models.Direction;
import com.ntnu.swipeitagain.Models.GameModel;
import com.ntnu.swipeitagain.R;

import sheep.collision.Rectangle;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;
import sheep.input.TouchListener;
import sheep.util.Timer;

import static android.content.ContentValues.TAG;

/**
 * Created by Lars on 27.03.2017.
 */

public abstract class GameView extends State  {

    BoardController boardController;
    protected int screenWidth, screenHeight;
    protected ProgressBar progressBar;
    protected GameModel gameModel;


    public GameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel1) {
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameModel = gameModel1;

    }

    private MotionEvent startEvent;
    private MotionEvent endEvent;
    private boolean swiped;
    private Direction swipeDirection;

    @Override
    public boolean onTouchDown(MotionEvent motionEvent) {
        if (gameModel.getCurrentCard().getBoundingBox().contains(motionEvent.getX(),  motionEvent.getY())) {
            startEvent = motionEvent;
            return true;
        }

        if(swiped){
            boolean swipedCorrect = boardController.tryDirection(boardController.decideDirection(startEvent,endEvent));
            if(swipedCorrect){
                gameModel.nextCard();
                gameModel.getCurrentCard().update(0.2f);
            }
        }
        //boardController.pushState(new GameOver(boardController, screenWidth, screenHeight));
        return false;
    }

    @Override
    public boolean onTouchUp(MotionEvent motionEvent) {
        if (swiped) {
            endEvent = motionEvent;
            //calculate direction
            swipeDirection = boardController.decideDirection(startEvent, endEvent);
        }
        return false;
    }


    //Touch fungerer, men kortet beveger seg ikke rett.
    @Override
    public boolean onTouchMove(MotionEvent motionEvent) {
        if(gameModel.getCurrentCard().getBoundingBox().contains(motionEvent.getX(),  motionEvent.getY())){

            gameModel.getCurrentCard().setPosition(motionEvent.getX(), motionEvent.getY());
            gameModel.getCurrentCard().setScale(1,1);
            gameModel.getCurrentCard().update(0.1f);
            Log.d(TAG, "Swiped X: " + motionEvent.getX()+ " Y: " + motionEvent.getY());



            return true;

        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.LTGRAY);
        drawProgressBars(canvas);

        //positions card in the middle
        gameModel.getCurrentCard().setScale(1,1);
        gameModel.getCurrentCard().update(0.2f);
        gameModel.getCurrentCard().setPosition((float)screenWidth/2, (float)screenHeight/2);
        gameModel.getCurrentCard().draw(canvas);
    }

    private void drawProgressBars(Canvas canvas){
        int yourTime = gameModel.getCurrentTime(true);
        Log.d(TAG, "current time: " + yourTime);
        double yourProgress = (1.0 * yourTime)/100;
        int yourBarEnd = 100+(int)(screenWidth * (yourProgress * ((1.0 * screenWidth - 200) / screenWidth)));
        Rect rect = new Rect(100, screenHeight - 200, yourBarEnd , screenHeight - 170);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Color.argb(255,255 - (int)(yourProgress*255),(int) (yourProgress *255),0)); //gradually from green to red
        canvas.drawRect(rect, paint);

        int oppTime = gameModel.getCurrentTime(false);
        Log.d(TAG, "current time: " + oppTime);
        double oppProgress = (1.0 * oppTime)/100;
        int oppBarEnd = 100+(int)(screenWidth * (oppProgress * ((1.0 * screenWidth - 200) / screenWidth)));
        Rect oppRect = new Rect(100, screenHeight - 150, oppBarEnd , screenHeight - 120);
        Paint oppPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        oppPaint.setColor(Color.argb(255,255 - (int)(oppProgress*255),(int) (oppProgress *255),0)); //gradually from green to red
        canvas.drawRect(oppRect, oppPaint);
    }


    @Override
    public void update(float dt) {
        super.update(dt);
        boardController.updateGame();
        // TODO boardController.doYourThing()
    }
}

