package meizu.hon.doubanexample.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

import meizu.hon.doubanexample.R;


/**
 * @Author liuhehong
 * @Project FABHideAndShowDemo
 * @PackageName meizu.hon.fabhideandshowdemo.widget
 * @Date 2016/4/29 13:56
 * @Des
 */
public class MaterialProgressBar extends ProgressBar {

    private final int mDuration;
    private Animator mAnimator = null;
    private static final int INDETERMINATE_MAX = 1000;
    private static final String PROGRESS = "progress";
    private static final String SECONDARY_PROGRESS = "secondaryProgress";

    public MaterialProgressBar(Context context) {
        this(context, null, -1);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialProgressBar, defStyleAttr, 0);
        int backgroundColour;
        int progressColour;
        try {
            backgroundColour = typedArray.getColor(R.styleable.MaterialProgressBar_backgroundColour, 0);
            progressColour = typedArray.getColor(R.styleable.MaterialProgressBar_progressColour, 0);
            int defalutDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
            mDuration = typedArray.getInteger(R.styleable.MaterialProgressBar_duration, defalutDuration);
        }finally {
            typedArray.recycle();
        }
        this.setProgressDrawable(context.getResources().getDrawable(android.R.drawable.progress_horizontal));
        createIndeterminateProgressDrawable(backgroundColour, progressColour);
        setMax(INDETERMINATE_MAX);
        super.setIndeterminate(false);
        this.setIndeterminate(true);
    }

    private void createIndeterminateProgressDrawable(@ColorInt int backgroundColour, @ColorInt int progressColour) {
        LayerDrawable layerDrawable = (LayerDrawable) getProgressDrawable();
        if (layerDrawable != null) {
            layerDrawable.mutate();  //改变对应ID的drawable
            layerDrawable.setDrawableByLayerId(android.R.id.progress, createClipDrawable(backgroundColour));
            layerDrawable.setDrawableByLayerId(android.R.id.secondaryProgress, createClipDrawable(progressColour));
            layerDrawable.setDrawableByLayerId(android.R.id.background, createShapeDrawable(backgroundColour));
        }
    }

    private Drawable createClipDrawable(@ColorInt int colour) {
        ShapeDrawable shapeDrawable = createShapeDrawable(colour);
        return new ClipDrawable(shapeDrawable, Gravity.START, ClipDrawable.HORIZONTAL);
    }

    private ShapeDrawable createShapeDrawable(@ColorInt int colour) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        setColour(shapeDrawable, colour);
        return shapeDrawable;
    }

    private void setColour(ShapeDrawable drawable, @ColorInt int colour) {
        Paint paint = drawable.getPaint();
        paint.setColor(colour);
    }

    private boolean isStarted() {
        return mAnimator != null && mAnimator.isStarted();
    }

    @Override public synchronized void setIndeterminate(boolean indeterminate) {
        super.setIndeterminate(indeterminate);

        if (isStarted()) {
            return;
        }
        mAnimator = createIndeterminateAnimator();
        mAnimator.setTarget(this);
        mAnimator.start();
    }

    private Animator createIndeterminateAnimator() {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator secondaryProgressAnimator = getAnimator(SECONDARY_PROGRESS, new DecelerateInterpolator());  //减速度动画
        ObjectAnimator progressAnimator = getAnimator(PROGRESS, new AccelerateInterpolator()); //加速度动画
        set.playTogether(progressAnimator, secondaryProgressAnimator);
        set.setDuration(mDuration);
        return set;
    }

    private ObjectAnimator getAnimator(String propertyName, Interpolator interpolator) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, propertyName, 0, INDETERMINATE_MAX);
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.setDuration(mDuration);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setRepeatMode(ValueAnimator.INFINITE);
        return objectAnimator;
    }
}
