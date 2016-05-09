package meizu.hon.doubanexample.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import meizu.hon.doubanexample.R;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.widget
 * @date 2016/5/9 9:27
 * @des
 */
public class RecyclerInsetsDecoration extends RecyclerView.ItemDecoration {

    private int mInsets;

    public RecyclerInsetsDecoration(Context context) {
        mInsets = context.getResources().getDimensionPixelSize(R.dimen.item_divider);
    }

    @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}
