package meizu.hon.doubanexample.rx;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.rx
 * @date 2016/5/7 10:46
 * @des
 */
public class RESTResult<T> {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;

    public int status;
    public String message;
    public T data;

}
