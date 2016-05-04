package meizu.hon.doubanexample.net;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import meizu.hon.doubanexample.conf.Constant;
import meizu.hon.doubanexample.utils.UIUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @Author liuhehong
 * @Project RxDemo
 * @PackageName com.zf.rxdemo.utils
 * @Date 2016/1/14 10:01
 * @Des Retrofit工厂类
 */
public class RetrofitFactory {

    private static Retrofit mRetrofit;

    public static ApiService createApi(final Context context) {
        if (mRetrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofit == null) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(Constant.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                            .readTimeout(Constant.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                            .cache(new Cache(context.getCacheDir(), Constant.CACHE_MAX_SIZE))
                            .addInterceptor((chain) -> {
                                Request request = chain.request();  //拦截Request
                                if (!UIUtils.isNetworkReachable(context)) {
                                    request = request.newBuilder()
                                            .cacheControl(CacheControl.FORCE_CACHE)  //无网络时只从缓存读取
                                            .build();
                                    UIUtils.showToastSafe("暂无网络");

                                }
                                Response response = chain.proceed(request);  //拦截Response
                                Logger.e(chain.request().toString()); // Request{method=POST, url=http://120.31.131.105:8012/WebService/DaDaTongService.asmx/Login, tag=null}
                                if (UIUtils.isNetworkReachable(context)) {
                                    int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
                                    response.newBuilder()
                                            .removeHeader("Pragma")  //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                            .header("Cache-Control", "public, max-age=" + maxAge)  //设置缓存超时时间
                                            .build();
                                } else {
                                    int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                                    response.newBuilder()
                                            .removeHeader("Pragma")
                                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                            .build();  //设置缓存策略，及超时策略
                                }
                                return response;
                            })
                            .build();
                    //http://gank.io/api/data/福利/10/10
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl("http://gank.io/api/")
                            .client(client)
//                            .addConverterFactory(StringConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
//                            .addConverterFactory(CustomGsonConverterFactory.create(MyApplication.getInstance().mGson))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit.create(ApiService.class);
    }
}
