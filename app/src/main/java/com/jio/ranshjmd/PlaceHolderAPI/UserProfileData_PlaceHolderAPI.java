package com.jio.ranshjmd.PlaceHolderAPI;

import java.util.List;

import com.jio.ranshjmd.ApiConstructor.OTP_Constructor;
import com.jio.ranshjmd.ApiConstructor.UserProfileDataConstructor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserProfileData_PlaceHolderAPI {

    @GET("umdata")
    Call<List<UserProfileDataConstructor>> getandroidid();

    @POST("umdata")
    Call<UserProfileDataConstructor> postuserprofiledata (@Body UserProfileDataConstructor userProfileDataConstructor );

    @PATCH("umdata/{mob}")
    Call<UserProfileDataConstructor> updatedataagainmob(@Path("mob") String mob, @Body OTP_Constructor otp_constructor);

    @GET("umdata/uid/{umd_uid}")
    Call<UserProfileDataConstructor> getuid(@Path("umd_uid") String uid);

    @PATCH("umdata/{key}/{name}/{prm}/{email}")
    Call<UserProfileDataConstructor> patchprofiledata (@Path ("key") String key , @Path ("name") String name, @Path ("prm") String prm , @Path ("email") String email );

}
