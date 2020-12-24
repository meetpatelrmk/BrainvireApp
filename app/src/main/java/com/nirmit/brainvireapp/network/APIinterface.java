package com.nirmit.brainvireapp.network;

import com.nirmit.brainvireapp.model.ImageResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {

    @GET("photos/")
    Call<ArrayList<ImageResponse>> getAllImages();

}
