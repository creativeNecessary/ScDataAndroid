//package sc.com.commonlib.common.binder;
//
//import android.os.IBinder;
//import android.os.Parcel;
//import android.os.RemoteException;
//import com.orhanobut.logger.Logger;
//import sc.com.commonlib.api.http.HttpRequest;
//
///**
// * Created by Eric on 2018/10/29.
// */
//public class NetWorkManagerProxy implements INetWorkManager {
//
//    private IBinder remote;
//
//    public NetWorkManagerProxy(IBinder remote) {
//        this.remote = remote;
//    }
//
//
//    @Override
//    public IBinder asBinder() {
//        return remote;
//    }
//
//    @Override
//    public void executeGetRequest(HttpRequest httpRequest) {
//
//    }
//
//    @Override
//    public void executePostRequest(HttpRequest httpRequest) {
//        Parcel data = Parcel.obtain();
//        Parcel replay = Parcel.obtain();
//        try {
//            Logger.d("NetWorkManagerProxy---executePostRequest--"+remote);
//            data.writeInterfaceToken(DESCRIPTOR);
//            remote.transact(EXECUTE_POST_REQUEST, data, replay, 0);
//
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } finally {
//            replay.recycle();
//            data.recycle();
//        }
//
//
//    }
//
//    @Override
//    public void executeDownloadRequest(HttpRequest httpRequest) {
//
//    }
//
//    @Override
//    public void executeUploadRequest(HttpRequest httpRequest) {
//
//    }
//}
