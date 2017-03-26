package com.ntnu.swipeitagain.States;

import com.ntnu.swipeitagain.Controllers.BoardController;

/**
 * Created by Henrik on 26.03.2017.
 */

public abstract class GameState {
    protected BoardController boardController;


    //OrthographicCamera cam = new OrthographicCamera();

    public GameState(BoardController boardController){
        this.boardController=boardController;
    }


    public abstract void startGame();
}
