package sc.com.scdataview.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by Eric on 2018/3/12.
 */

public class LoadingActivity extends CommonBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public void onNetWorkReady() {

    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
