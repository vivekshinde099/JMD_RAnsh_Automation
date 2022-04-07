package com.jio.ranshjmd.RequestAPI;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.jio.ranshjmd.APICallBack;
import com.jio.ranshjmd.ApiConstructor.AutoResTicketConstructor;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.PlaceHolderAPI.AutoResTicket_PlaceHolderAPI;
import com.jio.ranshjmd.Ticketresults.Ticket_Resolved;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AutoResTicketPostData {

    public static Retrofit retrofit;

    public static void postdata(String ar_dt, String ar_eml, String ar_title, String mob, String ar_name, String ar_prm, String ar_status, String ar_fdcomm, String ar_fddt, String ar_fdtit, Activity activity,final APICallBack apiCallBack)
    {

        AutoResTicket_PlaceHolderAPI autoResTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(AutoResTicket_PlaceHolderAPI.class);

        AutoResTicketConstructor autoResTicketConstructor = new AutoResTicketConstructor(ar_dt,ar_eml,ar_title,mob, ar_name,ar_prm,ar_status,ar_fdcomm, ar_fddt, ar_fdtit);

        Call<AutoResTicketConstructor> call = autoResTicket_placeHolderAPI.postticketdata(autoResTicketConstructor);
        call.enqueue(new Callback<AutoResTicketConstructor>() {
            @Override
            public void onResponse(Call<AutoResTicketConstructor> call, Response<AutoResTicketConstructor> response) {

                if (response.isSuccessful()){
                    apiCallBack.onResponse(true);
                    AutoResTicketConstructor responsefromAPI = response.body();
                    String getid = responsefromAPI.getIntid();
                    Intent intent = new Intent(activity, Ticket_Resolved.class);
                    intent.putExtra("issuename", ar_title);
                    intent.putExtra("btnum", getid);
                    activity.startActivity(intent);
                    activity.finish();
                    Datacontainer.popupWindow1.dismiss();

                }else{
                    apiCallBack.onResponse(false);
                }

            }

            @Override
            public void onFailure(Call<AutoResTicketConstructor> call, Throwable t) {
                Toast.makeText(activity,"Failed for Response",Toast.LENGTH_LONG).show();
                apiCallBack.onResponse(false);
            }
        });
    }

    public static void getsingleticketdata(String comment,String Datetime,String title,Activity activity,int btnumber,final APICallBack apiCallBack){

        AutoResTicket_PlaceHolderAPI autoResTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(AutoResTicket_PlaceHolderAPI.class);

        Call<AutoResTicketConstructor> call = autoResTicket_placeHolderAPI.patchdata(btnumber,comment,Datetime,title);

        call.enqueue(new Callback<AutoResTicketConstructor>() {
            @Override
            public void onResponse(Call<AutoResTicketConstructor> call, Response<AutoResTicketConstructor> response) {

                if (response.isSuccessful()){
                   apiCallBack.onResponse(true);
                }else {
                    apiCallBack.onResponse(false);
                }
                Toast.makeText(activity, "Uploaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AutoResTicketConstructor> call, Throwable t) {

            }
        });



    }

}
