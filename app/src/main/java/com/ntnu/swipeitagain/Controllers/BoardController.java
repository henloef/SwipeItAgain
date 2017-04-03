package com.ntnu.swipeitagain.Controllers;
import android.content.res.Resources;
import android.util.Log;

import com.ntnu.swipeitagain.Models.Direction;
import com.ntnu.swipeitagain.Models.GameModel;
import com.ntnu.swipeitagain.States.GameState;
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
import sheep.math.Vector2;
import sheep.util.Timer;

import static android.content.ContentValues.TAG;

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
        private Resources resources;
        private Timer timer;
        private float counter;


        public BoardController(Game game, Resources resources, int screenWidth, int screenHeight){
            //pushState(new MainMenu());
            this.game = game;
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
            states = new ArrayList<State>();
            this.resources = resources;
            this.timer = new Timer();
        }

        public void pushState(State state){
            states.add(0,state);
            game.pushState(state);
            Log.d(TAG, "pushed state. length now: "+ states.size());
        }

        public State popState(){
            return states.remove(0);
        }

        public void createGameState(Boolean isMultiPlayer, Boolean generateKey) {
            createGameModel();
            if (isMultiPlayer){ gameState = new MultiPlayerState(this, generateKey);
                this.isMultiPlayer = isMultiPlayer;
                // Få fra input om man venter på motstander eller generer gamekey
            }
            else{ gameState = new SinglePlayerState(this);
                pushState(new SinglePlayerGameView(this, screenWidth, screenHeight, gameModel));// dette må endres, vi klarer jo ikke å kjøre en loop for å spille når denne pushes?
            }

        }

        public GameModel getGameModel(){
            return gameModel;
        }

        public void createGameModel(){
            gameModel = new GameModel();
        }

        public int getInputGameKey(){
            //Get input from screen
            return 0;
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

        //Called from joinGame
        public void tryEnteredGameKey(int gameKey){
            if(gameState instanceof MultiPlayerState){
                if(((MultiPlayerState) gameState).tryGameKey(gameKey)){
                    Log.d(TAG, "joining game");
                    pushState(new MultiPlayerGameView(this, screenWidth, screenHeight, gameModel));
                }else{
                    if(states.get(0) instanceof JoinGame){
                        ((JoinGame) states.get(0)).tryNewGameKey();
                    }
                }
            }else{
                Log.d(TAG, "Not multiplayer game");
            }
        }

        //START THE GAME called from
        public void startGame(){
            //createGameModel(); //assigns boardModel to this Boardcontroller
           // play();

        }

        public void updateGameState(){
            counter += timer.getDelta();//TODO fix game timer so that 0 stored is 0 displayed
            if(counter >=0.3){
                if(!gameModel.getPlayer().timeTick()){
                    pushState(new GameOver(this, screenWidth, screenHeight));
                }
                counter = 0.0f;
            }
        }

        //when gameOver option retry is chosen
        public void retry(){
        if(isMultiPlayer){

        }else{
            gameModel = new GameModel();
            pushState(new SinglePlayerGameView(this, screenWidth, screenHeight, gameModel));
        }
        }



        public void goToMainMenu(){
            //moves menu to the top of the stack
        game.pushState(new MainMenu(this, game, resources, screenWidth,screenHeight));
        Log.d(TAG, "States left: "+ states.size());
    }

        public boolean tryDirection(Direction direction){
            return gameModel.checkDirection(direction);
        }

        public void playCard(){
            //gameModel.nextCard();
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
