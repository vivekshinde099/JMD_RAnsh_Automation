package com.jio.ranshjmd.Common;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Myservice extends Service {

    String finaldata = "";
    List<String> mydata;
    List<String> finallist;
    int count = 0;
    SimpleDateFormat sdf3;
    Date d1 = null;
    String date;
    Handler handler = new Handler();
    public Myservice() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onTaskRemoved(intent);

//                Toast.makeText(getApplicationContext(),"This is a Service running in Background",
//                        Toast.LENGTH_SHORT).show();
//                mydata = new ArrayList<String>();
//                finallist = new ArrayList<String>();
//                sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//
//                DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference().child("Flagging");
//                ValueEventListener eventListener = new ValueEventListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                            String name = ds.getKey();
//
//                            date = (String) ds.child("dateandTime").getValue();
//                            name = String.valueOf(ds.child("issueTitle").getValue());
//
//                            //   Log.d("Data", String.valueOf(mydata));
//                            try{
//
//                                d1 = sdf3.parse(date);
//
//                                if (d1.after(min5min()) && d1.before(getDate())){
//                                    mydata.add(name);
////                            Log.d("Data", String.valueOf(mydata));
//                                }
//                            }
//                            catch (Exception e){ Log.d("error",e.getMessage()); }
//
//
//                        }
//                        for(int i = 0; i < mydata.size(); i++) {
//                            for (int j=0;j<mydata.size();j++) {
//                                if (finaldata.contains(mydata.get(i))){
//
//                                }else {
//                                    if (mydata.get(i).equals(mydata.get(j))) {
//                                        count++;
//
//                                    }else {
////                            Log.d("Data",mydata.get(j)+" "+count);
////                            count = 0;
////                            break;
//                                    }
//                                }
//
//                            }
//                            finaldata += "," +mydata.get(i);
//                            if (count != 0){
//                                Log.d("Data",mydata.get(i)+" "+count);
//                                if (count >= 2) {
//                                    SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
//                                    String nuserid = getshared.getString("struserid", null);
//                                    String npass = getshared.getString("strpass", null);
//                                    SendMail sender = new SendMail(nuserid, npass);
//                                    try {
//                                        sender.sendMail("Severity :"+mydata.get(i), "Hi Team,\nFlag has been raised for below issue :\n" +mydata.get(i)+"\n\n"+"Total count of tickets in 5 min : "+count,
//                                                "PartnerITbot@outlook.com",
//                                                "akshaykini133@gmail.com", Datacontainer.popupWindow1,"Severity","",mydata.get(i)+" "+count, null);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                    Toast.makeText(getApplicationContext(), mydata.get(i)+" "+count, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            count=0;
//                        }
////                Log.d("Data",String.valueOf(count));
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
//                    }
//                };
//                itemsRef.addListenerForSingleValueEvent(eventListener);



        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        super.onTaskRemoved(rootIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
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

}
