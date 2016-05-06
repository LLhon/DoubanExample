package meizu.hon.doubanexample.ui.fragment;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.databinding.FragmentGithubBinding;
import meizu.hon.doubanexample.ui.base.BaseFragment;
import meizu.hon.doubanexample.viewmodel.ViewModel;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.ui.fragment
 * @Date 2016/5/4 15:15
 * @Des
 */
public class GithubFragment extends BaseFragment<ViewModel, FragmentGithubBinding> {

    @Override protected int getLayoutRes() {
        return R.layout.fragment_github;
    }

    @Override protected void initView() {
        getBinding().pwb.getSettings().setJavaScriptEnabled(true);
        getBinding().pwb.loadUrl("https://github.com/LLhon");
    }
}
