package sc.com.commonlib.common.network.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Eric on 2018/4/11.
 */

public class NetWorkThreadPool {
    private ExecutorService cachedThreadPool;

    private static class ClassHolder {
        private static NetWorkThreadPool holder = new NetWorkThreadPool();
    }


    public static NetWorkThreadPool getInstance() {
        return ClassHolder.holder;
    }


    private NetWorkThreadPool() {
        cachedThreadPool = Executors.newCachedThreadPool();
    }


    public ExecutorService getThreadPool() {
        return cachedThreadPool;
    }

}
