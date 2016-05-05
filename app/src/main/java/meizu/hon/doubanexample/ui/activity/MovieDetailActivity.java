package meizu.hon.doubanexample.ui.activity;

import android.content.Context;
import android.content.Intent;

import meizu.hon.doubanexample.ui.base.BaseActivity;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.ui.activity
 * @Date 2016/5/5 18:19
 * @Des
 */
public class MovieDetailActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        context.startActivity(intent);
    }

    @Override protected int getLayoutRes() {
        return 0;
    }

    @Override protected void initView() {

    }
}
