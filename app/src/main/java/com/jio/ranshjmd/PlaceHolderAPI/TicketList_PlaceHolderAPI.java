package com.jio.ranshjmd.PlaceHolderAPI;

import java.util.List;

import com.jio.ranshjmd.ApiConstructor.TicketListConstructor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TicketList_PlaceHolderAPI {
    @GET("ticketdata")
    Call<List<TicketListConstructor>> getPosts();

    @GET("ticketdata/ticketdatabymob/{mob}")
    Call<List<TicketListConstructor>> getticketid(@Path("mob") String mob);
}
