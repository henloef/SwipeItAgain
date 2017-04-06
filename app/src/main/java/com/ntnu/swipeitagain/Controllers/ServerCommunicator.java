package com.ntnu.swipeitagain.Controllers;


//import io.socket.client.IO;


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

import io.socket.client.IO;
import io.socket.client.Socket;

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


    public void addToDatabase(String child, String id, String val){
        Log.d(TAG, "Action; addToDatabase()");
        myRef = database.getReference(child);

        myRef.setValue(val);
        //mDatabase.child(child).child(id).setValue(val);
        // Read from the database
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
    public void addNewGameKeyToDatabase(){
        increment ++;
        Log.d(TAG, "Action; addNewGameKeyToDatabase()");
        myRef.child("gameDatas").push().setValue(new GameData(increment));
        System.out.println("halla");

    }

    public void listnerMethod(){
        myRef.child("gameDatas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(zzt.TAG, "datachange");
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child: children){
                    GameData value = child.getValue(GameData.class);
                    gameDatas.add(value);
                }
                Log.d(zzt.TAG, "gameDatas test: " + gameDatas);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        myRef.child("gameDatas").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(zzt.TAG, "hmm" + dataSnapshot.getValue(GameData.class).toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })
        ;
    }

    public int getGameKeyFromServer(){
        //TODO implement server connection
        increment ++;
        return increment;
    }

    //try gameKey, if true return opponent ID?
    public int tryGameKey(int gameKey){
        //TODO
        System.out.println("Her prøver vi: ");
        Query query = myRef.child("gameKeys").equalTo("" + gameKey);
        query.addValueEventListener(valueEventListener);
        return  0;
    }
    //Query query = mFirebaseDatabaseReference.child("userTasks").orderByChild("title").equalTo("#Yahoo");
    ValueEventListener valueEventListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
            {
                //TODO get the data
                String value = (String) dataSnapshot.getValue();
                System.out.println("value from db:  " + value);
                Log.d(TAG, "Tries to get key from db: " + value);
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    };








    //Send message to other player that the game will start
    public void sendStartSignal(){
        //TODO implement
    }

   /*

   --------------------------------------------------------------
   FOR SOCKET.IO

    public static final String SERVER_URL = "https://swipeitagain-4a391.firebaseio.com/";

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

    */

}
