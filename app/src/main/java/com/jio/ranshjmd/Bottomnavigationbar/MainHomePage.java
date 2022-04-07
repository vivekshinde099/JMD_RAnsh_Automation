package com.jio.ranshjmd.Bottomnavigationbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.jio.ranshjmd.Adapters.NamesAdapter;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.Common.NotificationCount;
import com.jio.ranshjmd.Models.Names;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.SR_Form;
import com.jio.ranshjmd.Toolbar.Myaccount;

public class MainHomePage extends AppCompatActivity {

    AutoCompleteTextView actv;
    NavigationView nav;
    Toolbar toolbar,searchtoolbar;
    LinearLayout toolbaricons;
    EditText searchView;
    ImageView closebtn;
    TextView text1,text2,text3,text4;
    ImageView microphone,sendbtn;
    Handler handler;
    Runnable r;
    LinearLayout tabbaricons;
    CardView toolbarcard;
    TextView ticketpage;
    String ticketpageintent,flag;
    String ticketpagedata="";
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    NotificationCount notificationcount;
    int noticount ;
    TextView textView,flagcounter,notificationNumber ;
    long abc;
    List<String> statuslist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_page2);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        Datacontainer.sharedprefrence(getApplicationContext());

                notificationcount = new NotificationCount(findViewById(R.id.textView));
                textView = findViewById(R.id.textView);
                ticketpage = findViewById(R.id.ticketpage);
                flagcounter = findViewById(R.id.flagcounter);
                ticketpage.setText(Datacontainer.ticketpagedatahandler);
                ticketpagedata = ticketpage.getText().toString().toLowerCase();
                statuslist = new ArrayList<>();



                toolbaricons = (LinearLayout)findViewById(R.id.toolbar_icons);
                nav=(NavigationView)findViewById(R.id.navmenu);

                BottomNavigationView btmview = findViewById(R.id.botton_navigation);
              //  BottomNavigationView btmview2 = findViewById(R.id.botton_navigation2);

                btmview.setOnNavigationItemSelectedListener(navlistner);
            //    btmview2.setOnNavigationItemSelectedListener(navlist);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();

                actv = (AutoCompleteTextView) findViewById(R.id.searchview);
                searchView = (EditText) findViewById(R.id.searchviewdummy);
                tabbaricons = (LinearLayout)findViewById(R.id.tabbar_icons);
                toolbarcard = (CardView)findViewById(R.id.toolbarcardview);

                List namesList = new ArrayList<Names>();

        namesList.add(new Names("Agent Authentication Failed from OID"));
        namesList.add(new Names("Store Code Mis-Match"));
        namesList.add(new Names("Payment not found"));
        namesList.add(new Names("Gst Table Does not exist. Do you want to try again?"));
        namesList.add(new Names("Something went wrong,Response:No Data"));
        namesList.add(new Names("Ledger / Order Status"));
        namesList.add(new Names("User is not present in OIAM"));
        namesList.add(new Names("connection refused."));
        namesList.add(new Names("I want to know my POS ID"));
        namesList.add(new Names("Touch screen not working"));
        namesList.add(new Names("Tablet Battery getting drained (Fast Discharge)"));
        namesList.add(new Names("Order pending in JioMart Digital"));
        namesList.add(new Names("Add to cart button not enabled"));
        namesList.add(new Names("Pin code not available"));
        namesList.add(new Names("Web page not available"));
        namesList.add(new Names("Order Placement Process through JioMart Digital"));
        namesList.add(new Names("Unable to search product in JioMart Digital."));
        namesList.add(new Names("Error checking out inventory. Please try after sometime."));
        namesList.add(new Names("App is stuck on blue screen"));
        namesList.add(new Names("Unable to click on Buy Now option"));
        namesList.add(new Names("User account is locked/disabled"));
        namesList.add(new Names("Unable to login in JMD"));
        namesList.add(new Names("Invalid User Credentials"));
        namesList.add(new Names("504 gateway timeout"));
        namesList.add(new Names("404 app config not found"));
        namesList.add(new Names("Unable to compare products"));
        namesList.add(new Names("No data found, while entering OTP"));
        namesList.add(new Names("Order cancellation process"));
        namesList.add(new Names("Amount refund process"));
        namesList.add(new Names("Buffering/Redirecting to Login screen/SSL pinning failed"));
        namesList.add(new Names("product price mismatch"));
        namesList.add(new Names("Failed reason : validation failed"));
        namesList.add(new Names("Online Order MASP Process"));
        namesList.add(new Names("You cannot void selected article"));
        namesList.add(new Names("OTP could not be received"));
        namesList.add(new Names("Amount requested does not match online payment"));
        namesList.add(new Names("Some technical error occurred"));
        namesList.add(new Names("KYC failed"));
        namesList.add(new Names("504 gateway time out"));
        namesList.add(new Names("Your loan application is rejected - APPROVAL CRITERIA NOT MET"));
        namesList.add(new Names("Screen goes blank after selecting Bundle Phone option"));
        namesList.add(new Names("We are processing your data. Please wait for some time and retry"));
        namesList.add(new Names("Number is not eligible"));
        namesList.add(new Names("Your loan application is under process. Please try after sometime"));
        namesList.add(new Names("You cannot sell this device from device sale"));
        namesList.add(new Names("Payment deducted, EJ not generated"));
        namesList.add(new Names("Item Not Found"));
        namesList.add(new Names("Pincode Not Found"));
        namesList.add(new Names("Error in call while sending the payment link"));
        namesList.add(new Names("Error in Call after completion of payment by customer"));
        namesList.add(new Names("Tax Details not configured"));
        namesList.add(new Names("Article price 0 (Zero)"));
        namesList.add(new Names("MOP not visible"));
        namesList.add(new Names("Failed to get Response"));
        namesList.add(new Names("Session Expired"));
        namesList.add(new Names("TV Display is Not Working"));
        namesList.add(new Names("Tablet over heating issue"));
        namesList.add(new Names("Tablet is not getting charged through POS stand"));
        namesList.add(new Names("Cursor visible on tablet screen"));
        namesList.add(new Names("STB Hardware related Issues"));
        namesList.add(new Names("Print not coming from printer"));
        namesList.add(new Names("Failed to configure RPOS.db"));










        NamesAdapter namesAdapter = new NamesAdapter(
                MainHomePage.this,
                R.layout.activity_issuepage,
                R.id.lbl_name,
                namesList
        );


        actv.setDropDownBackgroundDrawable(getResources().getDrawable(R.drawable.searchbar_roundedcorners));
        actv.setThreshold(1);
        actv.setAdapter(namesAdapter);
        actv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                tabbaricons.setVisibility(View.GONE);
                microphone.setVisibility(View.GONE);
                closebtn.setVisibility(View.VISIBLE);
                sendbtn.setVisibility(View.VISIBLE);
            }
        });

        actv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    textsearch();
                    handled = true;
                }
                return handled;
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        toolbar.setTitleTextColor(0xCA1212);


        Datacontainer.getshared= getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        String nname = getshared.getString("strname", "Name");
        String nmob = getshared.getString("strmob", "Mobile Number");

//        CreateTicket_PlaceHolderAPI createTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
//        Call<List<StatusticketdataConstructor>> call = createTicket_placeHolderAPI.getstatusdatabymob(nmob);
//        call.enqueue(new Callback<List<StatusticketdataConstructor>>() {
//            @Override
//            public void onResponse(Call<List<StatusticketdataConstructor>> call, Response<List<StatusticketdataConstructor>> response) {
//                List<StatusticketdataConstructor> posts = response.body();
//                for (StatusticketdataConstructor post : posts) {
//                 String status = post.getStatus_id();
//                 if(status.equals("unread")){
//                     statuslist.add(status);
//                 }
//
//                }
//                int count = statuslist.size();
//
//                int a = posts.size();
////                flagcounter.setText(a);
////                flag = flagcounter.getText().toString();
//                Toast.makeText(MainHomePage.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
//                if(count >= 1) {
//                    notificationcount.increament();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<StatusticketdataConstructor>> call, Throwable t) {
//
//            }
//        });
        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();

        DatabaseReference myRef1 = firebaseDatabase1.getReference("User Data").child("+91"+nmob);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    Datacontainer.Maxid = (snapshot.getChildrenCount());
                    Datacontainer.Best5 = Datacontainer.Maxid-5;
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        //textView.setVisibility(View.GONE);

//        FirebaseDatabase firebaseDatabase23 = FirebaseDatabase.getInstance();
//        DatabaseReference myRef23 = firebaseDatabase23.getReference("Status Data").child("+91"+Datacontainer.nmob);
//
//        myRef23.addChildEventListener(new ChildEventListener() {
//
//            @Override
//            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
//               if (snapshot.hasChildren()){
//                   String Interaction = snapshot.child("sr").getValue().toString();
//                   flagcounter.setText(Interaction);
//                   flag = flagcounter.getText().toString();
//                   if (flag.equals("1")){
//                       notificationcount.increament();
//                     //  Toast.makeText(MainHomePage.this, flag, Toast.LENGTH_SHORT).show();
//                   }
//               }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

   //     noticount=noticount-1;
   //     String id= String.valueOf(noticount+1);
        // String ncount = String.valueOf(noticount);



        microphone = (ImageView)findViewById(R.id.microphone);
        microphone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//                toolbaricons.setVisibility(View.GONE);
//                searchtoolbar.setVisibility(View.VISIBLE);
                tabbaricons.setVisibility(View.GONE);
                microphone.setVisibility(View.GONE);
                closebtn.setVisibility(View.VISIBLE);
                sendbtn.setVisibility(View.VISIBLE);

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
                            .makeText(MainHomePage.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        searchtoolbar = (Toolbar) findViewById(R.id.searchlayout);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbaricons.setVisibility(View.GONE);
                searchtoolbar.setVisibility(View.VISIBLE);
            }
        });

        closebtn = (ImageView) findViewById(R.id.searchlayoutcloseimage);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                searchtoolbar.setVisibility(View.GONE);
//                toolbaricons.setVisibility(View.VISIBLE);
                closebtn.setVisibility(View.GONE);
                sendbtn.setVisibility(View.GONE);
                microphone.setVisibility(View.VISIBLE);
                tabbaricons.setVisibility(View.VISIBLE);
                actv.setText("");

            }
        });
        sendbtn = (ImageView) findViewById(R.id.sendbutton);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textsearch();

            }
        });

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navlistner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            Fragment selectedfragment = null;

            if (selectedfragment == null) {
                switch (item.getItemId()) {
                    case R.id.title_home:
                        selectedfragment = new HomeFragment();

                        break;
                    case R.id.title_resolutions:
                        selectedfragment = new Fragment_LanguageSelector();

                        break;
                    case R.id.title_tickets:
                        selectedfragment = new TicketFragment();

                        break;
                }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedfragment).commit();





            }
            return true;
        }
    };
    private BottomNavigationView.OnNavigationItemSelectedListener navlist = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.title_exit:
                    exitfromapp();
                    break;
            }
            return false;
        }
    };


    public void bellicon(View view) {
        Intent i = new Intent(MainHomePage.this,Bellicon_activity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        tellFragments();
        exitfromapp();
    }



    private void tellFragments(){
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(Fragment f : fragments){
            if(f != null && f instanceof MainCategoryIssuePage)
                ((MainCategoryIssuePage)f).onBackPressed();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    public void userprofile(View view) {
        Intent intent = new Intent(MainHomePage.this,Myaccount.class);
        startActivity(intent);
    }

    public void convert(){
        Datacontainer.model(null,text1,MainHomePage.this,"");
        Datacontainer.model(null,text2,MainHomePage.this,"");
        Datacontainer.model(null,text3,MainHomePage.this,"");
        Datacontainer.model(null,text4,MainHomePage.this,"");
    }

    private void textsearch(){

        String textsearch = actv.getText().toString();
        switch (textsearch){
            case "":
                Toast.makeText(MainHomePage.this, "Please search by Keyword", Toast.LENGTH_SHORT).show();
                break;

            default:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainHomePage.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("Sorry, We could not find any related Issue.\nKindly raise query with us click ok to continue")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                actv.setText("");
                                Intent intent = new Intent(MainHomePage.this, SR_Form.class);
                                startActivity(intent);
                            }
                        });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
                break;
        }
    }

    private void exitfromapp() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainHomePage.this);
        builder.setMessage("Are you sure you want to Exit R-Ansh?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
                actv.setText(
                        (result).get(0));

                actv.post(new Runnable() {
                    @Override
                    public void run() {
                        actv.setSelection(actv.getText().length());
                    }
                });

            }
        }
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
}