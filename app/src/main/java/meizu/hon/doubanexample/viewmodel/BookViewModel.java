package meizu.hon.doubanexample.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.model.BookModel;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.viewmodel
 * @Date 2016/5/5 10:16
 * @Des
 */
public class BookViewModel extends ViewModel {

    private static Context mContext;
    private BookModel.BooksEntity mBookModel;

    public BookViewModel(Context context, BookModel.BooksEntity bookModel) {
        this.mContext = context;
        this.mBookModel = bookModel;
    }

    public void setBooks(BookModel.BooksEntity bookModel) {
        this.mBookModel = bookModel;
        notifyChange();
    }

    public String getImg() {
        return mBookModel.getImage();
    }

    public String getTitle() {
        return mBookModel.getTitle();
    }

    public String getAuthor() {
        return mBookModel.getAuthor().get(0);
    }

    public String getPublish() {
        return mBookModel.getPublisher();
    }

    public String getPages() {
        return mBookModel.getPages();
    }

    public String getPrice() {
        return mBookModel.getPrice();
    }

    public String getDesc() {
        return "作者: "+getAuthor()+"\n出版商: "+getPublish()+"\n页数: "+getPages()+"\n价格: "+getPrice();
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

    @Override public void destory() {

    }
}
