package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.GameModel;

import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetListener;

import static android.content.ContentValues.TAG;

/**
 * Created by Sigrid on 26.03.2017.
 */

public class MultiPlayerGameView extends GameView {

    public MultiPlayerGameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel){
        super(boardController, screenWidth, screenHeight, gameModel);

    }

    public void update(float dt){
        super.update(dt);
        //boardController.getOpponentScore();
    }

    //Draws opponent game bar as well as the player one
    @Override
    protected void drawProgressBars(Canvas canvas){
        super.drawProgressBars(canvas);
        drawOpponentProgressBar(canvas);
    }
    private void drawOpponentProgressBar(Canvas canvas){
        int oppTime = gameModel.getCurrentTime(false);
        Log.d(TAG, "current opponent time: " + oppTime);
        double oppProgress = (1.0 * oppTime)/100;
        int oppBarEnd = 100+(int)(screenWidth * (oppProgress * ((1.0 * screenWidth - 200) / screenWidth)));
        Rect oppRect = new Rect(100, screenHeight - 150, oppBarEnd , screenHeight - 120);
        Paint oppPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        oppPaint.setColor(Color.argb(255,255 - (int)(oppProgress*255),(int) (oppProgress *255),0)); //gradually from green to red
        canvas.drawRect(oppRect, oppPaint);
    }
}