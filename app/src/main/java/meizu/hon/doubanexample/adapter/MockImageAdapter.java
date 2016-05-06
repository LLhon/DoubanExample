package meizu.hon.doubanexample.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.adapter
 * @date 2016/5/6 15:48
 * @des
 */
public class MockImageAdapter extends ImageAdapter {

    @BindingAdapter(value = {"imgUrl", "placeHolder"}, requireAll = false)
    public void loadImage(ImageView view, String url, int placeHolder) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .crossFade()
                .placeholder(placeHolder)
                .into(view);
    }

    class ImageComponent implements android.databinding.DataBindingComponent {

        MockImageAdapter mMockImageAdapter = new MockImageAdapter();

        @Override public MockImageAdapter getMockImageAdapter() {
            return mMockImageAdapter;
        }
    }
}
