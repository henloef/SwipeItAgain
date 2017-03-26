package com.ntnu.swipeitagain.States;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;

/**
 * Created by Henrik on 26.03.2017.
 */

public class MultiPlayerState extends GameState{
    private int gameKey;
    private ServerCommunicator serverCommunicator;

    public MultiPlayerState(BoardController boardController, Boolean generateKey){
        super(boardController);
        if(generateKey) {
            getGameKey();
            //set or get opponentPlayer trenger kanskje en while-loop for å vente på motspiller et sted
            //Tror getOpponentPlayer blir best
        }else{
            connectToOpponent();
        }
        startGame();
    }



    //Ask server for opponent
    private void getOpponentPlayer(){
        /*
        if(serverCommunicator.requestedGameKey == gameKey){
            //What to do if it is the correct gameKey
        }*/
    }

    //Ask server for random gameKey
    public void getGameKey(){
        //TODO connect to server and get gameKey
        gameKey = serverCommunicator.getGameKey();
    }

    public void connectToOpponent(){
        boolean waitingForOpponentPlayer = true;
        while(waitingForOpponentPlayer){
            if(tryGameKey(boardController.getInputGameKey()))
                waitingForOpponentPlayer = false;
        }
    }
    //try random gameKey to server
    public boolean tryGameKey(int gameKey){
        if(serverCommunicator.tryGameKey(gameKey) != 0){
            //FÅR MAN I DET HELE TATT BOARDCONTROLLER FRA OPPONENT? setOpponentPlayer(gameKey, );
            return true;
        }else{
            System.out.println("No active game with that key");
            return false;
        }
    }

    public void startGame(){
        boardController.startGame();
        serverCommunicator.sendStartSignal();
    }
}
