package meizu.hon.doubanexample.rx;

import meizu.hon.doubanexample.net.ServerException;
import rx.Observable;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.rx
 * @date 2016/5/7 10:47
 * @des 封装 处理服务器返回数据
 */
public class RxResultHelper {

    public static <T> Observable.Transformer<RESTResult<T>, T> handleResult() {
        return restResultObservable -> restResultObservable.flatMap(trestResult -> {
            if (trestResult.status == RESTResult.SUCCESS) {
                return getData(trestResult.data);
            } else {
                return Observable.error(new ServerException(trestResult.message));
            }
//                        return Observable.empty();
        });
    }

    private static <T> Observable<T> getData(T t) {
        return Observable.create(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(t);
                }
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
