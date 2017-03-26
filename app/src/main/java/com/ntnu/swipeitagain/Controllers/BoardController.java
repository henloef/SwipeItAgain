package com.ntnu.swipeitagain.Controllers;

import android.app.Activity;

import com.ntnu.swipeitagain.States.GameState;
import com.ntnu.swipeitagain.States.SinglePlayerState;

/**
 * Created by Henrik on 26.03.2017.
 */

public class BoardController {
        private GameModel gameModel;
        private GameState gameState;
        private boolean isMultiPlayer;
        private Activity activity;


        public BoardController(){
            this.activity = new MainMenuScreen();
        }


        private void createGameState(Boolean isMultiPlayer, Boolean generateKey) {
            if (isMultiPlayer){ gameState = new MultiPlayerState(this, generateKey);
                this.isMultiPlayer = isMultiPlayer;
                // Få fra input om man venter på motstander eller generer gamekey
            }
            else{ gameState = new SinglePlayerState(this);}
        }

        public GameModel getGameModel(){
            return gameModel;
        }

        public void createGameModel(){
            new GameModel();
        }

        public int getInputGameKey(){
            //Get input from screen
            return 0;
        }

        public void startGame(){
            //   gameModel.startGame();
        }

        public boolean tryDirection(Direction direction){
            return gameModel.checkDirection(direction);
        }

        public void play(){
            while (gameModel.timeLeft()){
                playCard();
            }
        }
        public void playCard(){
            gameModel.nextCard();
            //tryDirection()
        }

        private Direction decideDirection(float startX, float endX, float startY, float endY){
            float deltaX = endX - startX;
            float deltaY = endY - startY;
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
