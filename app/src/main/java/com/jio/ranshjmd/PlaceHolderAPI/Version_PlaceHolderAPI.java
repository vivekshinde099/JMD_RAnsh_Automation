package com.jio.ranshjmd.PlaceHolderAPI;

import java.util.List;

import com.jio.ranshjmd.ApiConstructor.VersionConstructor;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Version_PlaceHolderAPI {
    @GET("versions")
    Call<List<VersionConstructor>> getPosts();
}
