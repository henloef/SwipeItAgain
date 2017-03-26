package com.ntnu.swipeitagain.Views;

/**
 * Created by Henrik on 26.03.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;

import sheep.game.Game;
public class Main extends Activity{
    private BoardController boardController;
    public int SCREEN_WIDTH;
    public int SCREEN_HEIGHT;
    ServerCommunicator sc = new ServerCommunicator();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        SCREEN_WIDTH = displayMetrics.widthPixels;
        SCREEN_HEIGHT = displayMetrics.heightPixels;

        Game game = new Game(this, null);
        this.boardController = new BoardController(game);
        game.pushState(new MainMenu(game, game.getResources(), SCREEN_WIDTH, SCREEN_HEIGHT));
        setContentView(game);
        sc.connectSocket();
    }
}
