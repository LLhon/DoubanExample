package meizu.hon.doubanexample;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orhanobut.logger.Logger;

import meizu.hon.doubanexample.net.ApiService;
import meizu.hon.doubanexample.net.RetrofitFactory;
import meizu.hon.doubanexample.utils.UIUtils;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample
 * @Date 2016/5/4 13:50
 * @Des
 */
public class MyApplication extends Application {

    public static MyApplication mApp;
    public static Context       mContext;
    public static ApiService    mService;
    public static Handler       mHandler;
    public static long          mMainThreadId;

    @Override public void onCreate() {
        super.onCreate();
        mApp = this;
        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        mService = RetrofitFactory.createApi(this);
        initData();
    }

    private void initData() {
        UIUtils.init(this);
        Logger.init();
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static MyApplication getInstance() {
        return mApp;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.mRefWatcher;
//    }

    public static ApiService getService() {
        return mService;
    }
}
