package com.ntnu.swipeitagain.Controllers;



import android.provider.Settings;

import android.util.Log;
import android.view.ViewDebug;

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


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    final String key ="";
    //this will hold our collection of gamekeys
    final List<GameData> gameDatas = new ArrayList<GameData>();

    // new eventListener er her en anonym klasse så da må vi visst kun gi inn noe som er final, her final List gamekeys
    public void getGameDataFromServer() {
        myRef.child("gameKeys").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children){
                    String value = child.getValue(String.class);
                    //  gameKeys.add(value);
                }
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

    public void addNewGameDataToDatabase(){
        Log.d(TAG, "Action; addNewGameKeyToDatabase()");
        myRef.child("gameDatas").push().setValue(new GameData(increment));
    }



    public int getGameKeyFromServer(){
        //TODO implement server connection
        return increment;
    }

    //try gameKey, if true return opponent ID?
    public int tryGameKey(int gameKey){
        //TODO
        return  0;
    }

    //Send message to other player that the game will start
    public void sendStartSignal(){
        //TODO implement
    }

    /*    ----------------------------------------------------------------------------


// Hvis socket.io skal brukes


import io.socket.client.IO;
import io.socket.client.Socket;


    public static final String SERVER_URL = "https://swipeitagain-4a391.firebaseio.com/";

    private Socket socket = null;

    public void connectSocket(){
        try {
            socket = IO.socket(SERVER_URL);
            socket.connect();
        }catch (Exception e){
            System.out.println(e);
        }
    }*/

}

