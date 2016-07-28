package com.hannesdorfmann.mosby.sample.moe.common.services;

import com.hannesdorfmann.mosby.sample.moe.common.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by daniel on 16-07-28.
 */
public interface PhotoService {
    @GET("/photos")
    Call<List<Photo>> index(@Query("_limit") int limit);
}
