package com.hannesdorfmann.mosby.mvp.delegate;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by daniel on 16-07-28.
 */
public class ViewControllerMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
    implements ViewControllerMvpDelegate<V, P> {

    protected MvpInternalDelegate<V, P> internalDelegate;
    protected ViewControllerMvpDelegateCallback<V, P> delegateCallback;

    public ViewControllerMvpDelegateImpl(ViewControllerMvpDelegateCallback<V, P> delegateCallback) {
        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }
        this.delegateCallback = delegateCallback;
    }

    protected MvpInternalDelegate<V, P> getInternalDelegate() {
        if (internalDelegate == null) {
            internalDelegate = new MvpInternalDelegate<>(delegateCallback);
        }
        return internalDelegate;
    }

    @Override
    public void loadView() {
        getInternalDelegate().createPresenter();
        getInternalDelegate().attachView();
    }

    @Override
    public void viewDidLoad() {

    }

    @Override
    public void viewWillAppear() {

    }

    @Override
    public void viewDidAppear() {

    }

    @Override
    public void viewWillDisappear() {

    }

    @Override
    public void viewDidDisappear() {
        getInternalDelegate().detachView();
    }
}
