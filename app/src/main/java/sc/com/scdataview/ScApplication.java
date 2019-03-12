package sc.com.scdataview;

import android.app.Application;

import sc.com.commonlib.CommonKit;

/**
 * Created by Eric on 2018/3/2.
 */
public class ScApplication extends Application {


    private static ScApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CommonKit.init(application);

    }

    public static ScApplication getApplication() {
        return application;
    }


}
