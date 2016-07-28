package com.hannesdorfmann.mosby.sample.moe.common.presenters;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.sample.moe.common.models.Photo;
import com.hannesdorfmann.mosby.sample.moe.common.services.PhotoService;
import com.hannesdorfmann.mosby.sample.moe.common.views.PhotoListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daniel on 16-07-28.
 */
public class PhotoListPresenter extends MvpBasePresenter<PhotoListView> {

    private PhotoService photoService;

    public PhotoListPresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        photoService = retrofit.create(PhotoService.class);
    }

    public void loadPhotos() {
        getView().showLoading();
        photoService.index(50).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    getView().setPhotos(response.body());
                } else {
                    getView().showError(new Exception("Something bad happened " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                getView().showError(t);
            }
        });
    }
}
