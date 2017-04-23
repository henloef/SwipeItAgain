package com.ntnu.swipeitagain.States;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;

/**
 * Created by Group 22 on 26.03.2017.
 */

public abstract class AbstractGameState {
    protected ServerCommunicator serverCommunicator;
    protected BoardController boardController;

   // protected GameData gameData;
   // final List<GameData> gameDatas = new ArrayList<GameData>();




    public AbstractGameState(BoardController boardController){
        this.boardController=boardController;
    }

}
