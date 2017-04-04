package com.ntnu.swipeitagain.Controllers;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;


/**
 * Created by Henrik on 26.03.2017.
 */


public class ServerCommunicator {

    private String id;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    public void addToDatabase(String child, String id, String val){
        Log.d(TAG, "Action; addToDatabase()");
        DatabaseReference myRef = database.getReference(child);
//        mDatabase = database.getReference();

        myRef.setValue(id);
//        myRef.child(id).setValue(val);
//        mDatabase.child(child).child(id).setValue(val);
//         Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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
    }/*


}
