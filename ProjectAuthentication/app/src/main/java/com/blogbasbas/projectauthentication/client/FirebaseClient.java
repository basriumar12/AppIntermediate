package com.blogbasbas.projectauthentication.client;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.blogbasbas.projectauthentication.adapter.CustomAdapter;
import com.blogbasbas.projectauthentication.model.Hewan;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by Blackswan on 7/19/2017.
 */

public class FirebaseClient {
    Firebase firebase;
    ArrayList<Hewan> hewanArrayList = new ArrayList<>();
    CustomAdapter customAdapter;
    Context c;
    String DB_URL;
    ListView listView;

    public FirebaseClient(Context c, String DB_URL, ListView listView) {
        this.c = c;
        this.DB_URL = DB_URL;
        this.listView = listView;
        Firebase.setAndroidContext(c);
        firebase = new Firebase(DB_URL);
    }

    public void savedata(String name,String info, String url) {
        Hewan h = new Hewan();
        h.setName(name);
        h.setInfo(info);
        h.setUrl(url);
        firebase.child("hewan").push().setValue(h);
    }


    public void refreshdata() {
        firebase.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);

            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        } );
    }

    private void getUpdates(com.firebase.client.DataSnapshot dataSnapshot) {
        hewanArrayList.clear();
        for (com.firebase.client.DataSnapshot ds : dataSnapshot.getChildren()) {
            Hewan h = new Hewan();

            h.setName(ds.getValue(Hewan.class).getName());
            h.setInfo(ds.getValue(Hewan.class).getInfo());
            h.setUrl(ds.getValue(Hewan.class).getUrl());
            hewanArrayList.add(h);

        }
        if (hewanArrayList.size() > 0) {
            customAdapter = new CustomAdapter(c, hewanArrayList);
            listView.setAdapter((ListAdapter) customAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //     dataSnapshot.getRef().child(String.valueOf(position)).removeValue();
                    //  dataSnapshot.getRef().setValue(null);
                    Toast.makeText(c, "test aja", Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(c, "no data", Toast.LENGTH_SHORT).show();
        }

    }

}