package meizu.hon.doubanexample.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.adapter.MusicAdapter;
import meizu.hon.doubanexample.databinding.FragmentMusicBinding;
import meizu.hon.doubanexample.model.MusicModel;
import meizu.hon.doubanexample.ui.base.BaseFragment;
import meizu.hon.doubanexample.utils.DensityUtils;
import meizu.hon.doubanexample.viewmodel.MusicViewModel;
import meizu.hon.doubanexample.widget.RecyclerInsetsDecoration;
import meizu.hon.doubanexample.widget.SpaceItemDecoration;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.ui.fragment
 * @date 2016/5/9 14:32
 * @des
 */
public class MusicFragment extends BaseFragment<MusicViewModel, FragmentMusicBinding> implements MusicViewModel.DataListener {

    private MusicAdapter mAdapter;
    private MusicViewModel mMusicViewModel;

    @Override protected int getLayoutRes() {
        return R.layout.fragment_music;
    }

    @Override protected void initView() {
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(getActivity(), SpaceItemDecoration.VERTICAL_LIST);
        itemDecoration.setSize(DensityUtils.dp2px(getActivity(), 10));
//        getBinding().recyclerView.addItemDecoration(itemDecoration);
        getBinding().recyclerView.addItemDecoration(new RecyclerInsetsDecoration(getActivity()));
        mAdapter = new MusicAdapter(getActivity());
        getBinding().recyclerView.setAdapter(mAdapter);
    }

    @Override protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mMusicViewModel = new MusicViewModel(getActivity(), this);
        setViewModel(mMusicViewModel);
        getBinding().setViewModel(mMusicViewModel);
    }

    @Override public void onMusicdataChanged(List<MusicModel.MusicsEntity> model) {
        MusicAdapter adapter = (MusicAdapter) getBinding().recyclerView.getAdapter();
        adapter.setMusicData(model);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        getViewModel().destory();
    }
}
