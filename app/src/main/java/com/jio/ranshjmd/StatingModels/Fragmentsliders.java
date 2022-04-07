package com.jio.ranshjmd.StatingModels;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.Startup_page;

public class Fragmentsliders extends AppCompatActivity {
    Button q1, q2, q3, q4;

    TextView t1,t2,t3,t4,label1;
    ViewPager viewPager;
    Fragmentslideradapter pageViewAdapter;
    TextView textView,textView1,textView2;
    RelativeLayout linearLayout;
    LinearLayout l1,l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentsliders);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        q1 = (Button) findViewById(R.id.home);
        q2 = (Button) findViewById(R.id.report);
        q3 = (Button) findViewById(R.id.payment);
        q4 = (Button) findViewById(R.id.Account);

        t1 = (TextView)findViewById(R.id.one);
        t2 = (TextView)findViewById(R.id.two);
        t3 = (TextView)findViewById(R.id.three);
        t4 = (TextView)findViewById(R.id.four);
        label1 = (TextView)findViewById(R.id.some_id);
        l1 = (LinearLayout)findViewById(R.id.tncl1);
        l2 = (LinearLayout)findViewById(R.id.tncl2);


        textView1 = (TextView) findViewById(R.id.checkboxtextview1);
        textView2 = (TextView) findViewById(R.id.checkboxtextview2);
        textView = (TextView) findViewById(R.id.checkboxtextview);
        textView.setText("By continuing, you agree to our ");
        linearLayout = (RelativeLayout) findViewById(R.id.formlayouttoshow);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termscond(linearLayout);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacypolicy(linearLayout);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.fragment_container);
        pageViewAdapter = new Fragmentslideradapter(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdapter);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(4);
                    if (isPermissionGranted()) {
                        Datacontainer.customtoast("Permission Already Granted",Fragmentsliders.this,R.drawable.check_icon);

                        Intent i = new Intent(Fragmentsliders.this, Startup_page.class);
                        finish();
                        startActivity(i);
                    } else if (ContextCompat.checkSelfPermission(getBaseContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getBaseContext(),Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getBaseContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(Fragmentsliders.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
                        takePermission();
                    }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void onChangeTab(int position) {
        if (position == 0) {
            t1.setBackgroundResource(R.drawable.selected_drawable);
            t2.setBackgroundResource(R.drawable.unselected_drawable);
            t3.setBackgroundResource(R.drawable.unselected_drawable);
            t4.setBackgroundResource(R.drawable.unselected_drawable);
            label1.setText("1");
            q1.setVisibility(View.VISIBLE);
            q2.setVisibility(View.GONE);
            q3.setVisibility(View.GONE);
            q4.setVisibility(View.GONE);
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
        }
        if (position == 1) {
            t2.setBackgroundResource(R.drawable.selected_drawable);
            t1.setBackgroundResource(R.drawable.unselected_drawable);
            t3.setBackgroundResource(R.drawable.unselected_drawable);
            t4.setBackgroundResource(R.drawable.unselected_drawable);
            label1.setText("2");
            q1.setVisibility(View.GONE);
            q2.setVisibility(View.VISIBLE);
            q3.setVisibility(View.GONE);
            q4.setVisibility(View.GONE);
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
        }
        if (position == 2) {
            t3.setBackgroundResource(R.drawable.selected_drawable);
            t1.setBackgroundResource(R.drawable.unselected_drawable);
            t2.setBackgroundResource(R.drawable.unselected_drawable);
            t4.setBackgroundResource(R.drawable.unselected_drawable);
            label1.setText("3");
            q1.setVisibility(View.GONE);
            q2.setVisibility(View.GONE);
            q3.setVisibility(View.VISIBLE);
            q4.setVisibility(View.GONE);
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
        }
        if (position == 3) {
            t4.setBackgroundResource(R.drawable.selected_drawable);
            t1.setBackgroundResource(R.drawable.unselected_drawable);
            t2.setBackgroundResource(R.drawable.unselected_drawable);
            t3.setBackgroundResource(R.drawable.unselected_drawable);
            label1.setText("4");
            q1.setVisibility(View.GONE);
            q2.setVisibility(View.GONE);
            q3.setVisibility(View.GONE);
            q4.setVisibility(View.VISIBLE);
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
        }

    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            //For Android 11
            return Environment.isExternalStorageManager();
        } else {
            //For below
            int readEnternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            return readEnternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void takePermission() {
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
                        Datacontainer.customtoast("Permission Granted in android 11",Fragmentsliders.this,R.drawable.check_icon);

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
                    Datacontainer.customtoast("Read Permission is granted for android 10 or below",Fragmentsliders.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }

            }else if (requestCode == 102){
                boolean camera = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (camera) {
                    Datacontainer.customtoast("Camera Permission is granted for android 10 or below",Fragmentsliders.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }
            }else if (requestCode == 103){
                boolean LOCATION = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                if (LOCATION) {
                    Datacontainer.customtoast("Location Permission is granted for android 10 or below",Fragmentsliders.this,R.drawable.check_icon);
                } else {
                    takePermission();
                }
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getpermissions(View view) {

    }

    public void termscond(View view){

        View popupView = getLayoutInflater().inflate(R.layout.fragment_termsandcondition, null);
        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
    public void privacypolicy(View view){

        View popupView = getLayoutInflater().inflate(R.layout.fragment_privacypolicy, null);
        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
}