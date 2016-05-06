package meizu.hon.doubanexample.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.databinding.ActivityDetailBinding;
import meizu.hon.doubanexample.model.MovieModel;
import meizu.hon.doubanexample.ui.base.BaseActivity;
import meizu.hon.doubanexample.ui.fragment.MovieDetailFragment;
import meizu.hon.doubanexample.viewmodel.MovieDetailViewModel;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.ui.activity
 * @Date 2016/5/5 18:19
 * @Des
 */
public class MovieDetailActivity extends BaseActivity<MovieDetailViewModel, ActivityDetailBinding> {

    private MovieModel.SubjectsEntity mMovie;


    public static void startActivity(Context context, MovieModel.SubjectsEntity subjectsEntity) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("movie", subjectsEntity);
        context.startActivity(intent);
    }

    @Override protected int getLayoutRes() {
        return R.layout.activity_detail;
    }

    @Override protected void init() {
        super.init();
        mMovie = getIntent().getParcelableExtra("movie");
    }

    @Override protected void initView() {
        setSupportActionBar(getBinding().toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MovieDetailAdapter adapter = new MovieDetailAdapter(getSupportFragmentManager());
        adapter.addFragment(MovieDetailFragment.newInstance(mMovie.getCasts().get(0).getAlt()), "Neirong");
        adapter.addFragment(MovieDetailFragment.newInstance(mMovie.getDirectors().get(0).getName()), "Author");
        adapter.addFragment(MovieDetailFragment.newInstance(mMovie.getImages().getLarge()), "Mulu");
        getBinding().viewPager.setAdapter(adapter);

        getBinding().tabLayout.addTab(getBinding().tabLayout.newTab().setText("Neirong"));
        getBinding().tabLayout.addTab(getBinding().tabLayout.newTab().setText("Author"));
        getBinding().tabLayout.addTab(getBinding().tabLayout.newTab().setText("Mulu"));
        getBinding().tabLayout.setupWithViewPager(getBinding().viewPager);
    }

    @Override protected void initData() {
        super.initData();
        getBinding().setViewModel(new MovieDetailViewModel(this, mMovie));
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        getBinding().getViewModel().destory();
    }

    class MovieDetailAdapter extends FragmentPagerAdapter {

        List<Fragment> mFragments = new ArrayList<>();
        List<String> mTitles = new ArrayList<>();

        public MovieDetailAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        @Override public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override public int getCount() {
            return mFragments.size();
        }

        @Override public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
