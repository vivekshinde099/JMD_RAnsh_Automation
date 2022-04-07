package com.jio.ranshjmd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.SMTP.SendMail;

public class Resolved_Open extends AppCompatActivity{

    Button submit;
    EditText comment;
    RadioGroup group_buttons;
    RadioButton radioButton;
    TextView resolvertext,finalbtticketno,finalbtticketno1,issuepagetxt,imageicontext,imageicontext2,feedbacktext;
    LinearLayout linearLayout;
    String issuetitle;
    Handler handler;
    Runnable r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolved_open);

        handler = new Handler();
        r = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Resolved_Open.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage(Html.fromHtml("Session Expired <br> Idle Timeout Error!!"))
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        });

                AlertDialog alert = alertDialogBuilder.create();
                if(!isFinishing()) {
                    alert.show();
                }
            }
        };
        startHandler();


        feedbacktext = findViewById(R.id.feedback_text);
        Datacontainer.model(null,feedbacktext,Resolved_Open.this,"");
        finalbtticketno = findViewById(R.id.finalbtticketno);
        finalbtticketno1 = findViewById(R.id.finalbtticketno1);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        issuepagetxt = (TextView) findViewById(R.id.issuenametxt);
        String value = getIntent().getExtras().getString("issuename");
        issuepagetxt.setText(value);

        String stringfinalbtticketno = getIntent().getExtras().getString("Ticket_no");
        finalbtticketno.setText("Your ticket number is\n"+stringfinalbtticketno);

        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        comment = findViewById(R.id.comment);
        submit = findViewById(R.id.submit);
        group_buttons = findViewById(R.id.group_buttons);
        linearLayout = findViewById(R.id.radiogrouplinear);
        Datacontainer.model(submit,null,Resolved_Open.this,"");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (group_buttons.getCheckedRadioButtonId() == -1){
                    Toast.makeText(Resolved_Open.this, "Kindly select any above option before sending feedback", Toast.LENGTH_LONG).show();
                }else {
                    issuetitle = issuepagetxt.getText().toString();
                    int selectedid =group_buttons.getCheckedRadioButtonId();
                    radioButton = (RadioButton)findViewById(selectedid);
                    String feedback = radioButton.getText().toString();
                String allcomment = comment.getText().toString();
                String nprm = getshared.getString("strprm", "PRM");
                String nmob = getshared.getString("strmob", "Mobile Number");
                String nemail = getshared.getString("stremail", "Email ID");
                String nname = getshared.getString("strname", "Name");

                Thread sender = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
                            String nuserid = getshared.getString("struserid", null);
                            String npass = getshared.getString("strpass", null);
                            SendMail sender = new SendMail(nuserid, npass);
//                            sender.sendMail(feedback, "Issue Title :"+issuetitle+"\nComment :"+allcomment+"\nName : "+nname+"\nMobile no. : "+nmob+"\nEmail : "+nemail+"\nPRM :"+nprm,
//                                    "partnerit@outook.com",
//                                    "partneritfeedback@gmail.com");


                        } catch (Exception e) {
                            Log.e("mylog", "Error: " + e.getMessage());
                        }
                    }
                });
                sender.start();
                Toast.makeText(Resolved_Open.this,"Feedback has been shared with PartnerIT\nThank You..!!",Toast.LENGTH_SHORT).show();
                comment.setText("");
                radioButton.setChecked(false);
                    linearLayout.setVisibility(View.GONE);
                    imageicontext.setText("Thank You for your Valuable Feedback");


                }
            }
        });

        ImageView mainicon = findViewById(R.id.image_icon);
        imageicontext = findViewById(R.id.image_icon_text);
        imageicontext2 = findViewById(R.id.image_icon_text2);
        resolvertext = findViewById(R.id.resolvertext);

        String getdecision = getIntent().getExtras().getString("Resolved");
        resolvertext.setText(getdecision);


        switch (getdecision){

            case "resolved" :
                linearLayout.setVisibility(View.VISIBLE);
                mainicon.setBackgroundResource(R.drawable.ic_round_check_circle_24);
                imageicontext.setText("We are pleased to assist you.Thank you");
                Datacontainer.model(null,imageicontext,Resolved_Open.this,"");
                finalbtticketno.setVisibility(View.GONE);
                finalbtticketno1.setVisibility(View.VISIBLE);

                break;
            case "notresolved" :
                mainicon.setBackgroundResource(R.drawable.ic_vector_sadface);
                imageicontext.setText("Sorry for the inconvenience caused");
                imageicontext2.setText("We have raised a ticket and informed our technical team. They will call you shortly!");
                Datacontainer.model(null,imageicontext,Resolved_Open.this,"");
                break;
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


    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        stopHandler();//stop first and then start
        startHandler();
    }
    public void stopHandler() {
        handler.removeCallbacks(r);
    }
    public void startHandler() {
        handler.postDelayed(r, 10*60*1000); //for 5 minutes
    }


}