package com.jio.ranshjmd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.jio.ranshjmd.RequestAPI.OTP_PostData;
import com.jio.ranshjmd.RequestAPI.UserProfileDataPostData;

public class Startup_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String mprm, mmob, memail, mname,content,getuid;
    Button msubbtn;
    SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    DatabaseReference masterdatasearchid;
    EditText mobilenumber;
    String phonenumber,phonenumberuserdata;


    TextView text1,text2,text3,text4,resendotp,timer;

    String[] Mainzone = {"East","West","North","South"};
    String[] East = {"(AS)-Assam","(NE)-North East","(BR)-Bihar & Jharkhand","(KO)-Kolkata","(OR)-Odisha","(WB)-West Bengal"};
    String[] West = {"(GJ)-Gujarat","(MP)-Madhya Pradesh & Chhattisgarh","(MU)-Mumbai","(MH)-Maharashtra & Goa",};
    String[] North = {"(DL)-Delhi","(HR)-Haryana","(HP)-Himachal Pradesh","(JK)-Jammu and Kashmir","(PB)-Punjab","(RJ)-Rajasthan","(UE)-UP (East)","(UW)-UP (West) & Uttrakhand"};
    String[] South = {"(AP)-Andhra Pradesh & Telangana","(KL)-Kerala & Lakshadweep","(KA)-Karnataka","(TN)-Tamil Nadu"};

    Button verify_btn;
    EditText phonenumberenteredbyuser;
    RelativeLayout linearLayout;
    TextView number;
    Handler handler;
    Runnable r;

    LinearLayout l1,l2;
    CountDownTimer cTimer = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_page);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        handler = new Handler();

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("User Data");

        verify_btn = (Button) findViewById(R.id.verifybutton);
        phonenumberenteredbyuser = (EditText) findViewById(R.id.phonenumberenteredbyuser);
        number = (TextView)findViewById(R.id.number);

        l1 = (LinearLayout)findViewById(R.id.enternumberlinear);
        l2 = (LinearLayout)findViewById(R.id.submitnumberlinear);

        linearLayout = (RelativeLayout) findViewById(R.id.formlayouttoshow);
        initalizeview();

        mobilenumber = (EditText) findViewById(R.id.getmobilenumber);
        msubbtn.setAlpha(0.5f);
        msubbtn.setEnabled(false);

        resendotp = (TextView) findViewById(R.id.resendcode);
        timer = (TextView) findViewById(R.id.timer);
        mobilenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mobilenumber.length() == 10) {
                    msubbtn.setAlpha(1f);
                    msubbtn.setEnabled(true);
                }else if(mobilenumber.length() != 10) {
                    msubbtn.setAlpha(0.5f);
                    msubbtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        msubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobilenumber.length() != 10) {
                    mobilenumber.requestFocus();
                    mobilenumber.setError("Please enter valid 10 Digit Mob number");
                }else {
                    UserProfileDataPostData.checkandroidid(new APICallBack() {
                        @Override
                        public void onResponse(boolean success) {
                            if (!success){
                                OTP_PostData.deleteotp(mobilenumber.getText().toString(),Startup_page.this);
                                String searchmobilenumber = mobilenumber.getText().toString();
                                phonenumber = "+91"+searchmobilenumber;
                                phonenumberuserdata = "+91"+searchmobilenumber;
                                try {
                                    new CountDownTimer(60000, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            // Used for formatting digit to be in 2 digits only
                                            NumberFormat f = new DecimalFormat("00");
                                            long min = (millisUntilFinished / 60000) % 60;
                                            long sec = (millisUntilFinished / 1000) % 60;
                                            timer.setText("OTP will expire in : "+f.format(min) + ":" + f.format(sec));
                                            resendotp.setEnabled(false);
                                        }
                                        // When the task is over it will print 00:00:00 there
                                        public void onFinish() {
                                            timer.setText("OTP Timeout : 00:00");
                                            resendotp.setEnabled(true);
                                            OTP_PostData.deleteotp(mobilenumber.getText().toString(),Startup_page.this);
                                        }
                                    }.start();
                                    OTP_PostData.postsendotp(mobilenumber.getText().toString(),Startup_page.this);
                                    l1.setVisibility(View.GONE);
                                    msubbtn.setVisibility(View.GONE);
                                    l2.setVisibility(View.VISIBLE);
                                    verify_btn.setVisibility(View.VISIBLE);
                                    number.setText("6-digit OTP has been sent to your mobile number +91 "+searchmobilenumber);
                                    String phoneno = mobilenumber.getText().toString();

                                    verify_btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String code = phonenumberenteredbyuser.getText().toString();
                                            Log.d("Code by user",code);
                                            OTP_PostData.validateotp(mobilenumber.getText().toString(),code,Startup_page.this);

                                        }
                                    });
                                }catch (Exception e){
                                    Toast.makeText(Startup_page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    },Startup_page.this,getApplicationContext());
                }
            }
        });
}

//if(Datacontainer.checkidresult.equals("false")){
//        String searchmobilenumber = mobilenumber.getText().toString();
//        phonenumber = "+91"+searchmobilenumber;
//        phonenumberuserdata = "+91"+searchmobilenumber;
//        try {
//
//            Random random = new Random();
//            int val = random.nextInt(999999-100000)+1;
//            String abc = Integer.toString(val);
//
//            OTP_PostData.postsendotp(abc,mobilenumber.getText().toString(),Datacontainer.getDate(),Startup_page.this);
//            l1.setVisibility(View.GONE);
//            msubbtn.setVisibility(View.GONE);
//            l2.setVisibility(View.VISIBLE);
//            verify_btn.setVisibility(View.VISIBLE);
//            number.setText("6-digit OTP has been sent to your mobile number +91 "+searchmobilenumber);
//            String phoneno = mobilenumber.getText().toString();
//
//            verify_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String code = phonenumberenteredbyuser.getText().toString();
//                    Log.d("Code by user",code);
//                    if (Datacontainer.sentotp.equals(code)){
//                        Intent i = new Intent(getApplicationContext(), UserFormData.class);
//                        i.putExtra("verifiedmob",phoneno);
//                        startActivity(i);
//                        finish();
//                    }else{
//                        Toast.makeText(Startup_page.this, "Invalid OTP. Enter valid OTP", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
//        }catch (Exception e){
//            Toast.makeText(Startup_page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//                            else{
//        sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("strprm", mprm);
//        editor.putString("strmob", mmob);
//        editor.putString("stremail", memail);
//        editor.putString("strname", mname);
//        editor.apply();
//
//        Intent i = new Intent(getApplicationContext(), MainHomePage.class);
//        i.putExtra("mob",mmob);
//        i.putExtra("prm",mprm);
//        i.putExtra("email",memail);
//        i.putExtra("name",mname);
//        startActivity(i);
//        finish();
//    }

    private void processButtonByTextLength() {
        String inputText = mobilenumber.getText().toString();
        if(inputText.length() != 10)
        {
            msubbtn.setEnabled(false);
        }else
        {
            msubbtn.setEnabled(true);
        }
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

    private void initalizeview(){

        msubbtn = (Button) findViewById(R.id.msendotp);
        text1 = findViewById(R.id.sharedpref1);
        text2 = findViewById(R.id.sharedpref2);
        text3 = findViewById(R.id.sharedpref3);
        text4 = findViewById(R.id.sharedpref4);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }

    public void backbutton(View view) {
        this.finish();
    }

    public void resendotp(View view) {

        String searchmobilenumber = mobilenumber.getText().toString();
        phonenumber = "+91"+searchmobilenumber;
        phonenumberuserdata = "+91"+searchmobilenumber;
        try {
            new CountDownTimer(50000, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Used for formatting digit to be in 2 digits only
                    NumberFormat f = new DecimalFormat("00");
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    timer.setText("OTP will expire in : "+f.format(min) + ":" + f.format(sec));
                    resendotp.setEnabled(false);
                }
                // When the task is over it will print 00:00:00 there
                public void onFinish() {
                    timer.setText("OTP Timeout : 00:00");
                    resendotp.setEnabled(true);
                    OTP_PostData.deleteotp(mobilenumber.getText().toString(),Startup_page.this);
                }
            }.start();
            OTP_PostData.postsendotp(mobilenumber.getText().toString(),Startup_page.this);
            l1.setVisibility(View.GONE);
            msubbtn.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
            verify_btn.setVisibility(View.VISIBLE);
            number.setText("6-digit OTP has been sent to your mobile number +91 "+searchmobilenumber);
            String phoneno = mobilenumber.getText().toString();

            verify_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String code = phonenumberenteredbyuser.getText().toString();
                    Log.d("Code by user",code);
                    OTP_PostData.validateotp(mobilenumber.getText().toString(),code,Startup_page.this);

                }
            });
        }catch (Exception e){
            Toast.makeText(Startup_page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}