package com.ntnu.swipeitagain.Controllers;

import android.app.Activity;
import android.content.res.Resources;

import com.ntnu.swipeitagain.Models.Direction;
import com.ntnu.swipeitagain.Models.GameModel;
import com.ntnu.swipeitagain.States.GameState;
import com.ntnu.swipeitagain.States.MultiPlayerState;
import com.ntnu.swipeitagain.States.SinglePlayerState;
import com.ntnu.swipeitagain.Views.GameOver;
import com.ntnu.swipeitagain.Views.SinglePlayerGameView;
import com.ntnu.swipeitagain.Views.Main;
import com.ntnu.swipeitagain.Views.MainMenu;
import com.ntnu.swipeitagain.Views.SinglePlayerGameView;

import java.util.ArrayList;

import sheep.game.Game;
import sheep.game.State;
import sheep.math.Vector2;

/**
 * Created by Henrik on 26.03.2017.
 */

public class BoardController {
        private GameModel gameModel;
        private GameState gameState;
        private boolean isMultiPlayer;
        private ArrayList<State> states;
        private Game game;
        private int screenWidth, screenHeight;


        public BoardController(Game game, int screenWidth, int screenHeight){
            //pushState(new MainMenu());
            this.game = game;
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
            states = new ArrayList<State>();
        }

        public void pushState(State state){
            states.add(0,state);
            game.pushState(state);
        }

        public State popState(){
            return states.remove(0);
        }

        public void createGameState(Boolean isMultiPlayer, Boolean generateKey) {
            if (isMultiPlayer){ gameState = new MultiPlayerState(this, generateKey);
                this.isMultiPlayer = isMultiPlayer;
                // Få fra input om man venter på motstander eller generer gamekey
            }
            else{ gameState = new SinglePlayerState(this);
                pushState(new SinglePlayerGameView(this, screenWidth, screenHeight));
            }
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
            pushState(new GameOver(this, game, game.getResources(), screenWidth, screenHeight));
        }
        public void playCard(){
            gameModel.nextCard();
            //tryDirection()
        }

        private Direction decideDirection(Vector2 start, Vector2 end){
            float deltaX = end.getX() - start.getX();
            float deltaY = end.getY() - start.getY();
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
