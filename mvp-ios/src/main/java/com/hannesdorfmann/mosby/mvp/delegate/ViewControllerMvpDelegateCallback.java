package com.hannesdorfmann.mosby.mvp.delegate;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by daniel on 16-07-28.
 */
public interface ViewControllerMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>>
    extends MvpDelegateCallback<V, P> {

}
