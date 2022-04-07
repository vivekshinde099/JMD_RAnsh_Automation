package com.jio.ranshjmd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.jio.ranshjmd.Adapters.NamesAdapter;
import com.jio.ranshjmd.Models.Names;

public class Issuepage extends AppCompatActivity {

       private AutoCompleteTextView msearchview;
       private ListView mlistview;
       private ArrayAdapter<String> mAdapter;
       private Animation animation;
       private TextView text1;
       private ImageView imageView,microphone;
        AutoCompleteTextView actv;
        String itemName;
        private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    Handler handler;
    Runnable r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuepage);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

//        Datacontainer.session_expired(Issuepage.this);

        animation = AnimationUtils.loadAnimation(Issuepage.this,R.anim.bounce);
        text1 = (TextView)findViewById(R.id.textviewissue);
        imageView = (ImageView) findViewById(R.id.imageview);
        microphone = (ImageView) findViewById(R.id.microphone);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msearchview.getText().toString();
                String textsearch = msearchview.getText().toString();

                switch (textsearch){
                    case "":
                        Toast.makeText(Issuepage.this, "Please search by Keyword", Toast.LENGTH_SHORT).show();
                        break;

//                        case "Agent Authentication Failed from OID" :
//                        Intent A = new Intent(getApplicationContext(), Checking_issue.class);
//                        msearchview.setText("");
//                        A.putExtra("A",textsearch);
//                        startActivity(A);
//                        break;

                    default:
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Issuepage.this);
                        alertDialogBuilder.setCancelable(false);
                        alertDialogBuilder.setMessage("Sorry, We could not find any related Issue.\nKindly raise query with us click ok to continue")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Issuepage.this,SR_Form.class);
                                        startActivity(intent);
                                    }
                                });
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();
                        break;
                }
            }
        });

        microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "listening...");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast
                            .makeText(Issuepage.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        msearchview = (AutoCompleteTextView) findViewById(R.id.searchview);

        mlistview = (ListView)findViewById(R.id.listview);

        actv = (AutoCompleteTextView) findViewById(R.id.searchview);


        List namesList = new ArrayList<Names>();

        namesList.add(new Names("Agent Authentication Failed from OID"));
        namesList.add(new Names("Customer Image Capture issue"));
        namesList.add(new Names("Cannot Perform Digital KYC as agent photo is not registered"));
        namesList.add(new Names("Gst Table Does not exist. Do you want to try again?"));
        namesList.add(new Names("MACID cannot be left blank"));
        namesList.add(new Names("Device time not match with server time"));
        namesList.add(new Names("Recharge Reversal"));
        namesList.add(new Names("Tender not configured"));



        //      List namesList;
   //     namesList = new ArrayList<>();
   //     namesList.add("Akshay");
   //     namesList.add("Vivek");

                NamesAdapter namesAdapter = new NamesAdapter(
                Issuepage.this,
                R.layout.activity_issuepage,
                R.id.lbl_name,
                namesList

        );
        //set adapter into listStudent
        actv.setThreshold(1);
        actv.setAdapter(namesAdapter);
      //  actv.showDropDown();




       actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

//                Names names = namesList.get(position);
                itemName = (String) adapterView.getItemAtPosition(position);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                msearchview.setText(
                        (result).get(0));

            }
        }
    }

}



