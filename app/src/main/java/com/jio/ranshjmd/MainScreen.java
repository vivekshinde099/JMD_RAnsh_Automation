package com.jio.ranshjmd;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import com.jio.ranshjmd.Common.Datacontainer;
//import kini.akshay.project.FirebaseConfig.FirebaseTrans;


public class MainScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FloatingActionButton floatingActionButton;
    TextView msharedprm, msharedmob, msharedemail,msharedname;
    int PERMISSION_ALL = 1;
    CardView data_cardview;
    TextView outageupdatetext;
    Animation blink_anim;
    ScrollView scrollView;
    Button launchbtn;
    TextView convert_me;

    private static final String NON_DA_RUNTIME_PERMISSION =
            "com.samsung.android.knox.permission.KNOX_NDA_PERIPHERAL_RT";

    String[] language = { "English","Hindi"};
    Handler handler;
    Runnable r;



//    ,"Marathi","Tamil","Kannada","Bengali","Telugu","Malayalam"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

//        Datacontainer.session_expired(MainScreen.this);


        launchbtn = findViewById(R.id.checkperbtn);

        scrollView = findViewById(R.id.scrollviewinfotext);
        outageupdatetext = findViewById(R.id.outageupdate_text);
        outageupdatetext.setSelected(true);
        outageupdatetext.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        data_cardview = findViewById(R.id.data_cardview);

        msharedprm = findViewById(R.id.msharedprm);
        msharedmob = findViewById(R.id.msharedmob);
        msharedemail = findViewById(R.id.msharedemail);
        msharedname = findViewById(R.id.msharedname);

        SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
        String nprm = getshared.getString("strprm", "PRM");
        String nmob = getshared.getString("strmob", "Mobile Number");
        String nemail = getshared.getString("stremail", "Email ID");
        String nname = getshared.getString("strname", "Name");

        msharedprm.setText(nprm);
        msharedmob.setText(nmob);
        msharedemail.setText(nemail);
        msharedname.setText("Welcome " +nname+"...!!");

//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = firebaseDatabase.getReference("outage");
//
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                FirebaseModel value = dataSnapshot.getValue(FirebaseModel.class);
//                String versioname = value.getCurrent_outage();
//                if(versioname.isEmpty()){
//                    scrollView.setVisibility(View.VISIBLE);
//                }else{
//                    scrollView.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "Outage : " + versioname);
//                    outageupdatetext.setText(versioname);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_list,language);
        aa.setDropDownViewResource(R.layout.spinner_list_expand);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }


    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            //For Android 11
            return Environment.isExternalStorageManager();
        } else {
            //For below
            int readEnternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            return readEnternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void takePermission() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 100);
            } catch (Exception exception) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 100);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 102);
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 103);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()) {
                        Toast.makeText(this, "Permission Granted in android 11", Toast.LENGTH_LONG).show();
                    } else {
                        takePermission();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 101) {

                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    Toast.makeText(this, "Read Permission is granted for android 10 or below", Toast.LENGTH_SHORT).show();
                } else {
                    takePermission();
                }

            }else if (requestCode == 102){
                boolean camera = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (camera) {
                    Toast.makeText(this, "Read Permission is granted for android 10 or below", Toast.LENGTH_SHORT).show();
                } else {
                    takePermission();
                }
            }else if (requestCode == 103){
                boolean LOCATION = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                if (LOCATION) {
                    Toast.makeText(this, "Read Permission is granted for android 10 or below", Toast.LENGTH_SHORT).show();
                } else {
                    takePermission();
                }
            }

        }

    }

//    public void checkPermission(String permission, int requestCode) {
//        if (ContextCompat.checkSelfPermission(this, permission)
//                == PackageManager.PERMISSION_DENIED) {
//
//            // Requesting the permission
//            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
//        } else {
//            //Toast.makeText(this,"Permission already granted",Toast.LENGTH_SHORT).show();
//        }
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getpermissions(View view) {

       if (isPermissionGranted()) {
            Toast.makeText(this, "Permission Already Granted", Toast.LENGTH_SHORT).show();
           Intent i = new Intent(MainScreen.this, Upload_imagebutton_activity.class);
           finish();
           startActivity(i);
        } else if (ContextCompat.checkSelfPermission(getBaseContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getBaseContext(),Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getBaseContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getBaseContext(), NON_DA_RUNTIME_PERMISSION) != PackageManager.PERMISSION_GRANTED)
           {
               ActivityCompat.requestPermissions(MainScreen.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, NON_DA_RUNTIME_PERMISSION}, 1000);
               takePermission();
           }
        }


    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();

    }
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//
//    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Datacontainer.getlanguage = (String) parent.getItemAtPosition(position);

//        switch (Datacontainer.getlanguage){
//            case "Hindi" :
//                textid();
//                break;
//        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setlanguage(String language){
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(language.toLowerCase()));
        } else {
            config.locale = new Locale(language.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }

    @Override
    public void onBackPressed() {
       finishAffinity();
    }

}
