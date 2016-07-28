package com.hannesdorfmann.mosby.sample.moe.common.views;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.sample.moe.common.models.Photo;

import java.util.List;

/**
 * Created by daniel on 16-07-28.
 */
public interface PhotoListView extends MvpView {
    void showLoading();
    void setPhotos(List<Photo> photos);
    void showError(Throwable throwable);
}
