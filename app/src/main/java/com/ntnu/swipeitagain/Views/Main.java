package com.ntnu.swipeitagain.Views;

/**
 * Created by Henrik on 26.03.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ntnu.swipeitagain.Controllers.BoardController;

import sheep.game.Game;
public class Main extends Activity{
    private BoardController boardController;

    public int screenWidth, screenHeight;
    private FirebaseAnalytics mFirebaseAnalytics;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Game game = new Game(this, null);
        this.boardController = new BoardController(game, game.getResources(),screenWidth, screenHeight);
        game.pushState(new MainMenu(boardController, game, game.getResources(), screenWidth, screenHeight));
        setContentView(game);
    }
}
