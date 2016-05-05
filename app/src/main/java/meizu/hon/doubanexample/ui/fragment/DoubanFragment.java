package meizu.hon.doubanexample.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.agera.BaseObservable;
import com.google.android.agera.Function;
import com.google.android.agera.Receiver;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.google.android.agera.rvadapter.RepositoryAdapter;
import com.google.android.agera.rvadapter.RepositoryPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.agera.BookSupplier;
import meizu.hon.doubanexample.agera.MovieSupplier;
import meizu.hon.doubanexample.databinding.FragmentDoubanBinding;
import meizu.hon.doubanexample.databinding.ItemBooksBinding;
import meizu.hon.doubanexample.databinding.ItemMovieBinding;
import meizu.hon.doubanexample.model.BookModel;
import meizu.hon.doubanexample.model.MovieModel;
import meizu.hon.doubanexample.ui.activity.MovieDetailActivity;
import meizu.hon.doubanexample.ui.base.BaseFragment;
import meizu.hon.doubanexample.utils.DensityUtils;
import meizu.hon.doubanexample.utils.UIUtils;
import meizu.hon.doubanexample.viewmodel.BookViewModel;
import meizu.hon.doubanexample.viewmodel.MovieViewModel;
import meizu.hon.doubanexample.viewmodel.ViewModel;
import meizu.hon.doubanexample.widget.DividerItemDecoration;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.ui.fragment
 * @Date 2016/5/4 15:13
 * @Des
 */
public class DoubanFragment extends BaseFragment<ViewModel, FragmentDoubanBinding> implements Updatable {

    public static BaseFragment mFragment;
    private String[] tabs = {"Book", "Movie", "Music", "Photos"};
    private ExecutorService mNetworkExecutor;
    private SearchObservable mSearchObservable;
    private BookSupplier mBookSupplier;
    private Repository<List<BookModel.BooksEntity>> mBookRepository;
    private Repository<List<MovieModel.SubjectsEntity>> mMovieRepository;
    private RepositoryAdapter mBookAdapter;
    private RepositoryAdapter mMovieAdapter;
    private static final List<BookModel.BooksEntity> INITIAL_VALUE = Collections.emptyList();
    private static final List<MovieModel.SubjectsEntity> INITIAL_MOVIE = Collections.emptyList();
    private MovieSupplier mMovieSupplier;

    public static BaseFragment newInstance() {
        if (mFragment == null) {
            mFragment = new DoubanFragment();
        }
        return mFragment;
    }

    @Override protected int getLayoutRes() {
        return R.layout.fragment_douban;
    }

    @Override protected void initView() {
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        itemDecoration.setSize(DensityUtils.dp2px(getActivity(), 12));
        getBinding().recyclerView.addItemDecoration(itemDecoration);
    }

    @Override protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        setupRepository();
        setupMovieRepository();
    }

    @Override public void update() {
        getBinding().mpb.setVisibility(View.INVISIBLE);
        Log.e("update:", mMovieRepository.get().get(0).getTitle());
    }

    class SearchObservable extends BaseObservable {
        public void doSearch(String key) {
            mBookSupplier.setKey(key);
            dispatchUpdate();
        }
    }

    class BookReceiver implements Receiver<List<BookModel>> {

        @Override public void accept(@NonNull List<BookModel> value) {

        }
    }

    private void setupRepository() {
        mNetworkExecutor = Executors.newSingleThreadExecutor();
        mSearchObservable = new SearchObservable();
        mBookSupplier = new BookSupplier(UIUtils.getString(R.string.default_search_keyword));
        mBookRepository = Repositories.repositoryWithInitialValue(INITIAL_VALUE)
                .observe(mSearchObservable)
                .onUpdatesPerLoop()
                .goTo(mNetworkExecutor)
                .getFrom(mBookSupplier)
//                .transform(new BookTransform())
                .thenTransform(new BookTransform())
                .compile();
        mBookAdapter = RepositoryAdapter.repositoryAdapter()
                .add(mBookRepository, new BookPresenter())
                .build();
        getBinding().recyclerView.setAdapter(mBookAdapter);
    }

    private void setupMovieRepository() {
        mNetworkExecutor = Executors.newSingleThreadExecutor();
        mSearchObservable = new SearchObservable();
        mMovieSupplier = new MovieSupplier();
        mMovieRepository = Repositories.repositoryWithInitialValue(INITIAL_MOVIE)
                .observe()
                .onUpdatesPerLoop()
                .goTo(mNetworkExecutor)
                .getFrom(mMovieSupplier)
//                .transform(new BookTransform())
                .thenTransform(new MovieTransform())
                .compile();
        mMovieAdapter = RepositoryAdapter.repositoryAdapter()
                .add(mMovieRepository, new MoviePresenter())
                .build();
        getBinding().recyclerView.setAdapter(mMovieAdapter);
    }

    private void doSearch(String key) {
        getBinding().mpb.setVisibility(View.VISIBLE);
        mSearchObservable.doSearch(key);
    }

    class BookTransform implements Function<Result<List<BookModel.BooksEntity>>, List<BookModel.BooksEntity>> {
        @NonNull @Override public List<BookModel.BooksEntity> apply(@NonNull Result<List<BookModel.BooksEntity>> input) {
            return input.get();
        }
    }

    class MovieTransform implements Function<Result<List<MovieModel.SubjectsEntity>>, List<MovieModel.SubjectsEntity>> {
        @NonNull @Override public List<MovieModel.SubjectsEntity> apply(@NonNull Result<List<MovieModel.SubjectsEntity>> input) {
            return input.get();
        }
    }

    class BookPresenter extends RepositoryPresenter<List<BookModel.BooksEntity>> {

        private ItemBooksBinding mBinding;
        private View mItemView;
        private List<BookModel> mBookModelList = new ArrayList<>();

        @Override public int getLayoutResId(@NonNull List<BookModel.BooksEntity> data, int index) {
            return R.layout.item_books;
        }

        @Override public int getItemCount(@NonNull List<BookModel.BooksEntity> data) {
            return data.size();
        }

        @Override public void bind(@NonNull List<BookModel.BooksEntity> data, int index, @NonNull RecyclerView.ViewHolder holder) {
            mItemView = holder.itemView;
            mBinding = DataBindingUtil.bind(mItemView);
            bindBooks(data.get(index));
        }

        void bindBooks(BookModel.BooksEntity bookModel) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new BookViewModel(mItemView.getContext(), bookModel));
            }else {
                mBinding.getViewModel().setBooks(bookModel);
            }
        }
    }

    class MoviePresenter extends RepositoryPresenter<List<MovieModel.SubjectsEntity>> {

        private View mItemView;
        private ItemMovieBinding mBinding;

        @Override public int getLayoutResId(@NonNull List<MovieModel.SubjectsEntity> data, int index) {
            return R.layout.item_movie;
        }

        @Override public int getItemCount(@NonNull List<MovieModel.SubjectsEntity> data) {
            return data.size();
        }

        @Override public void bind(@NonNull List<MovieModel.SubjectsEntity> data, int index, @NonNull RecyclerView.ViewHolder holder) {
            Logger.e("bind()");
            mItemView = holder.itemView;
            mBinding = DataBindingUtil.bind(mItemView);
            bindMovie(data.get(index));
            bindClick();
        }

        void bindMovie(MovieModel.SubjectsEntity subjectsEntity) {
            if (mBinding.getViewModel() == null) {
                Log.e("tag", "1");
                mBinding.setViewModel(new MovieViewModel(mItemView.getContext(), subjectsEntity));
            }else {
                Log.e("tag", "2");
                mBinding.getViewModel().setMovie(subjectsEntity);
            }
        }

        void bindClick() {
            mItemView.setOnClickListener(v -> MovieDetailActivity.startActivity(mItemView.getContext()));
        }
    }

    @Override public void onResume() {
        super.onResume();
//        mBookRepository.addUpdatable(this);
//        mBookAdapter.startObserving();
        mMovieRepository.addUpdatable(this);
        mMovieAdapter.startObserving();
    }

    @Override public void onPause() {
        super.onPause();
        //        mBookRepository.removeUpdatable(this);
//        mBookAdapter.stopObserving();
        mMovieRepository.removeUpdatable(this);
        mMovieAdapter.stopObserving();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        mNetworkExecutor.shutdown();
    }
}
