package com.jio.ranshjmd.RequestAPI;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;

import com.jio.ranshjmd.APICallBack;
import com.jio.ranshjmd.ApiConstructor.UserProfileDataConstructor;
import com.jio.ranshjmd.Bottomnavigationbar.MainHomePage;
import com.jio.ranshjmd.PlaceHolderAPI.UserProfileData_PlaceHolderAPI;
import com.mdminfo.mdmsdk.MDMSDK;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileDataPostData {

    public static String mprm,memail,mmob,mname;
    public static String content;
    public static String getuid;
    public static ArrayList<String> uidlist;

    public static void postupdata(String umd_key, String umd_circ, String umd_dt, String umd_eml, String umd_mob, String umd_mmm, String umd_name, String umd_pos, String umd_prm, String umd_udi, String umd_zone, Activity activity)
    {

        String umd_category = "JMD";
        UserProfileData_PlaceHolderAPI userProfileData_placeHolderAPI = APIConnection.getretrofitinstance().create(UserProfileData_PlaceHolderAPI.class);
        UserProfileDataConstructor userProfileDataConstructor = new UserProfileDataConstructor( umd_key, umd_circ, umd_dt, umd_eml,  umd_mob, umd_mmm,  umd_name,  umd_pos,  umd_prm, umd_udi, umd_zone,umd_category);

        Call<UserProfileDataConstructor> call = userProfileData_placeHolderAPI.postuserprofiledata(userProfileDataConstructor);
        call.enqueue(new Callback<UserProfileDataConstructor>() {
            @Override
            public void onResponse(Call<UserProfileDataConstructor> call, Response<UserProfileDataConstructor> response) {
                Toast.makeText(activity,"Data Added",Toast.LENGTH_LONG).show();

                UserProfileDataConstructor responsefromAPI = response.body();
                String getmob = responsefromAPI.getUmd_mob();
                String getprm = responsefromAPI.getUmd_prm();
                String getemail = responsefromAPI.getUmd_eml();
                String getname = responsefromAPI.getUmd_name();

                SharedPreferences sharedPreferences = activity.getSharedPreferences("Data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("strprm", getprm);
                editor.putString("strmob", getmob);
                editor.putString("stremail", getemail);
                editor.putString("strname", getname);

                editor.apply();

                Intent i = new Intent(activity, MainHomePage.class);
                i.putExtra("mob",getmob);
                i.putExtra("prm",getprm);
                i.putExtra("email",getemail);
                i.putExtra("name",getname);
                activity.startActivity(i);
                activity.finish();

            }

            @Override
            public void onFailure(Call<UserProfileDataConstructor> call, Throwable t) {
                Toast.makeText(activity,"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void checkandroidid (final APICallBack apiCallBack, Activity activity, Context context){

        String getIMEI = MDMSDK.imeiNumber(context);
        UserProfileData_PlaceHolderAPI userProfileData_placeHolderAPI = APIConnection.getretrofitinstance().create(UserProfileData_PlaceHolderAPI.class);
        Call<UserProfileDataConstructor> call = userProfileData_placeHolderAPI.getuid(getIMEI);
        call.enqueue(new Callback<UserProfileDataConstructor>() {
            @Override
            public void onResponse(Call<UserProfileDataConstructor> call, Response<UserProfileDataConstructor> response) {

                if (!response.isSuccessful()) {
                    apiCallBack.onResponse(false);
                } else {
                    UserProfileDataConstructor responsefromAPI = response.body();
                    String getmob = responsefromAPI.getUmd_mob();
                    String getprm = responsefromAPI.getUmd_prm();
                    String getemail = responsefromAPI.getUmd_eml();
                    String getname = responsefromAPI.getUmd_name();

                    SharedPreferences sharedPreferences = activity.getSharedPreferences("Data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("strprm", getprm);
                    editor.putString("strmob", getmob);
                    editor.putString("stremail", getemail);
                    editor.putString("strname", getname);

                    editor.apply();

                    Intent i = new Intent(activity, MainHomePage.class);
                    i.putExtra("mob", getmob);
                    i.putExtra("prm", getprm);
                    i.putExtra("email", getemail);
                    i.putExtra("name", getname);
                    activity.startActivity(i);
                    activity.finish();
                }
            }

            @Override
            public void onFailure(Call<UserProfileDataConstructor> call, Throwable t) {

                Toast.makeText(activity, "No data found", Toast.LENGTH_SHORT).show();
            }
        });
    }






}
