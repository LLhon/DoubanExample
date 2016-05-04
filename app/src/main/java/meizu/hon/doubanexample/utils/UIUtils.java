package meizu.hon.doubanexample.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import meizu.hon.doubanexample.MyApplication;
import meizu.hon.doubanexample.net.ApiService;


/**
 * @author liuhehong
 * @Project XiXiang_RentRoom_BuildManager
 * @Package com.zf.rentroom
 * @Date 2015/12/2 10:12
 * @description
 */
public class UIUtils {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static ApiService getService() {
        return MyApplication.getService();
    }

    public static long getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getString(int stringId) {
        return getContext().getResources().getString(stringId);
    }

    public static String[] getStringArray(int stringId) {
        return getContext().getResources().getStringArray(stringId);
    }

    public static int getColor(int colorId) {
        return getContext().getResources().getColor(colorId);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static void postTaskSafely(Runnable task) {
        //获取当前线程ID
        long currentThreadId = android.os.Process.myTid();
        //获取主线程ID
        long mainThreadId = getMainThreadId();
        //判断传送过来的是否在主线程中
        if (currentThreadId == mainThreadId) {
            task.run();
        }else {
            getHandler().post(task);
        }
    }

    public static void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int stringId) {
        Toast.makeText(getContext(), getString(stringId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 在子线程中安全的显示Toast
     * @param message
     */
    public static void showToastSafe(String message) {
        postTaskSafely(() -> showToast(message));
    }

    public static void showToastSafe(int stringId) {
        postTaskSafely(() -> showToast(stringId));
    }

    public static void showSnackbar(View view, int stringId) {
        Snackbar.make(view, getString(stringId), Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 获取当前应用程序的版本号
     *
     * @return
     */
    public static int getCurrentVersion() {
        PackageManager manager = getContext().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(UIUtils.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取设备IMEI
     *
     * @return
     */
    public static String getDeviceIMEI() {
        TelephonyManager manager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }

    public static String getPhoneNumber() {
        TelephonyManager manager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getLine1Number();
    }

    /**
     * 获取传入时间戳对应的日期
     * @param currentTimeMillis
     * @return
     */
    public static String getCurrentDate(long currentTimeMillis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(new Date(currentTimeMillis));
    }

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkReachable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        return info.isAvailable();
    }
}
