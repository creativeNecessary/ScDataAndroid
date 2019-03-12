package sc.com.scdataview.applogiclayer.welcome;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Bundle;

import sc.com.scdataview.applogiclayer.TestActivity;
import sc.com.scdataview.base.CommonBaseActivity;
import sc.com.scdataview.R;
import sc.com.scdataview.applogiclayer.host.HostActivity;

public class WelComeActivity extends CommonBaseActivity {


    /**
     * 从服务器获取 装备type 飞船名字 翻译到本地数据库
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startCountDown();

    }

    private void startCountDown() {

        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                HostActivity.start(WelComeActivity.this);
//                TestActivity.start(WelComeActivity.this);
                finish();

            }
        };
        countDownTimer.start();
    }

    @Override
    public void initView() {

    }

    @Override
    public void onNetWorkReady() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }


    @Override
    public Context getViewContext() {
        return this;
    }
}
