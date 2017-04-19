package com.ntnu.swipeitagain.Controllers;

import android.provider.Settings;

import android.telecom.InCallService;
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

import okhttp3.Callback;

import static android.content.ContentValues.TAG;

/**
 * Created by Henrik on 26.03.2017.
 */

public class ServerCommunicator {

    private Callback mCallback;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    //this will hold our collection of gamekeys
    final List<GameData> gameDatas = new ArrayList<GameData>(); //det går ann å endre en final array
    private int numberOfGames;

    public ServerCommunicator(){
        myRef.addChildEventListener(new GameDatasChildEventListener());
    }



    // new eventListener er her en anonym klasse så da må vi visst kun gi inn noe som er final, her final List gamekeys
    public void getAllGameDataFromServer() {
        myRef.child("gameDatas").addValueEventListener(new ValueEventListener() { //myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final long childrenCount = dataSnapshot.getChildrenCount();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                gameDatas.clear();

                for (DataSnapshot child: children){
                    GameData data = child.getValue(GameData.class);
                    data.setChildrenCount(childrenCount);
                      gameDatas.add(data);
                }

                Log.d(TAG, "Gamedata ser slik ut" + gameDatas);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



    private int getLastGameDataKey(){
        return  gameDatas.get(gameDatas.size()-1).gameKey;
    }


    public List<GameData> getGameDatas(){
        //getGameKeyFromServer();
        return gameDatas;
    }

    public void addNewGameDataToDatabase(GameData gameData){
        Log.d(TAG, "Action; addNewGameKeyToDatabase()");
        myRef.child("gameDatas").push().setValue(gameData);
        Log.d(TAG, "Gamedata size" + gameDatas.size());
    }

    public void remove(GameData gameData){

       //TODO sletting av data  myRef.child(gameData.getKey)
        myRef.child("gameDatas").child(gameData.getKey()).removeValue();
    }

    public void add(GameData gameData){
        myRef.child("gameDatas").push().setValue(gameData);
    }

    public GameData newGameData(){
        myRef.child("gameDatas").push().setValue(newGameData());
        return gameDatas.get(gameDatas.size()-1);
    }

    public GameData getGameKeyFromServer(){
        //addNewGameDataToDatabase(new GameData());
        //Ny data legges til i server, så gir onChildAdded den nye data fraserver i arrayet.
        //getGameDataFromServer();//hente ned alle
        addNewGameDataToDatabase(new GameData()); // last opp data slik at eventlisteners i getGameData slår inn
        GameData newGame = gameDatas.get(0);
        return newGame;// newGame.getGameKey();
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

    }

    public void Start(GameData gameData, boolean start){
        gameData.Start(true);
        myRef.child("gameDatas").child(gameData.getKey()).setValue(gameData);
    }

    class GameDatasChildEventListener implements ChildEventListener{

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            GameData data = dataSnapshot.getValue(GameData.class);
            data.setChildrenCount(dataSnapshot.getChildrenCount());
            Long temp = data.getChildrenCount();
            data.setGameKey(Integer.valueOf(temp.intValue()));
            data.setKey(dataSnapshot.getKey()); //TODO proper index not 0
            gameDatas.add(0, data);
            //notifyDatasetChanged();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            for (GameData data: gameDatas){
                if(data.getKey().equals(key)){
                    data.Start(true);
                }
            }
        }

        @Override
        // removing gameData from backend
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for (GameData gameData: gameDatas){
                if(gameData.getKey().equals(key)) {
                    gameDatas.remove(gameData);
                    break;
                }
            } //notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }

}

