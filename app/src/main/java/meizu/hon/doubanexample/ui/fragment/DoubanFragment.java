package meizu.hon.doubanexample.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.agera.BaseObservable;
import com.google.android.agera.Receiver;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.google.android.agera.rvadapter.RepositoryAdapter;
import com.google.android.agera.rvadapter.RepositoryPresenter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.agera.BookSupplier;
import meizu.hon.doubanexample.databinding.FragmentDoubanBinding;
import meizu.hon.doubanexample.model.BookModel;
import meizu.hon.doubanexample.ui.base.BaseFragment;
import meizu.hon.doubanexample.utils.DensityUtils;
import meizu.hon.doubanexample.utils.UIUtils;
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
    private Repository<Result<List<BookModel>>> mBookRepository;
    private RepositoryAdapter mBookAdapter;

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
        setupRepository();
    }

    @Override public void update() {
        getBinding().mpb.setVisibility(View.INVISIBLE);
        mBookRepository.get().ifSucceededSendTo(new BookReceiver());
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
        mBookRepository = Repositories.repositoryWithInitialValue(Result.<List<BookModel>>absent())
                .observe(mSearchObservable)
                .onUpdatesPerLoop()
                .goTo(mNetworkExecutor)
                .thenGetFrom(mBookSupplier)
                .compile();
        mBookRepository.addUpdatable(this);
        mBookAdapter = RepositoryAdapter.repositoryAdapter()
                .add(mBookRepository, new BookPresenter())
                .build();
        mBookAdapter.startObserving();
    }

    class BookPresenter extends RepositoryPresenter<Result<List<BookModel>>> {

        @Override public int getLayoutResId(@NonNull Result<List<BookModel>> data, int index) {
            return 0;
        }

        @Override public int getItemCount(@NonNull Result<List<BookModel>> data) {
            return 0;
        }

        @Override public void bind(@NonNull Result<List<BookModel>> data, int index, @NonNull RecyclerView.ViewHolder holder) {
            
        }
    }

    @Override public void onResume() {
        super.onResume();
        mBookRepository.addUpdatable(this);
        mBookAdapter.startObserving();
    }

    @Override public void onPause() {
        super.onPause();
        mBookRepository.removeUpdatable(this);
        mBookAdapter.stopObserving();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        mBookRepository.removeUpdatable(this);
        mBookAdapter.stopObserving();
    }
}
