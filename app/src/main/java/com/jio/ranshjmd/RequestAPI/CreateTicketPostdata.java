package com.jio.ranshjmd.RequestAPI;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.jio.ranshjmd.APICallBack;
import com.jio.ranshjmd.ApiConstructor.Attachmentdatapatchconstructor;
import com.jio.ranshjmd.ApiConstructor.CreateTicketConstructor;
import com.jio.ranshjmd.ApiConstructor.StatusPatchNotification;
import com.jio.ranshjmd.Common.Datacontainer;
import com.jio.ranshjmd.PlaceHolderAPI.CreateTicket_PlaceHolderAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateTicketPostdata {

    public static Retrofit retrofit;
    public  static  String getid;


    public static void postdata( String rt_agent, String ry_assgroup, String rt_cbt, String rt_opent, String opeup, String rt_parttype, String mob, String prm, String rt_remark, String rt_status, String rt_title, String rt_srval,String rt_tflag, Activity activity,final APICallBack apiCallBack)
    {

        CreateTicket_PlaceHolderAPI sr_form_PlaceholderApi = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
        CreateTicketConstructor createTicketConstructor = new CreateTicketConstructor( rt_agent,ry_assgroup,rt_cbt,rt_opent,opeup,rt_parttype,mob,prm,rt_remark,rt_status,rt_title,rt_srval,rt_tflag);
        Call<CreateTicketConstructor> call = sr_form_PlaceholderApi.postticketdata(createTicketConstructor);
        call.enqueue(new Callback<CreateTicketConstructor>() {
            @Override
            public void onResponse(Call<CreateTicketConstructor> call, Response<CreateTicketConstructor> response) {
                if (response.isSuccessful()){
                    CreateTicketConstructor responsefromAPI = response.body();

                    String getid = responsefromAPI.getIntid();
                    Datacontainer.getintid=getid;
                    Log.d("bt1",Datacontainer.getintid );
                    apiCallBack.onResponse(true);

                }else{
                    apiCallBack.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<CreateTicketConstructor> call, Throwable t) {
                Toast.makeText(activity,"Data Added",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getticketid(String btnumber,Activity activity,final APICallBack callBack){

        CreateTicket_PlaceHolderAPI createTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
        Call<CreateTicketConstructor> call = createTicket_placeHolderAPI.getticketstatus(btnumber);

        call.enqueue(new Callback<CreateTicketConstructor>() {
            @Override
            public void onResponse(Call<CreateTicketConstructor> call, Response<CreateTicketConstructor> response) {
                callBack.onResponse(true);
                CreateTicketConstructor posts = response.body();

                String status = posts.getRt_status();
                Datacontainer.status=status;
                String title = posts.getRt_title();
                Datacontainer.title=title;
                String remarks = posts.getRt_remark();
                Datacontainer.remarks=remarks;
                Datacontainer.result="successfull";
                Toast.makeText(activity, "Successfull" +status+title+remarks, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CreateTicketConstructor> call, Throwable t) {

            }
        });

    }

    public static void postattachmentdata(String intid, String attachment, String datetime, Activity activity, final APICallBack apiCallBack)
    {

        CreateTicket_PlaceHolderAPI sr_form_PlaceholderApi = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
        CreateTicketConstructor createTicketConstructor = new CreateTicketConstructor( intid,attachment,datetime);
        Call<CreateTicketConstructor> call = sr_form_PlaceholderApi.postattachdata(createTicketConstructor);
        call.enqueue(new Callback<CreateTicketConstructor>() {
            @Override
            public void onResponse(Call<CreateTicketConstructor> call, Response<CreateTicketConstructor> response) {
                if (response.isSuccessful()){

                    Log.d("attachment",intid + datetime + attachment);
                    Toast.makeText(activity, "Attachment Added", Toast.LENGTH_SHORT).show();
                    apiCallBack.onResponse(true);
                }else{
                    apiCallBack.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<CreateTicketConstructor> call, Throwable t) {
                Toast.makeText(activity,"Data Added",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void postlogdata(String intid,String logs, String date_time,String agent_name, String status, Activity activity, final APICallBack apiCallBack)
    {

        CreateTicket_PlaceHolderAPI sr_form_PlaceholderApi = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
        CreateTicketConstructor createTicketConstructor = new CreateTicketConstructor( intid,date_time,logs,agent_name,status);
        Call<CreateTicketConstructor> call = sr_form_PlaceholderApi.postlogdata(createTicketConstructor);
        call.enqueue(new Callback<CreateTicketConstructor>() {
            @Override
            public void onResponse(Call<CreateTicketConstructor> call, Response<CreateTicketConstructor> response) {
                if (response.isSuccessful()){

                    Log.d("message",intid+logs+date_time+agent_name+status);
                    Toast.makeText(activity, "Data Added", Toast.LENGTH_SHORT).show();
                    apiCallBack.onResponse(true);
                }else{
                    apiCallBack.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<CreateTicketConstructor> call, Throwable t) {
                Toast.makeText(activity,"Data Added",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void addattachdata(String intid,String status, String remarks, Activity activity, final APICallBack apiCallBack){
        CreateTicket_PlaceHolderAPI sr_form_PlaceholderApi = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
        Attachmentdatapatchconstructor attachmentdatapatchconstructor = new Attachmentdatapatchconstructor(intid,status,remarks);
        Call<Attachmentdatapatchconstructor> call = sr_form_PlaceholderApi.patchattachmentdata(intid,attachmentdatapatchconstructor);
        call.enqueue(new Callback<Attachmentdatapatchconstructor>() {
            @Override
            public void onResponse(Call<Attachmentdatapatchconstructor> call, Response<Attachmentdatapatchconstructor> response) {
                if (response.isSuccessful()){
                    apiCallBack.onResponse(true);
                }else{
                    apiCallBack.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<Attachmentdatapatchconstructor> call, Throwable t) {
                Toast.makeText(activity,"Data not Added",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void patchstatusofnotification(String id, final APICallBack apiCallBack) {
        CreateTicket_PlaceHolderAPI createTicket_placeHolderAPI = APIConnection.getretrofitinstance().create(CreateTicket_PlaceHolderAPI.class);
        StatusPatchNotification statusPatchNotification = new StatusPatchNotification("read");
        Call<StatusPatchNotification> call = createTicket_placeHolderAPI.patchstatus_id(id,statusPatchNotification);
        call.enqueue(new Callback<StatusPatchNotification>() {
            @Override
            public void onResponse(Call<StatusPatchNotification> call, Response<StatusPatchNotification> response) {
                if (response.isSuccessful()){
                    apiCallBack.onResponse(true);
                }else{
                    apiCallBack.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<StatusPatchNotification> call, Throwable t) {

            }
        });

    }






}
