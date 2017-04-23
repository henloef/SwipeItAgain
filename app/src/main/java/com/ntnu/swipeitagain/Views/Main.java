package com.ntnu.swipeitagain.Views;
import com.google.firebase.*;
/**
 * Created by Henrik on 26.03.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;
import com.ntnu.swipeitagain.Models.GameData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import sheep.game.Game;

import static com.google.android.gms.internal.zzt.TAG;

public class Main extends Activity{
    private BoardController boardController;

    public int screenWidth, screenHeight;
    private ServerCommunicator serverCommunicator ;
    private String playerId;

    //Lagret info om spill og gamePins
    final ArrayList<GameData> gameDatas = new ArrayList<GameData>();

        // Generating a random String for this run called playerId
    private String aToZ="ABCD.....1234"; // 36 letter.

    private static String generateRandom(String aToZ) {
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randIndex=rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        }
        return res.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){

        //Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        this.playerId = generateRandom(aToZ);
        System.out.println("playerId" + playerId);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        serverCommunicator  = new ServerCommunicator();


        Game game = new Game(this, null);
        this.boardController = new BoardController(game,screenWidth, screenHeight, serverCommunicator, playerId);
        game.pushState(new MainMenu(boardController, game,  screenWidth, screenHeight));
        setContentView(game);

    }
}
