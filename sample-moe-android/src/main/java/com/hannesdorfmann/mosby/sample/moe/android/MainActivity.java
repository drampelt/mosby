package com.hannesdorfmann.mosby.sample.moe.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.sample.moe.common.models.Photo;
import com.hannesdorfmann.mosby.sample.moe.common.presenters.PhotoListPresenter;
import com.hannesdorfmann.mosby.sample.moe.common.views.PhotoListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<PhotoListView, PhotoListPresenter> implements PhotoListView {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.loading)
    ProgressBar loading;

    @Bind(R.id.error)
    TextView error;

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new Adapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        presenter.loadPhotos();
    }

    @NonNull
    @Override
    public PhotoListPresenter createPresenter() {
        return new PhotoListPresenter();
    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        recyclerView.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        error.setVisibility(View.GONE);

        adapter.setPhotos(photos);
    }

    @Override
    public void showError(Throwable throwable) {
        recyclerView.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.PhotoHolder> {

        private List<Photo> photos;

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return photos == null ? 0 : photos.size();
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item_photo, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            holder.bind(photos.get(position));
        }

        class PhotoHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.image_view)
            ImageView imageView;

            @Bind(R.id.title)
            TextView title;

            public PhotoHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void bind(Photo photo) {
                title.setText(photo.getTitle());
                Picasso.with(MainActivity.this)
                        .load(photo.getUrl())
                        .fit()
                        .into(imageView);
            }
        }
    }
}
