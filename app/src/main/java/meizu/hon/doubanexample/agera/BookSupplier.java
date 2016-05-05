package meizu.hon.doubanexample.agera;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meizu.hon.doubanexample.model.BookModel;
import meizu.hon.doubanexample.net.ApiService;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.agera
 * @Date 2016/5/4 18:01
 * @Des Supplier: 提供数据的接口, 通过泛型指定数据类型, 通过get()方法获取数据.
 */
public class BookSupplier implements Supplier<Result<List<BookModel.BooksEntity>>> {

    private String mKey;
    private static final String URL = "https://api.douban.com/v2/";
    OkHttpClient mOkHttpClient = new OkHttpClient();

    public BookSupplier(String key) {
        this.mKey = key;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    private String getKey() {
        return mKey;
    }

    private String getSearchUrl() {
        return URL + "book/search";
    }

    private List<BookModel.BooksEntity> getBooks() {
        HttpUrl url = HttpUrl.parse(getSearchUrl())
                .newBuilder()
                .addQueryParameter("q", getKey())
                .addQueryParameter("start", "0")
                .addQueryParameter("count", "1")
                .build();
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = mOkHttpClient.newCall(request).execute();
            JSONObject object = new JSONObject(response.body().string());
            JSONArray  array  = object.optJSONArray("books");

            Log.e("-------Json------", array.toString());
            Logger.e(response.body().string());

            BookModel  books  = new Gson().fromJson(response.body().string(), new TypeToken<BookModel>(){}.getType());
            return books.getBooks();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<BookModel.BooksEntity> getBooksByRetrofit() {
        Map<String, String> map = new HashMap<>();
        map.put("q", getKey());
        map.put("start", "0");
        map.put("count", "50");
        Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
        ApiService service = retrofit.create(ApiService.class);
        try {
            retrofit2.Response<BookModel> response = service.getBooks(map).execute();
            return response.body().getBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull @Override public Result<List<BookModel.BooksEntity>> get() {
        List<BookModel.BooksEntity> books = getBooks();
//        List<BookModel> books = getBooksByRetrofit();
        Log.e("TAG", "---------------------------------");
        if (books == null) {
            return Result.failure();
        }else {
            return Result.success(books);
        }
    }
}
