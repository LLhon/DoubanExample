package meizu.hon.doubanexample.net;

import java.util.Map;

import meizu.hon.doubanexample.model.BookModel;
import meizu.hon.doubanexample.model.MovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author liuhehong
 * @project FABHideAndShowDemo
 * @packageName meizu.hon.fabhideandshowdemo
 * @date 2016/4/25 17:16
 * @des
 */
public interface ApiService {

    //GET  https://api.douban.com/v2/book/search
    @GET("book/search")
    Call<BookModel> getBooks(@QueryMap Map<String, String> map);

    //movie/top250
    //movie/in_theaters
    @GET("movie/in_theaters")
    Call<MovieModel> getMovie(@QueryMap Map<String, Object> map);

}
