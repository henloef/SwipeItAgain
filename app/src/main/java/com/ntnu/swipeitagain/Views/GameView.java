package com.ntnu.swipeitagain.Views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.widget.ProgressBar;

import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.collision.Rectangle;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * Created by Lars on 27.03.2017.
 */

public abstract class GameView extends State implements WidgetListener {

    BoardController boardController;
    protected int screenWidth, screenHeight;
    protected TextButton goDirectlyToGameOver;
    protected ProgressBar progressBar;

    public GameView(BoardController boardController, int screenWidth, int screenHeight) {
        this.boardController = boardController;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLUE);
        goDirectlyToGameOver.draw(canvas);

        int time = 10;  //TODO boardController.getGameModel().getPlayer().getCurrentTime();
        double progress = (1.0 * time)/100;
        int barEnd = (int)(screenWidth * (progress * ((1.0 * screenWidth - 200) / screenWidth)));
        Rect rect = new Rect(100, screenHeight - 300, barEnd , screenHeight - 250);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        /*if(time > 66)       paint.setColor(Color.GREEN); //lots of time left
        else if (time > 33) paint.setColor(Color.YELLOW);//quite a bit of time left
        else               paint.setColor(Color.RED);   //running low on time */

        paint.setColor(Color.argb(255,255 - (int)(progress*255),(int) (progress *255),0)); //gradually from green to red



        canvas.drawRect(rect, paint);
    }

    @Override
    public void actionPerformed(WidgetAction widgetAction) {
        if (widgetAction.getSource() == goDirectlyToGameOver) {
            boardController.pushState(new GameOver(boardController, screenWidth, screenHeight));
        }
    }
}

