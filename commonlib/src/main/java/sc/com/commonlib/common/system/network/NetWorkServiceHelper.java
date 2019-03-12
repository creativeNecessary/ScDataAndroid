//package sc.com.commonlib.common.system.network;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.IBinder;
//
//import com.orhanobut.logger.Logger;
//
//import sc.com.commonlib.CommonKit;
//import sc.com.commonlib.common.network.service.NetWorkService;
//
//import static android.content.Context.BIND_AUTO_CREATE;
//
///**
// * Created by Eric on 2018/11/9.
// */
//public class NetWorkServiceHelper  implements ServiceConnection{
//    private INetWorkManager manager;
//    private INetWorkStateListener.OnBindSuccessListener onBindSuccessListener;
//    private INetWorkStateListener.OnNullBindingListener onNullBindingListener;
//    private INetWorkStateListener.OnBindingDiedListener onBindingDiedListener;
//    private INetWorkStateListener.OnServiceDisconnectedListener onServiceDisconnectedListener;
//    private INetWorkStateListener.OnBindFailedListener onBindFailedListener;
//
//
//
//    public NetWorkServiceHelper(INetWorkStateListener.OnBindSuccessListener onBindSuccessListener) {
//        this.onBindSuccessListener = onBindSuccessListener;
//    }
//
//    public  void bindNetWorkService() {
//        Intent intent = new Intent(CommonKit.getApplicationContext(), NetWorkService.class);
//        CommonKit.getApplicationContext().bindService(intent, this, BIND_AUTO_CREATE);
//
//    }
//
//    public  void unBindNetWorkService() {
//        CommonKit.getApplicationContext().unbindService(this);
//    }
//
//
//    public INetWorkManager getManager() {
//        return manager;
//    }
//
//    @Override
//    public void onServiceConnected(ComponentName name, IBinder service) {
//        manager = INetWorkManager.Stub.asInterface(service);
//        if(onBindSuccessListener !=null){
//            onBindSuccessListener.onBindSuccess();
//        }
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName name) {
//
//    }
//}
