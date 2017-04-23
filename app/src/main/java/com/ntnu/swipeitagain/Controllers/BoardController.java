package com.ntnu.swipeitagain.Controllers;
import android.util.Log;
import android.view.MotionEvent;

import com.ntnu.swipeitagain.Models.Direction;
import com.ntnu.swipeitagain.Models.GameData;
import com.ntnu.swipeitagain.Models.GameModel;
import com.ntnu.swipeitagain.States.AbstractGameState;
import com.ntnu.swipeitagain.States.MultiPlayerState;
import com.ntnu.swipeitagain.States.SinglePlayerState;
import com.ntnu.swipeitagain.Views.GameOver;
import com.ntnu.swipeitagain.Views.JoinGame;
import com.ntnu.swipeitagain.Views.MainMenu;
import com.ntnu.swipeitagain.Views.MultiPlayerGameView;
import com.ntnu.swipeitagain.Views.SinglePlayerGameView;


import java.util.ArrayList;

import sheep.game.Game;
import sheep.game.State;
import sheep.util.Timer;

import static android.content.ContentValues.TAG;

/**
 * Created by Group 22 on 26.03.2017.
 */

public class BoardController {
        private GameModel gameModel;
        private AbstractGameState gameState;
        private boolean isMultiPlayer;
        private ArrayList<State> states;
        private Game game;
        private int screenWidth, screenHeight;
        private Timer timer;
        private float counter;
        private float updateTime; //Difficulty
        private ServerCommunicator serverCommunicator;
        private String playerId;
        private GameData gameData;


        public BoardController(Game game, int screenWidth, int screenHeight, ServerCommunicator serverCommunicator, String playerId){
            this.game = game;
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
            states = new ArrayList<State>();
            this.timer = new Timer();
            this.serverCommunicator = serverCommunicator;
            this.playerId = playerId;
            this.gameData = new GameData();
            setDifficulty(Difficulty.easy);
        }

        public void updateGame(){
            counter += timer.getDelta();
            if(counter >= updateTime){
                gameModel.getPlayer().timeTick();
                gameModel.getOpponent().timeTick();
                counter = 0.0f;
            }
            if (!gameModel.getPlayer().timeLeft())
            {pushState(new GameOver(this, screenWidth,screenHeight, gameModel));}
        }

        public void pushState(State state){
            game.pushState(state);
            Log.d(TAG, "pushed state. length now: "+ states.size());
        }


        public void createGameState(Boolean isMultiPlayer, Boolean generateKey) {
            createGameModel();
            if (isMultiPlayer){ gameState = new MultiPlayerState(this, generateKey, serverCommunicator, playerId);
                this.isMultiPlayer = isMultiPlayer;
                // Få fra input om man venter på motstander eller generer gamekey
                pushState(new MultiPlayerGameView(this,screenWidth,screenHeight, gameModel));
            }
            else{ gameState = new SinglePlayerState(this);
                pushState(new SinglePlayerGameView(this, screenWidth, screenHeight, gameModel));// dette må endres, vi klarer jo ikke å kjøre en loop for å spille når denne pushes?
                Log.d(TAG, "New GameStarted");
            }

        }

        public GameModel getGameModel(){
            return gameModel;
        }

        public void createGameModel(){
            gameModel = new GameModel();
        }

        public int getGeneratedGameKey(){
            if(gameState instanceof MultiPlayerState){
                int gameKey = ((MultiPlayerState) gameState).showGameKey();
                return gameKey;
            }else{
                Log.d(TAG, "Not multiplayer game");
                return 0;
            }
        }

        public AbstractGameState getGameState(){
            return gameState;
        }
        //Called from joinGame


        public void tryEnteredGameKey(int gameKey){
            if(gameState instanceof MultiPlayerState){
                if(((MultiPlayerState) gameState).tryGameKey(gameKey)){
                    Log.d(TAG, "joining game");
                    pushState(new MultiPlayerGameView(this, screenWidth, screenHeight, gameModel));
                    //TODO connect to opponent
                }else{
                    if(states.get(0) instanceof JoinGame){
                        ((JoinGame) states.get(0)).tryNewGameKey();
                    }
                }
            }else{
                Log.d(TAG, "Not multiplayer game");
            }
        }

        //when gameOver option retry is chosen
        public void retry(){
            if(isMultiPlayer){
                //TODO Ask the oponent for a rematch
            }else{
                gameModel = new GameModel();
                pushState(new SinglePlayerGameView(this, screenWidth, screenHeight, gameModel));
            }
        }

        public void setDifficulty(Difficulty difficulty){
            if(difficulty == Difficulty.hard){
                updateTime = 0.03f;
                Log.d(TAG, "difficulty set to hard");
            }else if(difficulty == Difficulty.medium){
                updateTime = 0.07f;
                Log.d(TAG, "difficulty set to medium");
            }else if(difficulty == Difficulty.easy){
                updateTime = 0.1f;
                Log.d(TAG, "difficulty set to easy");
            }
        }

        public Difficulty getDifficulty(){
            if(updateTime == 0.03f){
                return Difficulty.hard;
            }else if(updateTime == 0.07f){
                return  Difficulty.medium;
            }else if(updateTime ==0.1f){
                return Difficulty.easy;
            }
            return null;
        }

        public void goToMainMenu(){
        game.pushState(new MainMenu(this, game, screenWidth,screenHeight));
        }

        public boolean tryDirection(Direction direction){
            return gameModel.checkDirection(direction);
        }

    public Direction decideDirection(MotionEvent end){
        float deltaX = end.getX() - (float)screenWidth/2;
        float deltaY = end.getY() - (float)screenHeight/2;
        if(Math.abs(deltaX) > Math.abs(deltaY)){
            if(deltaX > 0){
                return Direction.RIGHT;
            }else{
                return Direction.LEFT;
            }
        }else{
            if(deltaY > 0){
                return Direction.DOWN;
            }else{
                return Direction.UP;
            }
        }
    }

}
