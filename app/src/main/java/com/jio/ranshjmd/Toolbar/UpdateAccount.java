package com.jio.ranshjmd.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.jio.ranshjmd.APICallBack;
import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.RequestAPI.OTP_PostData;
import com.jio.ranshjmd.RequestAPI.UpdateProfilePatchData;

public class UpdateAccount extends AppCompatActivity {

    EditText name,prm,mob,email,otp;
    TextView text_otp;
    Button update,verify;
    SharedPreferences sharedPreferences;
    Handler handler;
    Runnable r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24);
        getSupportActionBar().setTitle("");

        name = findViewById(R.id.updateaccount_name);
        prm = findViewById(R.id.updateaccount_prm);
        mob = findViewById(R.id.updateaccount_mob);
        email = findViewById(R.id.updateaccount_email);
        update = findViewById(R.id.updateaccount_updatebtn);
        otp = findViewById(R.id.updateaccount_OTP);
        text_otp = findViewById(R.id.otp);
        verify = findViewById(R.id.updateaccount_verifybtn);

        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        String nprm = getshared.getString("strprm", "PRM");
        String nmob = getshared.getString("strmob", "Mobile Number");
        String nemail = getshared.getString("stremail", "Email ID");
        String nname = getshared.getString("strname", "Name");

        name.setText(nname);
        prm.setText(nprm);
        mob.setText(nmob);
        email.setText(nemail);

    }
    public void onBackPressed() {
        Intent first = new Intent(UpdateAccount.this, MainHomePage.class);
        startActivity(first);
        super.onBackPressed();
    }

    public void send_otp_forupdateprofile(View view) {

        final String PRM = prm.getText().toString();
        final String Email = email.getText().toString();
        final String Name = name.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String number = mob.getText().toString();

        if (Name.isEmpty()){
            name.requestFocus();
            name.setError("Please Enter Full Name");
        }else if (PRM.length() != 10){
            prm.requestFocus();
            prm.setError("Please enter valid 10 Digit PRM/Store ID number");
        }else if (!Email.matches(emailPattern)){
            email.requestFocus();
            email.setError("Please enter valid Email ID");
        }else {
            Random random = new Random();
            int val = random.nextInt(999999-100000)+1;
            String abc = Integer.toString(val);

            OTP_PostData.postsendotp(number, UpdateAccount.this);
            update.setVisibility(View.GONE);
            text_otp.setVisibility(View.VISIBLE);
            otp.setVisibility(View.VISIBLE);
            verify.setVisibility(View.VISIBLE);

        }

    }

    public void verify_otp(View view) {
        final String PRM = prm.getText().toString();
        final String Email = email.getText().toString();
        final String Name = name.getText().toString();
        String enteredotp = otp.getText().toString();
        final String Number = mob.getText().toString();
        if (Datacontainer.sentotp.equals(enteredotp)){

                UpdateProfilePatchData.updateprofiledata(Name, PRM, Email, Number, new APICallBack() {
                    @Override
                    public void onResponse(boolean b) {

                    }
                },UpdateAccount.this);



            SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("strprm", PRM);
            editor.putString("strmob", Number);
            editor.putString("stremail", Email);
            editor.putString("strname", Name);

            editor.apply();
            Intent intent = new Intent(UpdateAccount.this, Myaccount.class);
            startActivity(intent);
            finish();


        }else{
            Toast.makeText(UpdateAccount.this, "Invalid OTP. Enter valid OTP", Toast.LENGTH_SHORT).show();
        }


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