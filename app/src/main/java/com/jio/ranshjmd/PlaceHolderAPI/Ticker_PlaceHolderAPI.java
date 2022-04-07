package com.jio.ranshjmd.PlaceHolderAPI;

import java.util.List;

import com.jio.ranshjmd.ApiConstructor.TickerConstructor;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Ticker_PlaceHolderAPI {
    @GET("tickerdata")
    Call<List<TickerConstructor>> getPosts();
}
