package com.ntnu.swipeitagain.States;

import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public class MultiPlayerState extends GameState{
    private int gameKey;
    private ServerCommunicator serverCommunicator;
    private String playerId;
    public MultiPlayerState(BoardController boardController, Boolean generateKey, ServerCommunicator serverCommunicator, String playerId){
        super(boardController);
        this.serverCommunicator = serverCommunicator;
        this.playerId = playerId;


        if(generateKey) {
            getGameKeyFromServer();
        }else{

        }

        startGame();
    }


    //Ask server for random gameKey
    public void getGameKeyFromServer(){
        //TODO connect to server and get gameKey
        //gameKey = serverCommunicator.getGameKeyFromServer();
        serverCommunicator.addNewGameDataToDatabase();
    }

    public int showGameKey(){
        return gameKey;
    }


    //try random gameKey to server
    public boolean tryGameKey(int gameKey){
        System.out.println("Try gamekey i state ");
        //List<> serverCommunicator.getGameDatas();


        if(serverCommunicator.tryGameKey(gameKey) != 0){
            return true;
        }else{
            Log.d(TAG, "No active game with that key");
            return false;
        }
    }

    public void startGame(){
        serverCommunicator.sendStartSignal();
    }
}
