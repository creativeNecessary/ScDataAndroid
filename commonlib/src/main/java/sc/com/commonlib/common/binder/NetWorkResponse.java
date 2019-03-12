//package sc.com.commonlib.common.binder;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import java.io.IOException;
//import java.io.Serializable;
//
//import okhttp3.Response;
//
///**
// * Created by Eric on 2018/11/12.
// */
//public class NetWorkResponse implements Serializable {
//
//
//    private int responseCode;
//    private String responseBody = "";
//    private String requestTag = "";
//
//    public NetWorkResponse(Response response,String requestTag) {
//        this.responseCode = response.code();
//        try {
//            if (response.body() != null) {
//                responseBody = response.body().string();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//}
