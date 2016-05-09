package meizu.hon.doubanexample.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import meizu.hon.doubanexample.model.MusicModel;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.viewmodel
 * @date 2016/5/9 16:18
 * @des
 */
public class ItemMusicViewModel extends ViewModel {

    private Context mContext;
    private MusicModel.MusicsEntity mEntity;

    public ItemMusicViewModel(Context context, MusicModel.MusicsEntity entity) {
        this.mContext = context;
        this.mEntity = entity;
    }

    public void setEntity(MusicModel.MusicsEntity entity) {
        this.mEntity = entity;
    }

    public String getImg() {
        return mEntity.getImage();
    }

    public String getTitle() {
        return mEntity.getTitle();
    }

    public String getDesc() {
        return "歌手: " + mEntity.getAuthor().get(0).getName() + "\n发布日期: " + mEntity.getAttrs().getPubdate() +
                "\n类型: " + mEntity.getAttrs().getPublisher() + "\n推荐级别: " + mEntity.getRating().getAverage();
    }

    @BindingAdapter({"imgMusic", "placeHolder"})
    public static void loadImage(ImageView view, String url, Drawable drawable) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .crossFade()
                .placeholder(drawable)
                .into(view);
    }

    @Override public void destory() {

    }
}
