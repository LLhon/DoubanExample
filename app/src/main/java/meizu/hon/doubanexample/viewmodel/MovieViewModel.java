package meizu.hon.doubanexample.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.model.MovieModel;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.viewmodel
 * @Date 2016/5/5 16:25
 * @Des
 */
public class MovieViewModel extends ViewModel {

    private static Context mContext;
    private MovieModel.SubjectsEntity mSubjectsEntity;

    public MovieViewModel(Context context, MovieModel.SubjectsEntity entity) {
        this.mContext = context;
        this.mSubjectsEntity = entity;
    }

    public void setMovie(MovieModel.SubjectsEntity entity) {
        this.mSubjectsEntity = entity;
        notifyChange();
    }

    public String getTitle() {
        Log.e("title:", mSubjectsEntity.getTitle());
        return mSubjectsEntity.getTitle();
    }

    public String getImg() {
        return mSubjectsEntity.getImages().getMedium();
    }

    public String getDesc() {
        return "演员: " + mSubjectsEntity.getDirectors().get(0).getName() + "\n上映日期: " + mSubjectsEntity.getYear()
                + "\n收藏人数: " + mSubjectsEntity.getCollect_count() + "\n标签: " + listToString(mSubjectsEntity.getGenres());
    }

    private String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            }else {
                sb.append(list.get(i) + ", ");
            }
        }
        return sb.toString();
    }

    @BindingAdapter({"bind:imgUrl"})
    public static void loadImage(ImageView view, String imgUrl) {
        Glide.with(mContext)
                .load(imgUrl)
                .centerCrop()
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }

    @Override void destory() {

    }
}
