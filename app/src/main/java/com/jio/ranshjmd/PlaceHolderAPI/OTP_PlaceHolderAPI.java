package com.jio.ranshjmd.PlaceHolderAPI;

import com.jio.ranshjmd.ApiConstructor.OTP_Constructor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OTP_PlaceHolderAPI {

    @POST("generateotp")
    Call<OTP_Constructor> postsendotp (@Body OTP_Constructor otp_constructor);

    @PUT("verifyotp/{mob}")
    Call<OTP_Constructor> putdataagainmob(@Path("mob") String mob,@Body OTP_Constructor otp_constructor);

    @PATCH("verifyotp/{mob}")
    Call<OTP_Constructor> patchdataagainmob(@Path("mob") String mob,@Body OTP_Constructor otp_constructor);

    @GET("validateotp/{mob}")
    Call<OTP_Constructor> validateotp (@Path("mob")String mob);

    @DELETE("deleteotp/{mob}")
    Call<OTP_Constructor> deleteotp (@Path("mob")String mob);

}
