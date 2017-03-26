package com.ntnu.swipeitagain.States;

import com.ntnu.swipeitagain.Controllers.BoardController;

/**
 * Created by Henrik on 26.03.2017.
 */

public class SinglePlayerState extends GameState{


    public SinglePlayerState(BoardController boardController){
        super(boardController);
        startGame();
    }

    //Gets gamemodel from boardController


    @Override
    public void startGame() {
        boardController.startGame();
    }
}
