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

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public class ServerCommunicator {
    //One initial gameKey to increment
    private int increment = 1;
    private final int numberOfGames = 0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    final String key ="";
    //this will hold our collection of gamekeys
    final List<GameData> gameDatas = new ArrayList<GameData>(); //det går ann å endre en final array

    // new eventListener er her en anonym klasse så da må vi visst kun gi inn noe som er final, her final List gamekeys
    public void getGameDataFromServer() {
        myRef.child("gameDatas").addValueEventListener(new ValueEventListener() { //myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children){
                    GameData value = child.getValue(GameData.class);
                      gameDatas.add(value);
                }
                Log.d(TAG, "Gamedata ser slik ut" + gameDatas);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public List<GameData> getGameDatas(){
        getGameKeyFromServer();
        return gameDatas;
    }



    public void addNewGameDataToDatabase(GameData gameData){
        Log.d(TAG, "Action; addNewGameKeyToDatabase()");
        myRef.child("gameDatas").push().setValue(gameData);
        Log.d(TAG, "Gamedata size" + gameDatas.size());
    }
    public GameData newGameData(){
        myRef.child("gameDatas").push().setValue(newGameData());
        return gameDatas.get(gameDatas.size()-1);
    }

    public int getGameKeyFromServer(){
      return gameDatas.size()+1;
    }

    //try gameKey, if true return true?
    public boolean tryGameKey(int gameKey){
        for (GameData game:gameDatas) {
            if (gameKey== game.gameKey){
                return true;
            }
        }
        return  false;
    }

    //Send message to other player that the game will start
    public void sendStartSignal(){
        //TODO implement
    }


}

