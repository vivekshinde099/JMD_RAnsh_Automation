package com.jio.ranshjmd.Bottomnavigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jio.ranshjmd.Adapters.NotificationDataAdapter;
import com.jio.ranshjmd.ApiConstructor.StatusticketdataConstructor;
import com.jio.ranshjmd.ApiConstructor.TickerConstructor;
import com.jio.ranshjmd.Models.Notificationdata;
import com.jio.ranshjmd.PlaceHolderAPI.CreateTicket_PlaceHolderAPI;
import com.jio.ranshjmd.PlaceHolderAPI.Ticker_PlaceHolderAPI;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.RequestAPI.APIConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bellicon_activity extends AppCompatActivity {

    CardView cardView;
    TextView textView;
    int position = 0;

    List<Notificationdata> mynotidata;
    NotificationDataAdapter notificationDataAdapter;
    private DatabaseReference databaseReference1;
    String phonenumber;
    List<StatusticketdataConstructor> statusticketdataConstructors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bellicon);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_close_24);
        getSupportActionBar().setTitle("");

        cardView = (CardView)findViewById(R.id.cardlayout1);
        textView = (TextView)findViewById(R.id.text_ticker);
        statusticketdataConstructors=new ArrayList<StatusticketdataConstructor>();

        outageupdate();


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.notification_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mynotidata=new ArrayList<>();
        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        phonenumber = getshared.getString("strmob", "Mobile Number");

        CreateTicket_PlaceHolderAPI createTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
        Call<List<StatusticketdataConstructor>> call = createTicket_placeHolderAPI.getstatusdatabymob(phonenumber);
        call.enqueue(new Callback<List<StatusticketdataConstructor>>() {
            @Override
            public void onResponse(Call<List<StatusticketdataConstructor>> call, Response<List<StatusticketdataConstructor>> response) {
                List<StatusticketdataConstructor> posts = response.body();
                for (StatusticketdataConstructor post : posts) {
                    String status_id = post.getStatus_id();
                    if(status_id.equals("unread")){
                        String intid = post.getIntid();
                        String status = post.getStatus();
                        String id = post.getId();
                        statusticketdataConstructors.add(new StatusticketdataConstructor(id,intid,status,status_id));
                    }

                }
                Collections.reverse(statusticketdataConstructors);
                notificationDataAdapter = new NotificationDataAdapter(statusticketdataConstructors,Bellicon_activity.this);
                recyclerView.setAdapter(notificationDataAdapter);

            }


            @Override
            public void onFailure(Call<List<StatusticketdataConstructor>> call, Throwable t) {

            }
        });

//        databaseReference1 = FirebaseDatabase.getInstance().getReference("Status Data").child("+91"+phonenumber);
//        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dsp:snapshot.getChildren()){
//                    try {
//                        if (Datacontainer.Best5 > 0) {
//                            String data1 = dsp.child("btnumber").getValue().toString();
//                            String data2 = dsp.child("status").getValue().toString();
//                            String data3 = dsp.getKey();
//                            //   String data = dsp.getChildren().toString();
//
//                            mynotidata.add(new Notificationdata(data1,data2,data3));
//                        }
//                        else {
//                            String data1 = dsp.child("btnumber").getValue().toString();
//                            String data2 = dsp.child("status").getValue().toString();
//                            //     String data = dsp.getChildren().toString();
//                            String data3 = dsp.getKey();
//                            mynotidata.add(new Notificationdata(data1,data2,data3));
//
//                        }
//                    }catch (Exception e){
//                        Toast.makeText(Bellicon_activity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//
////                    mygetticketdata.add(data);
//                }
//                Collections.reverse(mynotidata);
//                notificationDataAdapter = new NotificationDataAdapter(mynotidata,Bellicon_activity.this);
//                recyclerView.setAdapter(notificationDataAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

//        notificationDataAdapter = new NotificationDataAdapter(mynotidata,Bellicon_activity.this);
//        recyclerView.setAdapter(notificationDataAdapter);

    }

    public void outageupdate(){

        Ticker_PlaceHolderAPI ticker_PlaceHolderAPI = APIConnection.getretrofitinstance().create(Ticker_PlaceHolderAPI.class);
        Call<List<TickerConstructor>> call = ticker_PlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<TickerConstructor>>() {
            @Override
            public void onResponse(Call<List<TickerConstructor>> call, Response<List<TickerConstructor>> response) {

                List<TickerConstructor> posts = response.body();
                for (TickerConstructor post : posts) {

                    String content = post.getOutage();
                    textView.setText(content);
                    Log.d("Data:", content);
                }
            }
            @Override
            public void onFailure(Call<List<TickerConstructor>> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }



}