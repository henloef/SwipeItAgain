package com.ntnu.swipeitagain.States;

import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public class MultiPlayerState extends GameState{
    private int gameKey;
    private ServerCommunicator serverCommunicator ;


    public MultiPlayerState(BoardController boardController, Boolean generateKey){
        super(boardController);
        serverCommunicator  = new ServerCommunicator();
        serverCommunicator.connectSocket();
        serverCommunicator.addToDatabase("users", "user1", "Det er meg det");
        serverCommunicator.addToDatabase("users", "user2", "Det er hans nå");

        if(generateKey) {
            getGameKeyFromServer();
        }else{

        }

        startGame();
    }


    //Ask server for random gameKey
    public void getGameKeyFromServer(){
        //TODO connect to server and get gameKey
        gameKey = serverCommunicator.getGameKeyFromServer();
    }

    public int showGameKey(){
        return gameKey;
    }


    //try random gameKey to server
    public boolean tryGameKey(int gameKey){
        if(serverCommunicator.tryGameKey(gameKey) != 0){
            return true;
        }else{
            Log.d(TAG, "No active game with that key");
            return false;
        }
    }

    public void startGame(){
        boardController.startGame();
        serverCommunicator.sendStartSignal();
    }
}
