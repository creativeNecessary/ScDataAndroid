package sc.com.data_provider;

import android.content.Context;

/**
 * Created by Eric on 2018/4/10.
 */

public class ScDataProvider {

    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
