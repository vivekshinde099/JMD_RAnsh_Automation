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
import android.widget.TextView;

import com.jio.ranshjmd.R;

public class Myaccount extends AppCompatActivity {

    TextView name,prm,mob,email;
    Handler handler;
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_close_24);
        getSupportActionBar().setTitle("");

        name = findViewById(R.id.myaccount_name);
        prm = findViewById(R.id.myaccount_prm);
        mob = findViewById(R.id.myaccount_mobile);
        email = findViewById(R.id.myaccount_email);


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

    public void open_reopen_layout(View view) {
        Intent intent = new Intent(Myaccount.this,UpdateAccount.class);
        startActivity(intent);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}