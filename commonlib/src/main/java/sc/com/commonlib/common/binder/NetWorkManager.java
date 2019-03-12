//package sc.com.commonlib.common.binder;
//
//import android.content.Context;
//import android.os.Binder;
//import android.os.IBinder;
//import android.os.Parcel;
//import android.os.RemoteException;
//import android.support.annotation.NonNull;
//
//import com.orhanobut.logger.Logger;
//
//import java.io.IOException;
//
//import okhttp3.Cache;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Response;
//import sc.com.commonlib.api.http.HttpCallBack;
//import sc.com.commonlib.api.http.HttpRequest;
//import sc.com.commonlib.api.http.HttpResponse;
//import sc.com.commonlib.common.util.FileUtil;
//
//import static sc.com.commonlib.common.constant.Common.cacheSize10M;
//
///**
// * Created by Eric on 2018/10/29.
// */
//public class NetWorkManager extends Binder implements INetWorkManager {
//
//    private OkHttpClient okHttpClient;
//
//    private static class ClassHolder {
//        private static NetWorkManager holder = new NetWorkManager();
//    }
//
//    private NetWorkManager() {
//        if (okHttpClient == null) {
//            okHttpClient = new OkHttpClient().newBuilder()
//                    .cache(new Cache(FileUtil.getNetCacheDir(), cacheSize10M))
//                    .build();
//        }
//    }
//
//    public static NetWorkManager getInstance() {
//        return ClassHolder.holder;
//    }
//
//    @Override
//    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
//        switch (code) {
//            case INetWorkManager.EXECUTE_POST_REQUEST:
//                Logger.d("NetWorkManager--EXECUTE_POST_REQUEST --- ");
//                data.enforceInterface(INetWorkManager.DESCRIPTOR);
//                data.recycle();
//                reply.recycle();
//
//                return true;
//
//        }
//
//        return super.onTransact(code, data, reply, flags);
//    }
//
//
//    static public INetWorkManager asInterface(IBinder obj) {
//        if (obj == null) {
//            return null;
//        }
//        INetWorkManager in =
//                (INetWorkManager) obj.queryLocalInterface(DESCRIPTOR);
//        if (in != null) {
//            return in;
//        }
//
//        return new NetWorkManagerProxy(obj);
//    }
//
//    @Override
//    public IBinder asBinder() {
//        return this;
//    }
//
//
//    @Override
//    public void executeGetRequest(HttpRequest httpRequest) {
//
//        okHttpClient.newCall(httpRequest.getRequest()).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                //全局失败\
//
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                //构建response
//
////                T netResponse = initResponse(response.body().string());
////                httpCallBack.onResponse(netResponse);
//            }
//        });
//    }
//
//    @Override
//    public void executePostRequest(HttpRequest httpRequest) {
//    }
//
//    @Override
//    public void executeDownloadRequest(HttpRequest httpRequest) {
//    }
//
//    @Override
//    public void executeUploadRequest(HttpRequest httpRequest) {
//    }
//
//    public OkHttpClient getHttpClient() {
//        return okHttpClient;
//    }
//}
