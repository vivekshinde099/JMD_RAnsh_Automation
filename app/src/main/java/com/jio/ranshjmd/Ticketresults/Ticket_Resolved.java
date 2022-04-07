package com.jio.ranshjmd.Ticketresults;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jio.ranshjmd.ApiConstructor.AutoResTicketConstructor;
import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.PlaceHolderAPI.AutoResTicket_PlaceHolderAPI;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.RequestAPI.APIConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ticket_Resolved extends AppCompatActivity {

    TextView feedbacktext,issuepagetxt,btdata;
    Button submit,homepagebtn;
    EditText comment;
    RadioGroup group_buttons;
    RadioButton radioButton;
    String issuetitle;
    int btnumberfinaldata;
    RadioButton btn1,btn2,btn3,btn4;
    View lineview;
    TextView resolvertext,finalbtticketno,finalbtticketno1,imageicontext,imageicontext2;
    String finalcomment  = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_resolved);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24);
        getSupportActionBar().setTitle("");

        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        String nprm = getshared.getString("strprm", "PRM");
        String nmob = getshared.getString("strmob", "Mobile Number");
        String nemail = getshared.getString("stremail", "Email ID");
        String nname = getshared.getString("strname", "Name");

        lineview = (View)findViewById(R.id.linewview);
        feedbacktext = findViewById(R.id.feedback_text);
        Datacontainer.model(null,feedbacktext,Ticket_Resolved.this,"");
        finalbtticketno = findViewById(R.id.finalbtticketno);
        finalbtticketno1 = findViewById(R.id.finalbtticketno1);
        issuepagetxt = (TextView) findViewById(R.id.issuenametxt);
        btdata = (TextView) findViewById(R.id.btnumber);

        String value = getIntent().getExtras().getString("issuename");
        String btnum = getIntent().getExtras().getString("btnum");
        issuepagetxt.setText(value);
        btdata.setText(btnum);

        SharedPreferences getshared1 = getSharedPreferences("Data", MODE_PRIVATE);
        comment = findViewById(R.id.comment);
        submit = findViewById(R.id.submit);
        homepagebtn = findViewById(R.id.homepage);
        group_buttons = findViewById(R.id.group_buttons);
        btn1 = findViewById(R.id.excellent);
        btn2 = findViewById(R.id.poor);
        btn3 = findViewById(R.id.average);
        btn4 = findViewById(R.id.good);

        group_buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (btn1.isChecked()){
                    btn1.setBackground(getResources().getDrawable(R.drawable.icon_excellent));
                    btn2.setBackground(getResources().getDrawable(R.drawable.icon_sad_fade));
                    btn3.setBackground(getResources().getDrawable(R.drawable.icon_average_fade));
                    btn4.setBackground(getResources().getDrawable(R.drawable.icon_good_fade));
                }else if (btn2.isChecked()){
                    btn1.setBackground(getResources().getDrawable(R.drawable.icon_excellent_fade));
                    btn2.setBackground(getResources().getDrawable(R.drawable.icon_sad));
                    btn3.setBackground(getResources().getDrawable(R.drawable.icon_average_fade));
                    btn4.setBackground(getResources().getDrawable(R.drawable.icon_good_fade));
                }else if (btn3.isChecked()){
                    btn1.setBackground(getResources().getDrawable(R.drawable.icon_excellent_fade));
                    btn2.setBackground(getResources().getDrawable(R.drawable.icon_sad_fade));
                    btn3.setBackground(getResources().getDrawable(R.drawable.icon_average));
                    btn4.setBackground(getResources().getDrawable(R.drawable.icon_good_fade));
                }else if (btn4.isChecked()){
                    btn1.setBackground(getResources().getDrawable(R.drawable.icon_excellent_fade));
                    btn2.setBackground(getResources().getDrawable(R.drawable.icon_sad_fade));
                    btn3.setBackground(getResources().getDrawable(R.drawable.icon_average_fade));
                    btn4.setBackground(getResources().getDrawable(R.drawable.icon_good));

                }
            }
        });

        ImageView mainicon = findViewById(R.id.image_icon);
        imageicontext = findViewById(R.id.image_icon_text);
        imageicontext2 = findViewById(R.id.image_icon_text2);
        resolvertext = findViewById(R.id.resolvertext);


        mainicon.setBackgroundResource(R.drawable.ic_round_check_circle_24);
        imageicontext.setText("We are happy to help!");
        String num = btdata.getText().toString();
        imageicontext2.setText(Html.fromHtml("<font color='#141414'>"+"AR"+num+"</font> has been resolved"));
        Datacontainer.model(null,imageicontext,Ticket_Resolved.this,"");
        finalbtticketno.setVisibility(View.GONE);
        finalbtticketno1.setVisibility(View.VISIBLE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (group_buttons.getCheckedRadioButtonId() == -1){
                    Toast.makeText(Ticket_Resolved.this, "Kindly select any above option before sending feedback", Toast.LENGTH_LONG).show();
                }else {
                    btnumberfinaldata = Integer.parseInt(btdata.getText().toString());
                    issuetitle = issuepagetxt.getText().toString();
                    int selectedid =group_buttons.getCheckedRadioButtonId();
                    radioButton = (RadioButton)findViewById(selectedid);
                    String feedback = radioButton.getText().toString();
                    String allcomment = comment.getText().toString();
                    String nprm = getshared.getString("strprm", "PRM");
                    String nmob = getshared.getString("strmob", "Mobile Number");
                    String nemail = getshared.getString("stremail", "Email ID");
                    String nname = getshared.getString("strname", "Name");

                    if (allcomment.isEmpty()){
                          finalcomment = "No comment from user end.";
                      }else{
                          finalcomment = allcomment;
                      }

                    if (feedback.equals("Average") || feedback.equals("Poor")) {
                        if (allcomment.isEmpty()) {
                            Datacontainer.customtoast("Kindly provide your feedback on comment box...!", Ticket_Resolved.this, R.drawable.ic_round_info_24);
                        } else {

                                AutoResTicket_PlaceHolderAPI autoResTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(AutoResTicket_PlaceHolderAPI.class);
                                AutoResTicketConstructor autoResTicketConstructor = new AutoResTicketConstructor(finalcomment,Datacontainer.getDate(),feedback);
                                Call<AutoResTicketConstructor> a = autoResTicket_placeHolderAPI.patchfeedbackdata(btnumberfinaldata,autoResTicketConstructor);
                                a.enqueue(new Callback<AutoResTicketConstructor>() {
                                    @Override
                                    public void onResponse(Call<AutoResTicketConstructor> a, Response<AutoResTicketConstructor> response) {

                                        if (response.isSuccessful()){
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AutoResTicketConstructor> a, Throwable t) {
                                    }

                                });
                            comment.setText("");
                            radioButton.setChecked(false);
                            feedbacktext.setVisibility(View.GONE);
                            group_buttons.setVisibility(View.GONE);
                            comment.setVisibility(View.GONE);
                            submit.setVisibility(View.GONE);
                            lineview.setVisibility(View.GONE);
                            imageicontext.setText("Feedback submitted\nThank you!");
                            homepagebtn.setVisibility(View.VISIBLE);
                        }
                    }else {
                        try {
                            AutoResTicket_PlaceHolderAPI autoResTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(AutoResTicket_PlaceHolderAPI.class);
                            AutoResTicketConstructor autoResTicketConstructor = new AutoResTicketConstructor(finalcomment,Datacontainer.getDate(),feedback);
                            Call<AutoResTicketConstructor> a = autoResTicket_placeHolderAPI.patchfeedbackdata(btnumberfinaldata,autoResTicketConstructor);
                            a.enqueue(new Callback<AutoResTicketConstructor>() {
                                @Override
                                public void onResponse(Call<AutoResTicketConstructor> a, Response<AutoResTicketConstructor> response) {

                                    if (response.isSuccessful()){

                                        Toast.makeText(Ticket_Resolved.this, "Submitted", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<AutoResTicketConstructor> a, Throwable t) {
                                }
                            });
                                                                        comment.setText("");
                                            radioButton.setChecked(false);
                                            feedbacktext.setVisibility(View.GONE);
                                            group_buttons.setVisibility(View.GONE);
                                            comment.setVisibility(View.GONE);
                                            submit.setVisibility(View.GONE);
                                            lineview.setVisibility(View.GONE);
                                            imageicontext.setText("Feedback submitted\nThank you!");
                                            homepagebtn.setVisibility(View.VISIBLE);

                        } catch (Exception e2) {
                            Log.e("mylog", "Error: " + e2.getMessage());
                        }

                    }
                }
            }
        });


    }

    public void go_to_homepage(View view) {
        Intent intent = new Intent(Ticket_Resolved.this, MainHomePage.class);
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
}