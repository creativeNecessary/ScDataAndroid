package sc.com.scdataview.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import sc.com.commonlib.CommonKit;
import sc.com.commonlib.api.mvp.BaseView;
import sc.com.commonlib.common.util.StatusBarUtil;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/3/9.
 */

public abstract class CommonBaseActivity extends AppCompatActivity implements BaseView {

    protected Toolbar sc_toolbar;
    protected SwipeRefreshLayout refresh_layout;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        CommonKit.addToApplication(this);
        beforeSetContentView();
        setContentView(getContentViewId());
        onNetWorkReady();
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        sc_toolbar = findViewById(R.id.sc_toolbar);
        refresh_layout = findViewById(R.id.refresh_layout);
        if (sc_toolbar != null) {
            setSupportActionBar(sc_toolbar);
        }
        initView();
    }



    protected void beforeSetContentView() {
        StatusBarUtil.setColor(this, Color.BLACK);
    }



    public abstract void initView();

    public abstract void onNetWorkReady() ;

    public void setToolBarTitle(@StringRes int title) {
        getSupportActionBar().setTitle(title);
    }

    public void setToolBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public abstract int getContentViewId();


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        CommonKit.removeFromApplication(this);
        super.onDestroy();
    }
}
