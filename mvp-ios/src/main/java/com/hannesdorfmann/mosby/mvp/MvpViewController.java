package com.hannesdorfmann.mosby.mvp;

import com.hannesdorfmann.mosby.mvp.delegate.ViewControllerMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ViewControllerMvpDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ViewControllerMvpDelegateImpl;
import com.intel.moe.natj.general.Pointer;

import ios.uikit.UIViewController;

/**
 * Created by daniel on 16-07-28.
 */
public abstract class MvpViewController<V extends MvpView, P extends MvpPresenter<V>>
        extends UIViewController implements ViewControllerMvpDelegateCallback<V, P>, MvpView {

    protected ViewControllerMvpDelegate<V, P> mvpDelegate;
    protected P presenter;

    protected MvpViewController(Pointer peer) {
        super(peer);
    }

    //region UIViewController Lifecycle

    @Override
    public void loadView() {
        super.loadView();
        getMvpDelegate().loadView();
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        getMvpDelegate().viewDidLoad();
    }

    @Override
    public void viewWillAppear(boolean b) {
        super.viewWillAppear(b);
        getMvpDelegate().viewWillAppear();
    }

    @Override
    public void viewDidAppear(boolean b) {
        super.viewDidAppear(b);
        getMvpDelegate().viewDidAppear();
    }

    @Override
    public void viewWillDisappear(boolean b) {
        super.viewWillDisappear(b);
        getMvpDelegate().viewWillDisappear();
    }

    @Override
    public void viewDidDisappear(boolean b) {
        super.viewDidDisappear(b);
        getMvpDelegate().viewDidDisappear();
    }

    //endregion

    //region Presenter

    public abstract P createPresenter();

    protected ViewControllerMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ViewControllerMvpDelegateImpl<>(this);
        }
        return mvpDelegate;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    //endregion
}
