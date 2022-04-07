package com.jio.ranshjmd.Ticketresults;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.Ticket_status;

public class Ticket_NotResolved extends AppCompatActivity {
    Handler handler;
    Runnable r;
    TextView resolvertext,finalbtticketno,finalbtticketno1,issuepagetxt,imageicontext,imageicontext2,feedbacktext;
    String Getzone;
    DatabaseReference masterdatasearchid;
    FirebaseDatabase firebaseDatabase;
    long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_not_resolved);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24);
        getSupportActionBar().setTitle("");


        feedbacktext = findViewById(R.id.feedback_text);
        Datacontainer.model(null,feedbacktext, Ticket_NotResolved.this,"");
        finalbtticketno = findViewById(R.id.finalbtticketno);
        finalbtticketno1 = findViewById(R.id.finalbtticketno1);

        String stringfinalbtticketno = getIntent().getExtras().getString("Ticket_no");
        finalbtticketno.setText(Html.fromHtml("Your ticket number is<br><font color='#141414'>"+"BT"+stringfinalbtticketno+"</font>"));

        ImageView mainicon = findViewById(R.id.image_icon);
        imageicontext = findViewById(R.id.image_icon_text);
        imageicontext2 = findViewById(R.id.image_icon_text2);
        resolvertext = findViewById(R.id.resolvertext);

        mainicon.setBackgroundResource(R.drawable.ic_vector_sadface);
        imageicontext.setText("Sorry for the inconvenience caused");
        imageicontext2.setText("We have raised a ticket and informed our technical team. They will call you shortly!");
        Datacontainer.model(null,imageicontext,Ticket_NotResolved.this,"");

        String Title = getIntent().getExtras().getString("Title");
        String Mobile = getIntent().getExtras().getString("Mobilenumber");



    }




    public void go_to_homepage(View view) {
        Intent intent = new Intent(Ticket_NotResolved.this, MainHomePage.class);
        startActivity(intent);
    }

    public void viewticket(View view) {
        String stringfinalbtticket = getIntent().getExtras().getString("Ticket_no");
        Intent intent = new Intent(Ticket_NotResolved.this, Ticket_status.class);
        intent.putExtra("Account_history_BTNO",stringfinalbtticket);
        startActivity(intent);
    }

@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id =item.getItemId();
    if (id == android.R.id.home){
        this.finish();
    }

    return super.onOptionsItemSelected(item);
}

    public static String getDate(){

        long minutesToAdd=5;
        Date currentDate = new Date();
        Date futureDate = new Date(currentDate.getTime());
        Log.d("VALUE", String.valueOf(futureDate));
        return String.valueOf(futureDate);

    }
}