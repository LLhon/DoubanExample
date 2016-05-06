package meizu.hon.doubanexample.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import meizu.hon.doubanexample.R;
import meizu.hon.doubanexample.databinding.ItemMovieBinding;
import meizu.hon.doubanexample.model.MovieModel;
import meizu.hon.doubanexample.viewmodel.MovieViewModel;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.adapter
 * @date 2016/5/6 14:29
 * @des
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context mContext;
    private List<MovieModel.SubjectsEntity> mSubjectsEntities;

    public MovieAdapter(Context context, List<MovieModel.SubjectsEntity> subjectsEntity) {
        this.mContext = context;
        this.mSubjectsEntities = subjectsEntity;
    }

    @Override public int getItemCount() {
        return mSubjectsEntities.size();
    }

    @Override public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MovieHolder.create(LayoutInflater.from(mContext), parent, viewType);
    }

    @Override public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bindTo(mSubjectsEntities.get(position));
    }

    static class MovieHolder extends RecyclerView.ViewHolder {

        private ItemMovieBinding mBinding;

        static MovieHolder create(LayoutInflater inflater, ViewGroup parent, int viewType) {
//            ItemMovieBinding binding = ItemMovieBinding.inflate(inflater, parent, false);
            ItemMovieBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);
            return new MovieHolder(binding);
        }

        private MovieHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindTo(MovieModel.SubjectsEntity subjectsEntity) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new MovieViewModel(mBinding.getRoot().getContext(), subjectsEntity));
            }else {
                mBinding.getViewModel().setMovie(subjectsEntity);
            }
            mBinding.executePendingBindings();
        }
    }
}
