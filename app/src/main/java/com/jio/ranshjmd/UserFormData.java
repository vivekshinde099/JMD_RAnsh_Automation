package com.jio.ranshjmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.RequestAPI.UserProfileDataPostData;
import com.mdminfo.mdmsdk.MDMSDK;

public class UserFormData extends AppCompatActivity {

    String[] Mainzone = {"East","West","North","South"};
    String[] East = {"(AS)-Assam","(NE)-North East","(BR)-Bihar & Jharkhand","(KO)-Kolkata","(OR)-Odisha","(WB)-West Bengal"};
    String[] West = {"(GJ)-Gujarat","(MP)-Madhya Pradesh & Chhattisgarh","(MU)-Mumbai","(MH)-Maharashtra & Goa",};
    String[] North = {"(DL)-Delhi","(HR)-Haryana","(HP)-Himachal Pradesh","(JK)-Jammu and Kashmir","(PB)-Punjab","(RJ)-Rajasthan","(UE)-UP (East)","(UW)-UP (West) & Uttrakhand"};
    String[] South = {"(AP)-Andhra Pradesh & Telangana","(KL)-Kerala & Lakshadweep","(KA)-Karnataka","(TN)-Tamil Nadu"};

    Button registerbtn;
    private EditText aname,aprm,amob,aemail,amakemodel,apos;
    private TextView azonetext,acircletext;
    Spinner azone,acircle;
    String phonenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form_data);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String dataTransmited=intent.getStringExtra("verifiedmob");

        aname = findViewById(R.id.getmasterdataname);
        aprm = findViewById(R.id.getmasterdataprm);
        amob = findViewById(R.id.getmasterdatamobile);
        amob.setText(dataTransmited);
        aemail = findViewById(R.id.getmasterdataemail);
        amakemodel =  findViewById(R.id.getmasterdatamakemodel);
        azonetext =  findViewById(R.id.zone_text);
        acircletext =  findViewById(R.id.ciecle_text);
        apos =  findViewById(R.id.getmasterdatapos);

        String model = Build.MANUFACTURER +" "+ Build.MODEL;
        amakemodel.setText(model);

        registerbtn =  findViewById(R.id.masterdatasubbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setdata_usermasterdata();
                }catch (Exception e){
                    Toast.makeText(UserFormData.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        azone = (Spinner) findViewById(R.id.getmasterdatazone);
        acircle = (Spinner) findViewById(R.id.getmasterdatacircle);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Mainzone);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        azone.setAdapter(aa);
        try {
            azone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String zone = (String) parent.getItemAtPosition(position);
                    azonetext.setText(zone);
                    switch (zone)
                    {
                        case "East":
                            ArrayAdapter a1 = new ArrayAdapter(UserFormData.this,android.R.layout.simple_spinner_item,East);
                            a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            acircle.setAdapter(a1);
//                            azonetext.setText("East");
                            break;
                        case "West":
                            ArrayAdapter a2 = new ArrayAdapter(UserFormData.this,android.R.layout.simple_spinner_item,West);
                            a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            acircle.setAdapter(a2);
//                            azonetext.setText("West");
                            break;
                        case "North":
                            ArrayAdapter a3 = new ArrayAdapter(UserFormData.this,android.R.layout.simple_spinner_item,North);
                            a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            acircle.setAdapter(a3);
//                            azonetext.setText("North");
                            break;
                        case "South":
                            ArrayAdapter a4 = new ArrayAdapter(UserFormData.this,android.R.layout.simple_spinner_item,South);
                            a4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            acircle.setAdapter(a4);
//                            azonetext.setText("South");
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            }catch (Exception e){

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            acircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String circle = (String) parent.getItemAtPosition(position);
                    acircletext.setText(circle);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

    }

    private void setdata_usermasterdata(){

        String getname = aname.getText().toString();
        String getprm = aprm.getText().toString();
        String getmob = amob.getText().toString();
        String getemail = aemail.getText().toString();
        String getzone = azonetext.getText().toString();
        String getcircle = acircletext.getText().toString();
        String getmodeltext = amakemodel.getText().toString();
        String getpos = apos.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        phonenumber = "+91"+getmob;

        String getimei = MDMSDK.imeiNumber(getApplicationContext());

        if (getname.isEmpty()){
            aname.requestFocus();
            aname.setError("Please Enter Full Name");
        }else if (getprm.isEmpty()){
            aprm.requestFocus();
            aprm.setError("Please enter valid PRM/Store ID number");
        }else if (getpos.length() != 10){
                apos.requestFocus();
                apos.setError("Please enter valid 10 Digit POS ID number");
        }else if (getmob.length() != 10){
            amob.requestFocus();
            amob.setError("Please enter valid 10 Digit Mob number");
        }
        else if (!getemail.matches(emailPattern)){
            aemail.requestFocus();
            aemail.setError("Please enter valid Email ID");
        }else if (getzone.isEmpty()){
            Toast.makeText(this, "Kindly Select Zone", Toast.LENGTH_SHORT).show();
        }else if (getcircle.isEmpty()){
            Toast.makeText(this, "Kindly Select Circle", Toast.LENGTH_SHORT).show();
        }else{


            UserProfileDataPostData.postupdata(getmob,getcircle,Datacontainer.getDate(),getemail,getmob,getmodeltext,getname,getpos,getprm,getimei,getzone,UserFormData.this);
        }
    }

    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        String time = dfTime.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }

    public void backbutton(View view) {
        finishAffinity();
    }

}