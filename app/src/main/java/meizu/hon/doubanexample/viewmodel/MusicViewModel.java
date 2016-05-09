package meizu.hon.doubanexample.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meizu.hon.doubanexample.model.MusicModel;
import meizu.hon.doubanexample.rx.RxSchedulersHelper;
import meizu.hon.doubanexample.rx.RxSubscriber;
import meizu.hon.doubanexample.utils.UIUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.viewmodel
 * @date 2016/5/9 15:16
 * @des
 */
public class MusicViewModel extends ViewModel {

    private Context mContext;
    private DataListener mListener;
    public ObservableInt mRecyclerviewVisibility;
    public ObservableInt mProgressviewVisibility;
    public ObservableInt mFabVisibility;
    public ObservableField<String> mErrorPageText;
    public ObservableInt mErrorPageVisibility;
    private Subscription mSubscription;

    public MusicViewModel(Context context, DataListener listener) {
        this.mContext = context;
        this.mListener = listener;
        mRecyclerviewVisibility = new ObservableInt();
        mProgressviewVisibility = new ObservableInt();
        mFabVisibility = new ObservableInt(View.GONE);
        mErrorPageVisibility = new ObservableInt();
        mErrorPageText = new ObservableField<>("error page.....");
        loadMusicData("陈奕迅");
    }

    private void loadMusicData(String tag) {
        mProgressviewVisibility.set(View.VISIBLE);
        mRecyclerviewVisibility.set(View.INVISIBLE);
        mErrorPageVisibility.set(View.INVISIBLE);
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
        Map<String, Object> map = new HashMap<>();
        map.put("start", 0);
        map.put("count", 30);
        map.put("tag", tag);
        mSubscription = UIUtils.getService().getMusic(map)
                .compose(RxSchedulersHelper.io_main())
                .subscribe(new RxSubscriber<MusicModel>() {
                    @Override public void _onNext(MusicModel model) {
                        if (mListener != null) {
                            mListener.onMusicdataChanged(model.getMusics());
                        }
                        mProgressviewVisibility.set(View.INVISIBLE);
                        if (model.getCount() == 0) {
                            mErrorPageVisibility.set(View.VISIBLE);
                            mErrorPageText.set("no data.....");
                        }else {
                            mRecyclerviewVisibility.set(View.VISIBLE);
                            mFabVisibility.set(View.VISIBLE);
                        }
                    }

                    @Override public void _onError(String msg) {
                        UIUtils.showToast(msg);
                        mProgressviewVisibility.set(View.INVISIBLE);
                        mErrorPageVisibility.set(View.VISIBLE);
                        mErrorPageText.set(msg);
                    }
                });
    }

    public void clickSearch(View view) {
        new MaterialDialog.Builder(mContext)
                .title("搜索")
                .input("请输入关键字", "花千骨", (dialog, input) -> loadMusicData(input.toString()))
                .show();
    }

    public void setDataListener(DataListener listener) {
        this.mListener = listener;
    }

    public interface DataListener {
        void onMusicdataChanged(List<MusicModel.MusicsEntity> model);
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException)error).code() == 404;
    }

    @Override public void destory() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
        mSubscription = null;
        mContext = null;
        mListener = null;
    }
}
