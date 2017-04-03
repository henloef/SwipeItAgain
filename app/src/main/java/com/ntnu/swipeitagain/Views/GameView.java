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

public abstract class GameView extends State implements WidgetListener {

    BoardController boardController;
    protected int screenWidth, screenHeight;
    protected TextButton goDirectlyToGameOver;
    protected ProgressBar progressBar;
    //protected CardModel currentCard; //View skal vel ikke ha direkte tilgang på denne?
    protected GameModel gameModel;

    //Burde egentlig ikke være i view
    protected Timer timer;
    protected float counter;

    public GameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel1) {
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameModel = gameModel1;
        this.timer = new Timer();

        /*fungerer ikke
        this.addTouchListener(new TouchListener() {
            @Override
            public boolean onTouchDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onTouchUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onTouchMove(MotionEvent motionEvent) {
                //if(gameModel.getCurrentCard().getBoundingBox().contains(motionEvent.getX(),  motionEvent.getY())){
                    gameModel.getCurrentCard().setPosition(motionEvent.getX(), motionEvent.getY());
                //}
                return false;
            }
        });*/

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLUE);
        goDirectlyToGameOver.draw(canvas);

        int time = gameModel.getCurrentTime();
        Log.d(TAG, "current time: " + time);
        double progress = (1.0 * time)/100;
        int barEnd = (int)(screenWidth * (progress * ((1.0 * screenWidth - 200) / screenWidth)));
        Rect rect = new Rect(100, screenHeight - 300, barEnd , screenHeight - 250);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if(time > 66)       paint.setColor(Color.GREEN); //lots of time left
        else if (time > 33) paint.setColor(Color.YELLOW);//quite a bit of time left
        else paint.setColor(Color.RED);   //running low on time */

        paint.setColor(Color.argb(255,255 - (int)(progress*255),(int) (progress *255),0)); //gradually from green to red
        canvas.drawRect(rect, paint);


        gameModel.getCurrentCard().draw(canvas);


    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == goDirectlyToGameOver) {
            boardController.pushState(new GameOver(boardController, screenWidth, screenHeight));
        }
    }
/*
    @Override
    public void update(float dt) { //TODO må mest sannsynlig flyttes
        super.update(dt);

        //Burde være via boardcontroller
        counter += timer.getDelta();
        if(counter >=1.0){
            gameModel.getPlayer().timeTick();
            counter = 0.0f;
        }
        // TODO boardController.doYourThing()
    }*/
}

