package meizu.hon.doubanexample.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import meizu.hon.doubanexample.MyApplication;
import meizu.hon.doubanexample.event.RxBus;
import meizu.hon.doubanexample.viewmodel.ViewModel;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author liuhehong
 * @project Xixiang_Unite_Receiver
 * @packageName com.zf.src.enforce.base
 * @date 2016/4/18 14:28
 * @des
 */
public abstract class BaseFragment<VM extends ViewModel, B extends ViewDataBinding> extends Fragment {

    protected View                  mContentView;
    protected MyApplication         mApp;
    private   CompositeSubscription mCompositeSubscription;

    private VM mViewModel;
    private B mBinding;

    protected int mPage = 1;
    protected int mSize = 10;

    protected RxBus _rxBus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = MyApplication.getInstance();
        _rxBus = RxBus.getSingleton();
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
            mContentView = mBinding.getRoot();
        }else {
            mBinding = DataBindingUtil.getBinding(mContentView);
        }
        initView();
        initListener();
        initData(savedInstanceState);
        return mBinding.getRoot();
    }

    @LayoutRes protected abstract int getLayoutRes();

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

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化监听器
     */
    protected void initListener() {

    }

    /**
     * 初始化数据, 处理业务逻辑
     */
    protected void init() {

    }

    protected void initData(Bundle savedInstanceState) {

    }

    /**
     * 显示加载框
     */
    public void showLoadingDialog() {
        if (getActivity() != null) {
            ((BaseActivity)getActivity()).showLoadingDialog();
        }
    }

    public void showLoadingDialog(String message) {
        if (getActivity() != null) {
            ((BaseActivity)getActivity()).showLoadingDialog(message);
        }
    }

    /**
     * 移除加载框
     */
    public void dismissLoadingDialog() {
//        if (getActivity() instanceof ToolBarActivity) {
//            ToolBarActivity activity = (ToolBarActivity) getActivity();
//            (activity).dismissLoadingDialog();
//        }
        if (getActivity() != null) {
            ((BaseActivity)getActivity()).dismissLoadingDialog();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }
}
