package com.ntnu.swipeitagain.States;

import com.ntnu.swipeitagain.Controllers.BoardController;
import com.ntnu.swipeitagain.Controllers.ServerCommunicator;
import com.ntnu.swipeitagain.Models.GameData;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Group 22 on 26.03.2017.
 */

public abstract class GameState {
    protected ServerCommunicator serverCommunicator;
    protected BoardController boardController;

   // protected GameData gameData;
   // final List<GameData> gameDatas = new ArrayList<GameData>();




    public GameState(BoardController boardController){
        this.boardController=boardController;
    }

}
