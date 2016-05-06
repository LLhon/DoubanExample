package meizu.hon.doubanexample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Author liuhehong
 * @Project DoubanExample
 * @PackageName meizu.hon.doubanexample.widget
 * @Date 2016/5/6 9:24
 * @Des
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = {android.R.attr.listDivider};
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    public Drawable mDivider;
    public int mSize;
    public int mOrientation;

    public SpaceItemDecoration(Context context, int orientation) {
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        mSize = mDivider.getIntrinsicHeight();
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    public void setSize(int size) {
        this.mSize = size;
    }

    @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mSize);
        }else {
            outRect.set(0, 0, mSize, 0);
        }
    }
}
