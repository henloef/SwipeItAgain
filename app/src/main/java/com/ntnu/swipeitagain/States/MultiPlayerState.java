package com.ntnu.swipeitagain.States;

import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;
import com.ntnu.swipeitagain.Models.GameData;

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
        }
        serverCommunicator.getGameDataFromServer();
    }


    //Ask server for gameKey
    public void getGameKeyFromServer(){
        gameKey = serverCommunicator.getGameKeyFromServer();
        //serverCommunicator.addNewGameDataToDatabase();
    }

    public int showGameKey(){
        Log.d(TAG, "gameKey vises nå  " + gameKey + " size " + serverCommunicator.getGameDatas().size());
        return gameKey;
    }

    //try random gameKey to server
    public boolean tryGameKey(int gameKey){
        Log.d(TAG,"Try gamekey i state ");
        if(serverCommunicator.tryGameKey(gameKey)){
            serverCommunicator.sendStartSignal(gameKey);
            //startGame();
            return true;
        }else{
            Log.d(TAG, "No active game with that key");
            return false;
        }
    }

    public void startGame(){
        //TODO Do we need this function, or does
    }

}
