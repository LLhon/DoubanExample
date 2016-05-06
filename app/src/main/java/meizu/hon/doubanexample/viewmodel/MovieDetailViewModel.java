package meizu.hon.doubanexample.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.model.MovieModel;
import meizu.hon.doubanexample.ui.base.BaseActivity;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.viewmodel
 * @date 2016/5/6 10:38
 * @des
 */
public class MovieDetailViewModel extends ViewModel {

    private MovieModel.SubjectsEntity mMovie;
    public static Context mContext;

    public MovieDetailViewModel(Context context, MovieModel.SubjectsEntity subjectsEntity) {
        this.mContext = context;
        this.mMovie = subjectsEntity;
    }

    public void setMovie(MovieModel.SubjectsEntity subjectsEntity) {
        this.mMovie = subjectsEntity;
    }

    public String getImage() {
        return mMovie.getImages().getLarge();
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    @BindingAdapter({"titleImg"})
    public static void loadImage(ImageView view, String url) {
        Logger.e("titleImg:" + url);
        Glide.with(mContext)
                .load(url)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    @BindingAdapter({"title"})
    public static void setTitle(CollapsingToolbarLayout layout, String title) {
        layout.setTitle(title);
    }

    @BindingAdapter({"onNavigationClick"})
    public static void navigationClick(Toolbar toolbar, MovieDetailViewModel viewModel) {
        toolbar.setNavigationOnClickListener(v -> ((BaseActivity)mContext).finish());
    }

    @Override public void destory() {

    }
}
