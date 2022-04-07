package com.jio.ranshjmd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.Common.Utils;
import com.jio.ranshjmd.Models.Mylistdata;
import com.jio.ranshjmd.Toolbar.Myaccount;
import com.jio.ranshjmd.Toolbar.UpdateAccount;

public class Burgerlayout extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    int numberOfColumns = 1;
    CardView card;
    TextView test,text1,text2;
    RelativeLayout layout;
    Button btn;
    ArrayList<String> mydatalistArrayList;
    private Menu menu;

    Handler handler;
    Runnable r;

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Create your menu...

        this.menu = menu;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_burger);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

//        handler = new Handler();
//        r = new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Burgerlayout.this);
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


        mydatalistArrayList = new ArrayList<String>();

        layout = (RelativeLayout) findViewById(R.id.categorylayout);
        btn = (Button)layout.findViewById(R.id.red_btn_jpos);
        text1 = (TextView)layout.findViewById(R.id.main1);
        text2 = (TextView)layout.findViewById(R.id.main2);

//---------------------include category_fornt class---------------------------------
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xCA1212);


        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        Datacontainer.getshared= getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        String nname = getshared.getString("strname", "Name");
        String nmob = getshared.getString("strmob", "Mobile Number");



        View headerView = nav.getHeaderView(0);

        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_header_name_text);
        navUsername.setText("Hi, "+nname);

        TextView account = (TextView) headerView.findViewById(R.id.menu_myaccount);
//        Datacontainer.model(null,account,Burgerlayout.this,"");
        TextView updateaccount = (TextView) headerView.findViewById(R.id.menu_updateaccount);
//        Datacontainer.model(null,updateaccount,Burgerlayout.this,"");
        TextView history = (TextView) headerView.findViewById(R.id.menu_history);
//        Datacontainer.model(null,history,Burgerlayout.this,"");
        TextView logout = (TextView) headerView.findViewById(R.id.menu_exit);
//        Datacontainer.model(null,logout,Burgerlayout.this,"");

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myaccount = new Intent(Burgerlayout.this, Myaccount.class);
                startActivity(myaccount);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        updateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent updateaccount = new Intent(Burgerlayout.this, UpdateAccount.class);
                startActivity(updateaccount);
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent history = new Intent(Burgerlayout.this, Accounthistory.class);
//                startActivity(history);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                drawerLayout.closeDrawer(GravityCompat.START);

            }
        });



        String[] strArray = nname.split(" ");
        StringBuilder builder = new StringBuilder();

        if (strArray.length > 0){
            builder.append(strArray[0], 0, 1);
        }

        TextView initailletter = (TextView) headerView.findViewById(R.id.initialletter);
        initailletter.setText(builder.toString());



//        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
//            {
//
//                switch (menuItem.getItemId())
//                {
//                    case R.id.menu_exit:
//                        finishAffinity();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.menu_myaccount:
//                        Intent myaccount = new Intent(Burgerlayout.this, Myaccount.class);
//                        startActivity(myaccount);
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.menu_updateaccount:
//                        Intent updateaccount = new Intent(Burgerlayout.this, UpdateAccount.class);
//                        startActivity(updateaccount);
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.menu_history:
//                        Intent history = new Intent(Burgerlayout.this, Accounthistory.class);
//                        startActivity(history);
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                }
//
//                return true;
//
//            }
//        });


        test = findViewById(R.id.category_versiontab);
//        boolean isInstalled = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
//
//
//        PackageInfo versioname = Utils.getversionname("com.jio.jpp1",getPackageManager());
//
//        if (versioname == null){
//            test.setText("Jio Pos Plus Current Version : No App Installed");
//        }else {
//            test.setText("Jio Pos Plus Current Version : " + versioname.versionName);
//        }
        currentversionname();

        card = findViewById(R.id.keyboardcardlayout);
        card.setBackgroundResource(R.drawable.corners);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Issuepage.class);
                startActivity(intent);
            }
        });

        RecyclerView  recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));

        Mylistdata[] myissuelist1 = new Mylistdata[]{
                new Mylistdata("DKYC Related Issue",Burgerlayout.this),
                new Mylistdata("Login Related Issue",Burgerlayout.this),
                new Mylistdata("Recharge Related Issue",Burgerlayout.this),
                new Mylistdata("Jio Pos Plus Download",Burgerlayout.this),
                new Mylistdata("M-I-S & Report Related",Burgerlayout.this),
                new Mylistdata("Process Related",Burgerlayout.this),
                new Mylistdata("Jio Pos Plus - Utilities Related",Burgerlayout.this),
                new Mylistdata("Postpaid DKYC Related",Burgerlayout.this),
                new Mylistdata("I-Hub",Burgerlayout.this),
                new Mylistdata("Jio Pos Fttx",Burgerlayout.this),
        };
//        Maincategoryissue MYISSUELISTADAP1 = new Maincategoryissue(myissuelist1,Burgerlayout.this);
//        recyclerView.setAdapter(MYISSUELISTADAP1);
        textid();

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

    }

    private void textid() {

   //     Datacontainer.model(btn,null,Burgerlayout.this,"");
        Datacontainer.model(null,text1,Burgerlayout.this,"");
        Datacontainer.model(null,text2,Burgerlayout.this,"");
    }

    public void currentversionname(){
        boolean isInstalled = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
        if (isInstalled){
            PackageInfo versioname = Utils.getversionname("com.jio.jpp1",getPackageManager());
            test.setText("Jio Pos Plus Current Version : " + versioname.versionName);
        }else {
            PackageInfo versioname = Utils.getversionname("com.ril.rposcentral",getPackageManager());
            test.setText("Jio Pos Plus Current Version : " + versioname.versionName);
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