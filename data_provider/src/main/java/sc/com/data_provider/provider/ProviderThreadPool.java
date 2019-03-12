package sc.com.data_provider.provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Eric on 2018/4/11.
 */

public class ProviderThreadPool {
    private ExecutorService cachedThreadPool;

    private static class ClassHolder {
        private static ProviderThreadPool holder = new ProviderThreadPool();
    }


    public static ProviderThreadPool getInstance() {
        return ClassHolder.holder;
    }


    private ProviderThreadPool() {
        cachedThreadPool = Executors.newCachedThreadPool();
    }


    public ExecutorService getThreadPool() {
        return cachedThreadPool;
    }

}
