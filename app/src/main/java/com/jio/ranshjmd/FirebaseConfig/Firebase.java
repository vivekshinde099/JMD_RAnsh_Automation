package com.jio.ranshjmd.FirebaseConfig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import com.jio.ranshjmd.R;

public class Firebase extends AppCompatActivity {

    TextView textView;
    String abc = "12.8.9";
    DatabaseReference myRef;
    Button button;
    Button getcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);


        textView = findViewById(R.id.textView);
        textView.setSelected(true);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        button = findViewById(R.id.likebutton);
        getcount = findViewById(R.id.getcount);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        // Read from the database

/*        Query query = myRef.child("Versions").orderByChild("JPP").equalTo(abc);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
             /*   FirebaseModel value = dataSnapshot.getValue(FirebaseModel.class);
                String versioname = value.getVersionname();
                Log.d(TAG, "Value is: " + versioname);
                textView.setText("JIOPOSPlus current version : " +versioname);*//*
                if(dataSnapshot.exists()) {
                    //create new user
                    textView.setText("version exist");
                }else{
                    textView.setText("null");
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
             //   Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

    }

    public void onlikeclicked(View view) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        Query query = myRef.child("Email Count").child("Count").child("SD Count");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                long numLikes = 0;
                if(snapshot.exists()){
                    numLikes = snapshot.getValue(Long.class);
                }
                boolean isLiked = button.isClickable();
                if(isLiked){
                    //If already liked then user wants to unlike the post
                    myRef.child("Email Count").child("Count").child("SD Count").setValue(numLikes+1);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void onclickgetcount(View view) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        Query query = myRef.child("Email Count").child("Count");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    long numOfLikes = 0;
                    if(snapshot.hasChild("SD Count")){
                        numOfLikes = snapshot.child("SD Count").getValue(Long.class);
                    }

                    textView.setText("BT"+numOfLikes);
                    //Populate numOfLikes on post i.e. textView.setText(""+numOfLikes)
                    //This is to check if the user has liked the post or not

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}