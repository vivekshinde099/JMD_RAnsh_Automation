package com.jio.ranshjmd.PlaceHolderAPI;

import java.util.List;

import com.jio.ranshjmd.ApiConstructor.AutoResTicketConstructor;
import com.jio.ranshjmd.ApiConstructor.AutoresListConstructor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AutoResTicket_PlaceHolderAPI {

    @GET("autoresticketdata")
    Call<List<AutoResTicketConstructor>> getPosts();

    @POST("autoresticketdata")
    Call<AutoResTicketConstructor> postticketdata (@Body AutoResTicketConstructor autoResTicketConstructor );

    @PUT("autoresticketdata/{intid}")
    Call<AutoResTicketConstructor> putdata (@Path ("intid") int intid ,@Body AutoResTicketConstructor autoResTicketConstructor );

    @PATCH("autoresticketdata/{intid}/{comment}/{dt}/{title}")
    Call<AutoResTicketConstructor> patchdata (@Path ("intid") int intid ,@Path ("comment") String comment,@Path ("dt") String dt,@Path ("title") String title );

    @GET("autoresticketdata/ticketdatabymob/{mob}")
    Call<List<AutoresListConstructor>> getautoresticketid(@Path("mob") String mob);

    @GET("autoresticketdata/{intid}")
    Call<AutoResTicketConstructor> ar_getticketstatusdata(@Path("intid") String intid);

    @PATCH("autoresticketdata/{intid}")
    Call<AutoResTicketConstructor> patchfeedbackdata (@Path ("intid") int intid ,@Body AutoResTicketConstructor autoResTicketConstructor );


}
