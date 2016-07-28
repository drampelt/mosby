package com.hannesdorfmann.mosby.mvp.delegate;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by daniel on 16-07-28.
 */
public interface ViewControllerMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    public void loadView();

    public void viewDidLoad();

    public void viewWillAppear();

    public void viewDidAppear();

    public void viewWillDisappear();

    public void viewDidDisappear();

}
