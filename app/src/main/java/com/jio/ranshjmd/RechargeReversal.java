package com.jio.ranshjmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jio.ranshjmd.Common.Datacontainer;

public class RechargeReversal extends AppCompatActivity {

    String[] listItems;
    Button button, button1;
    RadioGroup Group1,Group2,Group3,Group4;
    TextView TextView1, TextView2, TextView3, TextView4, TextView5, TextView6, TextView7;
    TextView textview1,textview2,textview3,textview4,textview5,textview6,textview7,textview8;
    RadioButton Yes1,No1,Yes2,No2,Yes3,No3,Yes4,No4;
    Handler handler;
    Runnable r;
    RelativeLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_reversal);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Recharge Reversal Process");
        linearLayout = (RelativeLayout) findViewById(R.id.maincontener);

//        handler = new Handler();
//        r = new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RechargeReversal.this);
//                alertDialogBuilder.setCancelable(false);
//                alertDialogBuilder.setMessage(Html.fromHtml("Session Expired <br> Idle Timeout Error!!"))
//                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finishAffinity();
//                            }
//                        });
//
//                AlertDialog alert = alertDialogBuilder.create();
//                if(!isFinishing()) {
//                    alert.show();
//                }
//            }
//        };
//        startHandler();


        button = (Button) findViewById(R.id.process);
//        Datacontainer.model(button,null,RechargeReversal.this,"");
        Group1 =(RadioGroup) findViewById(R.id.group1);
        Group2 =(RadioGroup) findViewById(R.id.group2);
        Group3 =(RadioGroup) findViewById(R.id.group3);
        Group4 =(RadioGroup) findViewById(R.id.group4);
        button1 = (Button) findViewById(R.id.TextView1);
        TextView2 = (TextView) findViewById(R.id.TextView2);
        TextView3 = (TextView) findViewById(R.id.TextView3);
        TextView4 = (TextView) findViewById(R.id.TextView4);
        TextView5 = (TextView) findViewById(R.id.TextView5);
        TextView6 = (TextView) findViewById(R.id.TextView6);
        TextView7 = (TextView) findViewById(R.id.TextView7);
        // ---------------------------- Linear layout 2 ---------------------------------
        textview1 =(TextView)findViewById(R.id.fragment_text1);
        textview2 =(TextView)findViewById(R.id.fragment_text2);
        textview3 =(TextView)findViewById(R.id.fragment_text3);
        textview4 =(TextView)findViewById(R.id.fragment_text4);
        textview5 =(TextView)findViewById(R.id.fragment_text5);
        textview6 =(TextView)findViewById(R.id.fragment_text6);
        textview7 =(TextView)findViewById(R.id.fragment_text7);
        textview8 =(TextView)findViewById(R.id.fragment_text8);

        textid1();
        Group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.Yes1){
                    TextView4.setText("2. Is the recharge amount more than Rupees 100 ?");
                    TextView4.setVisibility(View.VISIBLE);
                    Group2.setVisibility(View.VISIBLE);
                    Datacontainer.model(null,TextView4,RechargeReversal.this,"");
                    Group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if (checkedId == R.id.Yes2 ){
                                TextView5.setText("3. Is this First recharge or Add_On or Top_Up or ISD_Pack ?");
                                TextView5.setVisibility(View.VISIBLE);
                                Group3.setVisibility(View.VISIBLE);
                                Datacontainer.model(null,TextView5,RechargeReversal.this,"");
                                Group3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        if(checkedId == R.id.No3){
                                            TextView6.setText("4. Was the recharge done 4 hours earlier ?");
                                            TextView6.setVisibility(View.VISIBLE);
                                            Group4.setVisibility(View.VISIBLE);
                                            Datacontainer.model(null,TextView6,RechargeReversal.this,"");
                                            Group4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                    if(checkedId == R.id.No4){
                                                        TextView7.setText("Click 'Proceed' to check the Recharge Reversal process");
                                                        TextView7.setVisibility(View.VISIBLE);
                                                        Group4.setVisibility(View.VISIBLE);
                                                        button.setVisibility(View.VISIBLE);
                                                        Datacontainer.model(null,TextView7,RechargeReversal.this,"");
                                                        button.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {

                                                                LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.linearmain1);
                                                                linearLayout1.setVisibility(View.GONE);
                                                                LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.linearmain2);
                                                                linearLayout2.setVisibility(View.VISIBLE);
                                                                LinearLayout2();
                                                            }
                                                        });


                                                    }else  if(checkedId == R.id.Yes4){
                                                        Toast.makeText(getApplicationContext(),"We can only do recharge revesral process if its initiated within 4 hours",Toast.LENGTH_LONG).show();

                                                    }
                                                }
                                            });
                                        }else if(checkedId == R.id.Yes3){
                                            Toast.makeText(getApplicationContext(),"We can not reverse the recharge for FRC/Add On/TopUp/ISD pack",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            }else if(checkedId == R.id.No2){
                                Toast.makeText(getApplicationContext(),"We can only reverse the recharge if amount is greater than Rs.100", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else if (checkedId == R.id.No1){
                    Toast.makeText(getApplicationContext(),"We can do recharge reversal process only once time in a month",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

    }
    public void textid1()
    {
    //    Datacontainer.model(button1,null,RechargeReversal.this,"");
     //   Datacontainer.model(null,TextView1,RechargeReversal.this,"");
        Datacontainer.model(null,TextView2,RechargeReversal.this,"");
        Datacontainer.model(null,TextView3,RechargeReversal.this,"");
    }
    public void LinearLayout2()
    {
        Datacontainer.model(null,textview1,RechargeReversal.this,"");
        Datacontainer.model(null,textview2,RechargeReversal.this,"");
        Datacontainer.model(null,textview3,RechargeReversal.this,"");
        Datacontainer.model(null,textview4,RechargeReversal.this,"");
        Datacontainer.model(null,textview5,RechargeReversal.this,"");
        Datacontainer.model(null,textview6,RechargeReversal.this,"");
        Datacontainer.model(null,textview7,RechargeReversal.this,"");
        Datacontainer.model(null,textview8,RechargeReversal.this,"");
    }
//
//    @Override
//    public void onUserInteraction() {
//        // TODO Auto-generated method stub
//        super.onUserInteraction();
//        stopHandler();//stop first and then start
//        startHandler();
//    }
//    public void stopHandler() {
//        handler.removeCallbacks(r);
//    }
//    public void startHandler() {
//        handler.postDelayed(r, 10*60*1000); //for 2 minutes
//    }


//    public void finish(View view) {
//        onButtonShowPopupWindowClick();
//    }
//    public void onButtonShowPopupWindowClick(){
//
//
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.fragemnt_doesissueresolved, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.MATCH_PARENT;
//        int height = LinearLayout.LayoutParams.MATCH_PARENT;
//        boolean focusable = false; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
//
//        View v =popupWindow.getContentView();
////        ImageView imageView = v.findViewById(R.id.close_popup);
////        imageView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                popupWindow.dismiss();
////                linearLayout.setAlpha(1f);
////            }
////        });
//
//        linearLayout.setAlpha(0.1f);
//
//        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
//
//        Button yesbtn = v.findViewById(R.id.issue_resolved);
//        yesbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Datacontainer.pleasewait_popup(linearLayout,RechargeReversal.this);
//
//                String nprm = getshared.getString("strprm", "PRM");
//                String nmob = getshared.getString("strmob", "Mobile Number");
//                String nemail = getshared.getString("stremail", "Email ID");
//                String nname = getshared.getString("strname", "Name");
//
//                Thread sender = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//
//                            SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
//                            String nuserid = getshared.getString("struserid", null);
//                            String npass = getshared.getString("strpass", null);
//                            SendMail sender = new SendMail(nuserid, npass);
//                            sender.sendMail("#botapplication", "Hi Team,\nIssue Name: Recharge Reversal\\nPRM : "+nprm+ "\nMob :"+nmob+"\nEmail :"+nemail+"\nName :"+nname+"\nIssue has been Resolved using Bot Application.\n\nThank You.\n\nMail has been sent from PartnerIT Bot Application",
//                                    "PartnerITbot@outlook.com",
//                                    "partnerit@jio.com",Datacontainer.popupWindow1,"resolved","","Recharge Reversal",RechargeReversal.this);
//                        } catch (Exception e) {
//                            Log.e("mylog", "Error: " + e.getMessage());
//                        }
//                    }
//                });
//                sender.start();
//
//            }
//        });
//        Button nobtn = v.findViewById(R.id.issue_notresolved);
//        nobtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    DatabaseReference myRef;
//                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                    myRef = firebaseDatabase.getReference();
//                    Query query = myRef.child("Email Count").child("Count").child("SD Count");
//                    query.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull @com.google.firebase.database.annotations.NotNull DataSnapshot snapshot) {
//                            long numLikes = 0;
//                            if(snapshot.exists()){
//                                numLikes = snapshot.getValue(Long.class);
//                            }
//                            myRef.child("Email Count").child("Count").child("SD Count").setValue(numLikes+1);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull @com.google.firebase.database.annotations.NotNull DatabaseError error) {
//
//                        }
//                    });
//
//                }catch (Exception e){
//                    Log.e("mylog", "Error: " + e.getMessage());
//                }
//
//                popupWindow.dismiss();
//                Datacontainer.pleasewait_popup(linearLayout,RechargeReversal.this);
//
//
//                String BTTICKETID = finalticketid.getText().toString();
//
//                String nprm = getshared.getString("strprm", "PRM");
//                String nmob = getshared.getString("strmob", "Mobile Number");
//                String nemail = getshared.getString("stremail", "Email ID");
//                String nname = getshared.getString("strname", "Name");
//                Thread sender = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        try {
//                            SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
//                            String nuserid = getshared.getString("struserid", null);
//                            String npass = getshared.getString("strpass", null);
//                            SendMail sender = new SendMail("PartnerITbot@outlook.com", "Partner@123");
//                            sender.sendMail(BTTICKETID+ " " + "Recharge Reversal" + "_BOT", "Hi Team,\n"
//                                            + "\nPRM : "+nprm+ "\nMob :"+nmob+"\nEmail :"+nemail+"\nName :"+nname+"\nMail has been sent from PartnerIT Bot Application",
//
//                                    "PartnerITbot@outlook.com",
//                                    "partnerit@jio.com", String.valueOf(maxid),Issuetitle,BTTICKETID,phonenumber,RechargeReversal.this,Datacontainer.popupWindow1);
//                        }catch (Exception e1){
//                            Toast.makeText(RechargeReversal.this, e1.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                sender.start();
//
//            }
//        });
//    }
}
