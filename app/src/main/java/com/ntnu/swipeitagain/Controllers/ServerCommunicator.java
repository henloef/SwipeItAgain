package com.ntnu.swipeitagain.Controllers;


//import io.socket.client.IO;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Henrik on 26.03.2017.
 */


public class ServerCommunicator {

    // Write a message to the database



    //myRef.setValue("Hello, World!");

    public static final String SERVER_URL = "https://swipeitagain-4a391.firebaseio.com";
    private Socket socket = null;
    private String id;

    public void connectSocket(){
        try {
            socket = IO.socket(SERVER_URL);
            socket.connect();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int getGameKeyFromServer(){
        //TODO implement server connection
        return 0;
    }

    //try gameKey, if true return opponent ID?
    public int tryGameKey(int gameKey){
        //TODO

        return 0;
    }

    //Send message to other player that the game will start
    public void sendStartSignal(){
        //TODO implement
    }
}
