package meizu.hon.doubanexample.viewmodel;

import android.databinding.BaseObservable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liuhehong
 * @project Xixiang_Unite_Receiver
 * @packageName com.zf.src.enforce.viewmodel
 * @date 2016/4/18 11:08
 * @des
 */
public abstract class ViewModel extends BaseObservable {

    /* Just mark a method in ViewModel */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    protected  @interface Command {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    protected @interface BindView {
    }
    // ... InstanceState in ViewModel

    public abstract void destory();
}
