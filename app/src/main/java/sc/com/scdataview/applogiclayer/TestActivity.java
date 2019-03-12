package sc.com.scdataview.applogiclayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.api.http.HttpCallBackTest;
import sc.com.commonlib.common.network.socket.NetWorkServerSocket;
import sc.com.commonlib.common.network.socket.NetWorkThreadPool;
import sc.com.commonlib.impl.http.entity.HttpParameter;
import sc.com.commonlib.impl.http.request.CheckUpdateRequest;
import sc.com.commonlib.impl.http.request.CheckUpdateRequestTest;
import sc.com.commonlib.impl.http.response.CheckUpdateResponse;
import sc.com.commonlib.impl.http.response.CheckUpdateResponseTest;
import sc.com.scdataview.R;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/4/3.
 */

public class TestActivity extends CommonBaseActivity {

    private NetWorkHandler handler;
    NetWorkServerSocket serverSocket;


    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new NetWorkHandler();

        serverSocket = new NetWorkServerSocket("TestActivity", handler);
        NetWorkThreadPool.getInstance().getThreadPool().execute(serverSocket);
        //链接socket

    }

    @Override
    public void initView() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {

            serverSocket.executeNetWorkRequest(new CheckUpdateRequestTest(2), new HttpCallBackTest<CheckUpdateResponseTest>() {
                @Override
                public void onFailure() {

                }

                @Override
                public void onResponse(CheckUpdateResponseTest netResponse) {
                    Logger.d(netResponse.getCheckUpdate().getVersion_name());
                }
            });

        });

    }

    @Override
    public void onNetWorkReady() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_test;
    }


    @Override
    public Context getViewContext() {
        return this;
    }

    public static class NetWorkHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 1:

                    break;


            }

        }
    }
}
