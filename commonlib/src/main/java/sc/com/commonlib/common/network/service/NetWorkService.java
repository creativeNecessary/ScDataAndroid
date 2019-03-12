package sc.com.commonlib.common.network.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.orhanobut.logger.Logger;

import sc.com.commonlib.common.network.socket.NetWorkServer;
import sc.com.commonlib.common.network.socket.NetWorkThreadPool;

/**
 * Created by Eric on 2018/10/29.
 */
public class NetWorkService extends Service {
    private NetWorkServer netWorkServer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.d(intent);
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        netWorkServer = new NetWorkServer();
        //启动server
        NetWorkThreadPool.getInstance().getThreadPool().execute(netWorkServer);


    }



    @Override
    public void onDestroy() {
        netWorkServer.stop();
        super.onDestroy();
    }


}
