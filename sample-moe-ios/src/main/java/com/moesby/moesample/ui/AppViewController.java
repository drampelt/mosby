package com.moesby.moesample.ui;

import com.hannesdorfmann.mosby.mvp.MvpViewController;
import com.hannesdorfmann.mosby.sample.moe.common.models.Photo;
import com.hannesdorfmann.mosby.sample.moe.common.presenters.PhotoListPresenter;
import com.hannesdorfmann.mosby.sample.moe.common.views.PhotoListView;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.NInt;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.Class;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Property;
import com.intel.moe.natj.objc.ann.Selector;

import java.util.List;

import ios.NSObject;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSIndexPath;
import ios.uikit.UIButton;
import ios.uikit.UICollectionView;
import ios.uikit.UICollectionViewCell;
import ios.uikit.UICollectionViewFlowLayout;
import ios.uikit.UICollectionViewLayout;
import ios.uikit.UIColor;
import ios.uikit.UILabel;
import ios.uikit.UIScreen;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import ios.uikit.protocol.UICollectionViewDataSource;
import ios.uikit.protocol.UICollectionViewDelegateFlowLayout;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("AppViewController")
@RegisterOnStartup
public class AppViewController
        extends MvpViewController<PhotoListView, PhotoListPresenter>
        implements PhotoListView, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {

    @Owned
    @Selector("alloc")
    public static native AppViewController alloc();

    @Selector("init")
    public native AppViewController init();

    protected AppViewController(Pointer peer) {
        super(peer);
    }

    protected UICollectionView collectionView;
    protected List<Photo> photos = null;

    @Override
    public void viewDidLoad() {
        setView(UIView.alloc().initWithFrame(UIScreen.mainScreen().bounds()));

        UICollectionViewFlowLayout layout = UICollectionViewFlowLayout.alloc().init();
        collectionView = UICollectionView.alloc().initWithFrameCollectionViewLayout(view().frame(), layout);
        collectionView.setDataSource(this);
        collectionView.setDelegate(this);

        collectionView.registerClassForCellWithReuseIdentifier(Class.fromJavaClass(UICollectionViewCell.class), "cell");
        collectionView.setBackgroundColor(UIColor.redColor());

        view().addSubview(collectionView);

        presenter.loadPhotos();
    }

    @Override
    public PhotoListPresenter createPresenter() {
        return new PhotoListPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        collectionView.reloadData();
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public UICollectionViewCell collectionViewCellForItemAtIndexPath(UICollectionView uiCollectionView, NSIndexPath nsIndexPath) {
        UICollectionViewCell cell = uiCollectionView.dequeueReusableCellWithReuseIdentifierForIndexPath("cell", nsIndexPath);
        cell.setBackgroundColor(UIColor.greenColor());
        return cell;
    }

    @Override
    public long collectionViewNumberOfItemsInSection(UICollectionView uiCollectionView, @NInt long l) {
        return photos == null ? 0 : photos.size();
    }

    @Override
    public CGSize collectionViewLayoutSizeForItemAtIndexPath(UICollectionView collectionView, UICollectionViewLayout collectionViewLayout, NSIndexPath indexPath) {
        return new CGSize(50, 50);
    }
}
