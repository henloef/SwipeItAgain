package com.ntnu.swipeitagain.Controllers;

import android.provider.Settings;

import android.util.Log;
import android.view.ViewDebug;
import android.view.animation.Interpolator;

import com.google.android.gms.internal.zzt;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ntnu.swipeitagain.Models.GameData;
import com.ntnu.swipeitagain.Models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public class ServerCommunicator {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private DatabaseReference gameListRef = myRef.child("gameDatas");

    //this will hold our collection of gamekeys
    final List<GameData> gameDatas = new ArrayList<GameData>(); //det går ann å endre en final array
    private static int numberOfGames =0;
    PlayerModel player;
    GameData gd;

    public ServerCommunicator(PlayerModel player){
        this.player = player;
    }

    // new eventListener er her en anonym klasse så da må vi visst kun gi inn noe som er final, her final List gamekeys
    public void getGameDataFromServer() {
        gameListRef.addValueEventListener(new ValueEventListener() { //myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                gameDatas.clear();

                for (DataSnapshot child: children){
                    GameData value = child.getValue(GameData.class);
                      gameDatas.add(value);
                }
                numberOfGames = gameDatas.size();
                Log.d(TAG, "Gamedata ser slik ut" + gameDatas);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public GameData getGameDataFromServerWithKey(final int gameKey) {
        gameListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children){
                    GameData value = child.getValue(GameData.class);
                    if (value.gameKey == gameKey){
                        gd = value;
                    }else{
                        Log.d(TAG, "Error message created in ServerComunicator -> getGameDataFromServerWithKey()");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return gd;
    }

    public List<GameData> getGameDatas(){
        getGameKeyFromServer();
        return gameDatas;
    }

    public void addNewGameDataToDatabase(GameData gameData){
        Log.d(TAG, "Action; addNewGameKeyToDatabase()");
        gameListRef.push().setValue(gameData);
        Log.d(TAG, "Gamedata size" + gameDatas.size());
    }

    public int getGameKeyFromServer(){
        GameData newGame = new GameData(numberOfGames+1, player);
        while(!doesKeyExistForNewGameData(newGame)){
            newGame.gameKey++;
        }
        addNewGameDataToDatabase(newGame);
        return newGame.gameKey;
    }
    public boolean doesKeyExistForNewGameData(GameData game){
        for (GameData gd: gameDatas){
            if (game.gameKey == gd.gameKey){
                return false;
            }
        }
        return true;
    }

    //try gameKey from opponent, if a game with that key exists in the database it will return true
    public boolean tryGameKey(int gK){
        for (GameData game:gameDatas) {
            if (gK== game.gameKey){
                return true;
            }
        }
        return  false;
    }

    //Send message to other player that the game will start
    public void sendStartSignal(int gamekey){
//        gameListRef.runTransaction();

    }


}

