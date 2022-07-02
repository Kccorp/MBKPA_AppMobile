package com.lotte_app;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

public interface RideAPI {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("ride")
    //on below line we are creating a method to post our data.
    Call<RideModal> createDelete(@Body RideModal rideModal);
}

