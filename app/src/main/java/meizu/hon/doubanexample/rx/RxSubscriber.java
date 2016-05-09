package meizu.hon.doubanexample.rx;

import meizu.hon.doubanexample.net.ServerException;
import meizu.hon.doubanexample.utils.UIUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.rx
 * @date 2016/5/7 11:07
 * @des 封装 异常处理
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    @Override public void onError(Throwable e) {
        if (!UIUtils.checkNet(UIUtils.getContext())) {
            _onError("网络不可用");
            return;
        }
        if (e instanceof ServerException) {
            _onError(e.getMessage());
        }else if (isHttp404(e)) {
            _onError("The Page is 404");
            //更多异常处理
        }else {
            _onError("请求失败,请稍后重试...");
        }
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException)error).code() == 404;
    }

    @Override public void onNext(T t) {
        _onNext(t);
    }

    @Override public void onCompleted() {

    }

    public abstract void _onNext(T t);

    public abstract void _onError(String msg);
}
