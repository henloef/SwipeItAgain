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
    public DatabaseReference myRef = database.getReference();

    //this will hold our collection of gamekeys
    final List<GameData> gameDatas = new ArrayList<GameData>(); //det går ann å endre en final array

    private int numberOfGames;

    public ServerCommunicator(){
        myRef.addChildEventListener(new GameDatasChildEventListener());
    }


    // Denne metoden skal hente all ned data fra server, men det går ikke.
    // new eventListener er her en anonym klasse så da må vi visst kun gi inn noe som er final, her final List gamekeys
    public void getAllGameDataFromServer() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference ref = database.getReference();

        ref.child("gameDatas").addValueEventListener(new ValueEventListener() { //myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //final long childrenCount = dataSnapshot.getChildrenCount();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                //Empties gameDatas before adding all datas from server
                gameDatas.clear();
                for (DataSnapshot child: children){
                    GameData data = child.getValue(GameData.class);
                    data.setChildrenCount(dataSnapshot.getChildrenCount());
                    Long temp = data.getChildrenCount();
                    data.setGameKey(Integer.valueOf(temp.intValue()));
                    gameDatas.add(data);
                }
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
        myRef.child(gameData.getKey()).removeValue();
    }

    public void add(GameData gameData){
        myRef.child("gameDatas").push().setValue(gameData);
    }

    public GameData newGameData(){
        myRef.child("gameDatas").push().setValue(newGameData());
        return gameDatas.get(gameDatas.size()-1);
    }

    public GameData getGameDataFromServer(){
       // Fredag

        //
        //Ny data legges til i server, så gir onChildAdded den nye data fraserver i arrayet.
        //getGameDataFromServer();//hente ned alle
        addNewGameDataToDatabase(new GameData()); // Uploads game data to server. Listener for new child added adds gameData to local array before actually uploading to server with near no latency
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
        //getAllGameDataFromServer();
        GameData temp = new GameData();
        addNewGameDataToDatabase(temp);
        myRef.addChildEventListener(new GameDatasChildEventListener());
        remove(gameDatas.get(0));
        for (GameData game : gameDatas) {
            if (gK== game.getGameKey()){
                return true;
            }
        }
        return  false;
    }

    public GameData GameDataFromKey(int key){
        for (GameData gameData:gameDatas) {
            if (key == gameData.gameKey){
                return gameData;
            }
        }
        return null;
    }

    public void opponentReady(GameData gameData){
        myRef.child("gameDatas").child(gameData.getKey()).setValue(gameData);
        Start(gameData, true);
    }
    public void updateGameData(GameData gameData){
        myRef.child(gameData.getKey()).setValue(gameData);
    }

    //Send message to other player that the game will start
    public void sendStartSignal(int gamekey){
        for (GameData gameData : gameDatas) {
            if (gameData.getGameKey() == gamekey) {
                Start(gameData, true);
            }
        }
    }

    public void Start(GameData gameData, boolean start){
        gameData.Start(true);
        myRef.child("gameDatas").child(gameData.getKey()).setValue(gameData);
    }

    class GameDatasChildEventListener implements ChildEventListener{

        @Override // Is called when creating new GameData
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
           // Either fill local storage with data or update local storage with latest data.
            Long temp1 = dataSnapshot.getChildrenCount();
            if (gameDatas.size()<=1){
               Iterable<DataSnapshot> children = dataSnapshot.getChildren();
               //Empties gameDatas before adding all datas from server
               gameDatas.clear();

               for (DataSnapshot child: children){
                   GameData data = child.getValue(GameData.class);
                   data.setChildrenCount(Integer.valueOf(temp1.intValue()));
                   data.setGameKey(Integer.valueOf(temp1.intValue()));
                   data.setKey(dataSnapshot.getKey());
                   gameDatas.add(0, data);
                   //upload gameKey
                   updateGameData(data);
               }
           } else {

               //Takes GameData from server
               GameData data = dataSnapshot.getValue(GameData.class);
               //Counting gamedatas
               data.setChildrenCount(dataSnapshot.getChildrenCount());
               Long temp = data.getChildrenCount();
               //Gamekey for joining games set to the new data count
               data.setGameKey(Integer.valueOf(temp.intValue()));
               //Database storage key saved locally for finding, and editing, and sending start signal
               data.setKey(dataSnapshot.getKey()); //TODO proper index not 0
               //add GameData from server to local array
               gameDatas.add(0, data);

           }
        }

        @Override // when we change a GameData this listener adds the changes in the local array
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            for (GameData data: gameDatas){
                if(data.getKey().equals(key)){
                    data.Start(true);
                }
            }
        }

        @Override
        // removing gameData from local array
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for (GameData gameData: gameDatas){
                if(gameData.getKey().equals(key)) {
                    gameDatas.remove(gameData);
                    break;
                }
            }
        }

        @Override //Not needed
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override //not Needed
        public void onCancelled(DatabaseError databaseError) {

        }
    }

}

