package com.jio.ranshjmd.RequestAPI;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.jio.ranshjmd.ApiConstructor.OTP_Constructor;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.Notification.NotificationView;
import com.jio.ranshjmd.PlaceHolderAPI.OTP_PlaceHolderAPI;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.UserFormData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OTP_PostData {
    public static Retrofit retrofit;

    public static void postsendotp(String mob,Activity activity)
    {


        String mobile = "91"+mob;
        OTP_PlaceHolderAPI otp_placeHolderAPI = APIConnection.getretrofitinstance().create(OTP_PlaceHolderAPI.class);
        OTP_Constructor otp_constructor = new OTP_Constructor(mobile);

        Call<OTP_Constructor> call = otp_placeHolderAPI.postsendotp(otp_constructor);
        call.enqueue(new Callback<OTP_Constructor>() {
            @Override
            public void onResponse(Call<OTP_Constructor> call, Response<OTP_Constructor> response) {
                Toast.makeText(activity,"Succesfully Registered",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<OTP_Constructor> call, Throwable t) {
                Toast.makeText(activity,"Data not Added",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void validateotp(String mob,String code, Activity activity)
    {

        OTP_PlaceHolderAPI otp_placeHolderAPI = APIConnection.getretrofitinstance().create(OTP_PlaceHolderAPI.class);

        String mobile = "91"+mob;
        Call<OTP_Constructor> call = otp_placeHolderAPI.validateotp(mobile);
        call.enqueue(new Callback<OTP_Constructor>() {
            @Override
            public void onResponse(Call<OTP_Constructor> call, Response<OTP_Constructor> response) {

                OTP_Constructor responsefromAPI = response.body();
                String getvalidateotp = responsefromAPI.getS_otp();
                Datacontainer.sentotp = getvalidateotp;
                Log.d("sentotp",getvalidateotp);
                if (Datacontainer.sentotp.equals(code)){
                    OTP_PostData.deleteotp(mobile, activity);
                    Intent i = new Intent(activity, UserFormData.class);
                    i.putExtra("verifiedmob",mob);
                    activity.startActivity(i);
                    activity.finish();
                }else{
                    Toast.makeText(activity, "Invalid OTP. Enter valid OTP", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OTP_Constructor> call, Throwable t) {
                Toast.makeText(activity,"Data not added",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void deleteotp(String mob,Activity activity)
    {
        OTP_PlaceHolderAPI otp_placeHolderAPI = APIConnection.getretrofitinstance().create(OTP_PlaceHolderAPI.class);
        OTP_Constructor otp_constructor = new OTP_Constructor(mob);

        String mobile = "91"+mob;
        Call<OTP_Constructor> call = otp_placeHolderAPI.deleteotp(mobile);
        call.enqueue(new Callback<OTP_Constructor>() {
            @Override
            public void onResponse(Call<OTP_Constructor> call, Response<OTP_Constructor> response) {
            }

            @Override
            public void onFailure(Call<OTP_Constructor> call, Throwable t) {
                Toast.makeText(activity,"No Data found Against Number",Toast.LENGTH_LONG).show();
            }
        });
    }

    private static void addNotification(Activity activity) {

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(activity)
                            .setSmallIcon(R.drawable.ic_round_message_icon) //set icon for notification
                            .setContentTitle("R-Ansh") //set title of notification
                            .setContentText(Datacontainer.sentotp+" is your OTP for Registartion of R-Ansh")//this is notification message
                            .setAutoCancel(true) // makes auto cancel of notification
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification


            Intent notificationIntent = new Intent(activity, NotificationView.class);
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //notification message will get at NotificationView
            notificationIntent.putExtra("message", Datacontainer.sentotp+" is your OTP for Registartion of R-Ansh");

            PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            // Add as notification
            NotificationManager manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }

}



