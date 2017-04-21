package com.ntnu.swipeitagain.States;

import android.util.Log;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;
import com.ntnu.swipeitagain.Models.GameData;
import com.ntnu.swipeitagain.Models.PlayerModel;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public class MultiPlayerState extends GameState{
    private int gameKey;
    private ServerCommunicator serverCommunicator;
    private String playerId;

    private PlayerModel playerModel;
    private GameData gameData;

    public MultiPlayerState(BoardController boardController, Boolean generateKey, ServerCommunicator serverCommunicator, String playerId){
        super(boardController);
        this.serverCommunicator = serverCommunicator;
        this.playerId = playerId;
        //serverCommunicator.getAllGameDataFromServer();
        if(generateKey) {
            getGameDataFromServer();
        }

    }


    //Ask server for gameKey
    public void getGameDataFromServer(){
       this.gameData = serverCommunicator.getGameDataFromServer();
        // this.gameKey = serverCommunicator.getGameKeyFromServer();
        //serverCommunicator.addNewGameDataToDatabase();
        gameData.setPlayer(boardController.getGameModel().getPlayer());
        this.gameKey = gameData.getGameKey();
    }

    public int showGameKey(){
        Log.d(TAG, "gameKey vises nå  " + gameKey + " size " + serverCommunicator.getGameDatas().size());
        return gameKey;
    }

    //try random gameKey to server
    public boolean tryGameKey(int gameKey){
        Log.d(TAG,"Try gamekey i state ");
        if(serverCommunicator.tryGameKey(gameKey)){
            this.gameData = serverCommunicator.GameDataFromKey(gameKey);
            this.gameData.setOpponent(boardController.getGameModel().getPlayer());
            serverCommunicator.opponentReady(this.gameData);
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
