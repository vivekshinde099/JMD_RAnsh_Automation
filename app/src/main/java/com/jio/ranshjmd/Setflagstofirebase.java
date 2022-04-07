package com.jio.ranshjmd;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.SMTP.SendMail;

import static java.lang.String.join;

public class Setflagstofirebase extends AppCompatActivity {

    String finaldata = "";
    List<String> mydata;
    List<String> finallist;
    int count = 0;
    SimpleDateFormat sdf3;
    Date d1 = null;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setflagstofirebase);
//        startService(new Intent(getApplicationContext(), Myservice.class));
        reload();
        mydata = new ArrayList<String>();
        finallist = new ArrayList<String>();
        sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("Flagging");
        ValueEventListener eventListener = new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.getKey();

                    date = (String) ds.child("dateandTime").getValue();
                    name = String.valueOf(ds.child("issueTitle").getValue());

                 //   Log.d("Data", String.valueOf(mydata));
                    try{

                        d1 = sdf3.parse(date);

                        if (d1.after(min5min()) && d1.before(getDate())){
                            mydata.add(name);
//                            Log.d("Data", String.valueOf(mydata));
                        }
                    }
                    catch (Exception e){ Log.d("error",e.getMessage()); }


                }
                for(int i = 0; i < mydata.size(); i++) {
                    for (int j=0;j<mydata.size();j++) {
                        if (finaldata.contains(mydata.get(i))){

                        }else {
                            if (mydata.get(i).equals(mydata.get(j))) {
                                count++;

                            }else {
//                            Log.d("Data",mydata.get(j)+" "+count);
//                            count = 0;
//                            break;
                            }
                        }

                    }
                    finaldata += "," +mydata.get(i);
                    if (count != 0){
                        Log.d("Data",mydata.get(i)+" "+count);
                        if (count >= 2) {
                            SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
                            String nuserid = getshared.getString("struserid", null);
                            String npass = getshared.getString("strpass", null);
                            SendMail sender = new SendMail(nuserid, npass);
                            try {
                                sender.sendMail("Severity :"+mydata.get(i), "Hi Team,\nFlag has been raised for below issue : \n" +mydata.get(i)+"\n\n"+"Total count of tickets in 5 min : "+count,
                                        "PartnerITbot@outlook.com",
                                        "akshaykini133@gmail.com,v.v.shinde099@gmail.com,kalpankur.pandey@ril.com,rajiv2.jain@ril.com,prathamesh.palkar@ril.com,love.nigham@ril.com,vishwanath1.sawant@ril.com,nikhil6.sharma@ril.com", Datacontainer.popupWindow1,"Severity","",mydata.get(i)+" "+count,Setflagstofirebase.this,"");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                       //     Toast.makeText(Setflagstofirebase.this, mydata.get(i)+" "+count, Toast.LENGTH_SHORT).show();
//
                        }
                    }
                    count=0;
                }
//                Log.d("Data",String.valueOf(count));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
            }
        };
        itemsRef.addListenerForSingleValueEvent(eventListener);




    }

    public Date getDate(){
        Date currentDate = new Date();
        Date futureDate = new Date(currentDate.getTime());
        Log.d("VALUE", String.valueOf(futureDate));
        return futureDate;
    }
    public Date min5min(){
        long minutesToAdd=5;
        Date currentDate = new Date();
        Date futureDate = new Date(currentDate.getTime() - minutesToAdd*60000);
        return futureDate;
    }
//    @Override
//    protected void onDestroy() {
//
//        Intent broadcastIntent = new Intent();
//        broadcastIntent.setAction("restartservice");
//        broadcastIntent.setClass(this, Restarter.class);
//        this.sendBroadcast(broadcastIntent);
//        super.onDestroy();
//    }

    private void reload(){


        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressWarnings("unchecked")
                    public void run() {
                        try {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doTask,60000,60000);

    }


}