package com.jio.ranshjmd.StatingModels;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.jio.ranshjmd.ApiConstructor.VersionConstructor;
import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;
import com.jio.ranshjmd.Common.Utils;
import com.jio.ranshjmd.PlaceHolderAPI.Version_PlaceHolderAPI;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.RequestAPI.APIConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    TextView setversion;
    private final int SPLASH_DISPLAY_LENGTH = 500;
    String versioname,content,userid,passcode;
    ArrayList<String> versionlist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        versionlist = new ArrayList<>();



        setversion = findViewById(R.id.setversion);
        try {
            ConnectivityManager manager= (ConnectivityManager)
                    getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

            if(null!=activeNetwork) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);
                        int screenWidth = dm.widthPixels;
                        int screenHeight = dm.heightPixels;

                        double x = Math.pow(screenWidth / dm.xdpi, 2);
                        double y = Math.pow(screenHeight / dm.xdpi, 2);
                        double screenInches = Math.sqrt(x + y);
                        screenInches = (double) Math.round(screenInches * 10) / 10;
                        if (screenInches < 4.3 || screenInches >11.0) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
                            builder.setMessage("R-Ansh does not support below 5.0 inch and higher than 7.0 screen sizes");
                            builder.setCancelable(false);

                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finishAffinity();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                        else {
                            boolean isInstalledGT = Utils.isPackageInstalled("com.jio.jpp1", getPackageManager());
                            boolean isInstalledRR = Utils.isPackageInstalled("com.ril.rposcentral", getPackageManager());
                            //-------------------------------For GT---------------------------------------------------
                            if (isInstalledGT) {

                                Version_PlaceHolderAPI version_placeHolderAPI = APIConnection.getretrofitinstance().create(Version_PlaceHolderAPI.class);
                                Call<List<VersionConstructor>> call = version_placeHolderAPI.getPosts();

                                PackageInfo currentversioname = Utils.getversionname("com.jio.jpp1", getPackageManager());
                                versioname = currentversioname.versionName;

                                call.enqueue(new Callback<List<VersionConstructor>>() {
                                    @Override
                                    public void onResponse(Call<List<VersionConstructor>> call, Response<List<VersionConstructor>> response) {
                                        List<VersionConstructor> posts = response.body();
                                        for (VersionConstructor post : posts) {
                                            content = post.getOutage();
                                            Log.d("Data:", content);
                                            versionlist.add(content);
                                        }


                                        Log.d("Data:", String.valueOf(versionlist));
                                        if (versionlist.contains(versioname)) {
                                            Toast.makeText(SplashScreen.this, "success", Toast.LENGTH_SHORT).show();
                                            SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
                                            String nprm = getshared.getString("strprm", null);
                                            String nmob = getshared.getString("strmob", null);
                                            String nemail = getshared.getString("stremail", null);

                                            if (nprm != null && nmob != null && nemail != null) {
                                                Intent i = new Intent(SplashScreen.this, MainHomePage.class);
                                                startActivity(i);
                                                finish();
                                            } else {
                                                Intent i = new Intent(SplashScreen.this, Fragmentsliders.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        } else {
                                            Toast.makeText(SplashScreen.this, "Version not found", Toast.LENGTH_SHORT).show();

                                            final AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
                                            builder.setMessage("We found you are using old version of Jio Pos Plus\nClick 'ok' to update");
                                            builder.setCancelable(false);

                                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    String url = "https://play.google.com/store/apps/details?id=com.jio.jpp1";
                                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                                    i.setData(Uri.parse(url));
                                                    startActivity(i);
                                                }
                                            });

                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<List<VersionConstructor>> call, Throwable t) {
                                        Toast.makeText(SplashScreen.this, "Unable to connect", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            //-------------------------------For RR---------------------------------------------------
                            else if (isInstalledRR) {

                                Version_PlaceHolderAPI version_placeHolderAPI = APIConnection.getretrofitinstance().create(Version_PlaceHolderAPI.class);
                                Call<List<VersionConstructor>> call = version_placeHolderAPI.getPosts();

                                PackageInfo currentversioname = Utils.getversionname("com.ril.rposcentral", getPackageManager());
                                versioname = currentversioname.versionName;

                                call.enqueue(new Callback<List<VersionConstructor>>() {
                                    @Override
                                    public void onResponse(Call<List<VersionConstructor>> call, Response<List<VersionConstructor>> response) {

                                        List<VersionConstructor> posts = response.body();
                                        for (VersionConstructor post : posts) {
                                            content = post.getOutage();
                                            Log.d("Data:", content);
                                            versionlist.add(content);
                                        }
                                        Log.d("Data:", String.valueOf(versionlist));
                                        if (versionlist.contains(versioname)) {
                                            SharedPreferences getshared = getSharedPreferences("Data", MODE_PRIVATE);
                                            String nprm = getshared.getString("strprm", null);
                                            String nmob = getshared.getString("strmob", null);
                                            String nemail = getshared.getString("stremail", null);

                                            if (nprm != null && nmob != null && nemail != null) {
                                                Intent i = new Intent(SplashScreen.this, MainHomePage.class);
                                                startActivity(i);
                                                finish();
                                            } else {
                                                Intent i = new Intent(SplashScreen.this, Fragmentsliders.class);
                                                startActivity(i);
                                            }
                                        } else{

                                            final AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
                                            builder.setMessage("We found you are using old version of Jio Pos Plus\nKindly download latest version of Jio Pos Plus from Inteligent Hub");
                                            builder.setCancelable(false);

                                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    String url = "https://play.google.com/store/apps/details?id=com.airwatch.androidagent";
                                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                                    i.setData(Uri.parse(url));
                                                    startActivity(i);
                                                    finish();
                                                }
                                            });

                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<VersionConstructor>> call, Throwable t) {

                                    }
                                });
                            }
                            //-------------------------------------------------------------------------------------------------------
                            else {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
                                builder.setMessage("JIO POS Plus not Found!! Kindly select below options to download");
                                builder.setCancelable(false);

                                builder.setNegativeButton("GT", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String url = "https://play.google.com/store/apps/details?id=com.jio.jpp1";
                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(url));
                                        startActivity(i);
//                                    progressBar.setVisibility(View.GONE);
                                        finish();
                                    }
                                });

                                builder.setPositiveButton("RR", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        boolean isInstalledRR = Utils.isPackageInstalled("com.airwatch.androidagent", getPackageManager());

                                        if(isInstalledRR){
                                            Intent intent = getPackageManager().getLaunchIntentForPackage("com.airwatch.androidagent");
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            String url = "https://play.google.com/store/apps/details?id=com.airwatch.androidagent";
                                            Intent i = new Intent(Intent.ACTION_VIEW);
                                            i.setData(Uri.parse(url));
                                            startActivity(i);
                                            finish();
                                        }
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

//                                Intent i = new Intent(SplashScreen.this, MainHomePage.class);
//                                startActivity(i);
//                                finish();
                            }

                        }
                    }
                }, SPLASH_DISPLAY_LENGTH);
            }else {

                final AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
                builder.setMessage("No Internet connection");
                builder.setCancelable(false);

                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

}