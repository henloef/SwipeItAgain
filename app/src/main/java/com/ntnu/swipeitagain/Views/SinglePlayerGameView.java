package com.ntnu.swipeitagain.Views;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Models.GameModel;


/**
 * Created by Group 22 on 26.03.2017.
 */

public class SinglePlayerGameView extends AbstractGameView {

    public SinglePlayerGameView(BoardController boardController, int screenWidth, int screenHeight, GameModel gameModel){
        super(boardController, screenWidth, screenHeight, gameModel);
    }
}
