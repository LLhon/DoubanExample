package meizu.hon.doubanexample.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.databinding.FragmentDetailBinding;
import meizu.hon.doubanexample.ui.base.BaseFragment;
import meizu.hon.doubanexample.viewmodel.ViewModel;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.ui.fragment
 * @date 2016/5/6 10:56
 * @des
 */
public class MovieDetailFragment extends BaseFragment<ViewModel, FragmentDetailBinding> {

    private String mInfo;

    public static Fragment newInstance(String info) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override protected int getLayoutRes() {
        return R.layout.fragment_detail;
    }

    @Override protected void init() {
        super.init();
        mInfo = getArguments().getString("info");
    }

    @Override protected void initView() {

    }

    @Override protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getBinding().setInfo(mInfo);
    }
}
