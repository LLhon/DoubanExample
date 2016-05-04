package meizu.hon.doubanexample.agera;

import android.support.annotation.NonNull;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import java.util.List;

import meizu.hon.doubanexample.model.BookModel;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.agera
 * @Date 2016/5/4 18:01
 * @Des Supplier: 提供数据的接口, 通过泛型指定数据类型, 通过get()方法获取数据.
 */
public class BookSupplier implements Supplier<Result<List<BookModel>>> {

    private String mKey;

    public BookSupplier(String key) {
        this.mKey = key;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    @NonNull @Override public Result<List<BookModel>> get() {
        return null;
    }
}
