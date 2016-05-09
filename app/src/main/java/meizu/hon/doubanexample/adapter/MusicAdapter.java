package meizu.hon.doubanexample.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.databinding.ItemMusicBinding;
import meizu.hon.doubanexample.model.MusicModel;
import meizu.hon.doubanexample.viewmodel.ItemMusicViewModel;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.adapter
 * @date 2016/5/9 15:48
 * @des
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private Context mContext;
    private List<MusicModel.MusicsEntity> mDatas;

    public MusicAdapter(Context context) {
        this.mContext = context;
    }

    @Override public int getItemCount() {
        return mDatas.size();
    }

    @Override public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MusicViewHolder.create(LayoutInflater.from(mContext), parent, viewType);
    }

    @Override public void onBindViewHolder(MusicViewHolder holder, int position) {
        holder.bindTo(mDatas.get(position));
    }

    public void setMusicData(List<MusicModel.MusicsEntity> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    static class MusicViewHolder extends RecyclerView.ViewHolder {

        ItemMusicBinding mBinding;

        static MusicViewHolder create(LayoutInflater inflater, ViewGroup parent, int viewType) {
            ItemMusicBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_music, parent, false);
            return new MusicViewHolder(binding);
        }

        private MusicViewHolder(ItemMusicBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bindTo(MusicModel.MusicsEntity entity) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemMusicViewModel(mBinding.getRoot().getContext(), entity));
            }else {
                mBinding.getViewModel().setEntity(entity);
            }
            mBinding.executePendingBindings();
        }
    }
}
