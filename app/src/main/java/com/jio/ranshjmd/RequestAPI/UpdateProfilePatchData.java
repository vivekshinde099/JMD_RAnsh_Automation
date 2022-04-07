package com.jio.ranshjmd.RequestAPI;

import android.app.Activity;

import com.jio.ranshjmd.APICallBack;
import com.jio.ranshjmd.ApiConstructor.UserProfileDataConstructor;
import com.jio.ranshjmd.PlaceHolderAPI.UserProfileData_PlaceHolderAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfilePatchData {

    public static void updateprofiledata (String name, String prm, String email, String mob, final APICallBack apiCallBack, Activity activity){
        UserProfileData_PlaceHolderAPI userProfileData_placeHolderAPI = APIConnection.getretrofitinstance().create(UserProfileData_PlaceHolderAPI.class);
        Call<UserProfileDataConstructor> call = userProfileData_placeHolderAPI.patchprofiledata(mob,name,prm,email);

        call.enqueue(new Callback<UserProfileDataConstructor>() {
            @Override
            public void onResponse(Call<UserProfileDataConstructor> call, Response<UserProfileDataConstructor> response) {

                apiCallBack.onResponse(true);

            }

            @Override
            public void onFailure(Call<UserProfileDataConstructor> call, Throwable t) {
                apiCallBack.onResponse(false);
            }
        });

    }
}
