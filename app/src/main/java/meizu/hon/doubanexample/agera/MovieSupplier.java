package meizu.hon.doubanexample.agera;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meizu.hon.doubanexample.model.MovieModel;
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
 * @Date 2016/5/5 15:10
 * @Des
 */
public class MovieSupplier implements Supplier<Result<List<MovieModel.SubjectsEntity>>> {

    private static final String URL = "https://api.douban.com/v2/";
    OkHttpClient mOkHttpClient = new OkHttpClient();

    private String getMovieUrl() {
        return URL + "movie/top250";
    }

    private List<MovieModel.SubjectsEntity> getMovie() {
        HttpUrl url = HttpUrl.parse(getMovieUrl())
                .newBuilder()
                .addQueryParameter("start", "0")
                .addQueryParameter("count", "1")
                .build();
        try {
            Request  request = new Request.Builder().url(url).build();
            Response response = mOkHttpClient.newCall(request).execute();
            Log.e("--------Json------", response.body().string());
            MovieModel movieModel = new Gson().fromJson(response.body().string(), new TypeToken<MovieModel>(){}.getType());
            return movieModel.getSubjects();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<MovieModel.SubjectsEntity> getMovieByRetrofit() {
        Map<String, Object> map = new HashMap<>();
        map.put("start", 0);
        map.put("count", 50);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        try {
            retrofit2.Response<MovieModel> response = service.getMovie(map).execute();
            Logger.e("----Json----" + new Gson().toJson(response.body()));
            return response.body().getSubjects();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull @Override public Result<List<MovieModel.SubjectsEntity>> get() {
        List<MovieModel.SubjectsEntity> subjectsEntities = getMovieByRetrofit();
//        getMovie();
        if (subjectsEntities == null) {
            return Result.failure();
        }else {
            return Result.success(subjectsEntities);
        }
    }
}
