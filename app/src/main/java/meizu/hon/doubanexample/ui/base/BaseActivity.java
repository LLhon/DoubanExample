package meizu.hon.doubanexample.ui.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.event.RxBus;
import meizu.hon.doubanexample.viewmodel.ViewModel;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author liuhehong
 * @project Xixiang_Unite_Receiver
 * @packageName com.zf.src.enforce.base
 * @date 2016/4/18 10:58
 * @des Activity基类
 */
public abstract class BaseActivity<VM extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {

    private   ProgressDialog       mProgressDialog;
    private String mProgressMessage = "正在加载中.....";

    private CompositeSubscription mCompositeSubscription;
    private VM mViewModel;
    private B mBinding;
    protected RxBus _rxBus;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _rxBus = RxBus.getSingleton();
        setBinding(DataBindingUtil.setContentView(this, getLayoutRes()));
        init();
        initView();
        initData();
    }

    @LayoutRes protected abstract int getLayoutRes();

    /**
     * 初始化
     */
    protected void init() {

    }

    protected abstract void initView();

    protected void initData() {

    }

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setContentInsetsRelative(0, 0);
        toolbar.showOverflowMenu();
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setTitle();
    }

    protected void setTitle() {

    }

    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment, tag).commit();
    }

    public <T extends Fragment>T getFragment(String tag) {
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }

    protected void setViewModel(@NonNull VM viewModel) {
        this.mViewModel = viewModel;
    }

    protected VM getViewModel() {
        if (mViewModel == null) {
            throw new NullPointerException("You should setViewModel first!");
        }
        return mViewModel;
    }

    protected void setBinding(@NonNull B binding) {
        this.mBinding = binding;
    }

    protected B getBinding() {
        if (mBinding == null) {
            throw new NullPointerException("You should setBinding first!");
        }
        return mBinding;
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void showLoadingDialog() {
        showLoadingDialog(mProgressMessage);
    }

    public void showLoadingDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public void dismissLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
