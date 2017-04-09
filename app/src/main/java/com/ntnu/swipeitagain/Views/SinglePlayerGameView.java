package com.ntnu.swipeitagain.Views;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.GameModel;


/**
 * Created by Henrik on 26.03.2017.
 */

public class SinglePlayerGameView extends GameView {

    public SinglePlayerGameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel){
        super(boardController, screenWidth, screenHeight, gameModel);
    }
}
