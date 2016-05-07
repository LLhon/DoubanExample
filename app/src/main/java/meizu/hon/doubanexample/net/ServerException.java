package meizu.hon.doubanexample.net;

/**
 * @author liuhehong
 * @project DoubanExample
 * @packageName meizu.hon.doubanexample.net
 * @date 2016/5/7 11:23
 * @des
 */
public class ServerException extends Exception {

    private String mMsg;

    public ServerException(String message) {
        this.mMsg = message;
    }

    public String getMessage() {
        return mMsg;
    }
}
