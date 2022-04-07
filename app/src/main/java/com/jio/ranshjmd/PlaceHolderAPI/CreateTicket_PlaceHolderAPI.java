package com.jio.ranshjmd.PlaceHolderAPI;

import java.util.List;

import com.jio.ranshjmd.ApiConstructor.Attachmentdatapatchconstructor;
import com.jio.ranshjmd.ApiConstructor.CreateTicketConstructor;
import com.jio.ranshjmd.ApiConstructor.StatusPatchNotification;
import com.jio.ranshjmd.ApiConstructor.StatusticketdataConstructor;
import com.jio.ranshjmd.ApiConstructor.TickerConstructor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CreateTicket_PlaceHolderAPI {

    @GET("ticketdata")
    Call<List<CreateTicketConstructor>> getPosts();

    @POST("ticketdata")
    Call<TickerConstructor> postticketdata (@Body TickerConstructor tickerConstructor);


    @POST("ticketdata")
    Call<CreateTicketConstructor> postticketdata(@Body CreateTicketConstructor createTicketConstructor);

    @POST("ticketdata/attachment")
    Call<CreateTicketConstructor> postattachdata(@Body CreateTicketConstructor createTicketConstructor);

    @POST("ticketdata/logs")
    Call<CreateTicketConstructor> postlogdata(@Body CreateTicketConstructor createTicketConstructor);

    @GET("ticketdata/{intid}")
    Call<CreateTicketConstructor> getticketstatus(@Path("intid") String intid);

    @PATCH("ticketdata/{intid}")
    Call<Attachmentdatapatchconstructor> patchattachmentdata(@Path("intid") String intid,@Body Attachmentdatapatchconstructor attachmentdatapatchconstructor);

    @GET("ticketdata/statusdata/{mob}")
    Call<List<StatusticketdataConstructor>> getstatusdatabymob(@Path("mob") String mob);

    @PATCH("ticketdata/statusdata/{id}")
    Call<StatusPatchNotification> patchstatus_id (@Path ("id") String id , @Body StatusPatchNotification statusPatchNotification );

}
