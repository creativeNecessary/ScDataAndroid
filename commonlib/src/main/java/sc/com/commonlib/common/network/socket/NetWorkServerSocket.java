package sc.com.commonlib.common.network.socket;

import android.os.Handler;
import android.os.SystemClock;

import com.orhanobut.logger.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.api.http.HttpCallBackTest;
import sc.com.commonlib.api.http.HttpRequest;
import sc.com.commonlib.api.http.HttpRequestStatus;
import sc.com.commonlib.api.http.HttpRequestTest;
import sc.com.commonlib.api.http.HttpResponse;
import sc.com.commonlib.api.http.HttpResponseTest;
import sc.com.commonlib.common.network.entity.SocketData;
import sc.com.commonlib.impl.http.entity.HttpParameter;

/**
 * Created by Eric on 2018/11/14.
 */
public class NetWorkServerSocket implements Runnable {
    //客户端存储Client
    private Socket socket;
    private String tag;
    private boolean closed = false;
    private Handler handler;
    private volatile int count = 1;
    private LinkedBlockingQueue<HttpRequestTest> requestQueue;
    private HashMap<String, HttpCallBackTest> callBackHashMap;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public NetWorkServerSocket(String tag, Handler handler) {
        this.tag = tag;
        this.handler = handler;
        requestQueue = new LinkedBlockingQueue<HttpRequestTest>();
        callBackHashMap = new HashMap<>();
    }

    public <T extends HttpResponseTest> void executeNetWorkRequest(HttpRequestTest<T> request, HttpCallBackTest<T> httpCallBack) {
        try {
            requestQueue.put(request);
            callBackHashMap.put(request.getTag(), httpCallBack);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int connect_count = 0;
        while (socket == null) {
            try {
                socket = new Socket("127.0.0.1", 8688);
                outputStream = new DataOutputStream(socket.getOutputStream());
                inputStream = new DataInputStream(socket.getInputStream());
                handler.sendEmptyMessage(1);
            } catch (IOException e) {
                SystemClock.sleep(1000);
                connect_count++;
                if (connect_count == 5) {
                    closed = true;
                }
                e.printStackTrace();
            }
            //发送信息
        }
        //链接上服务
        //给server进程发消息
        try {
            while (!closed) {

                Logger.d("Client 阻塞读取请求");
                HttpRequestTest request = requestQueue.take();
                SocketData socketData = new SocketData(request);
                Logger.d("Client 读取到请求 " + request.getTag());
                byte [] data = socketData.getSendData();
                outputStream.write(data,0,data.length);
                Logger.d("Client 写入完成");

//                outputStream.writeObject(request);
//                outputStream.flush();
//                Logger.d("Client 发送请求到Server " + request.getTag());
//                Logger.d("Client 读取Server响应阻塞 ");
//                HttpRequestTest responseRequest = (HttpRequestTest) inputStream.readObject();
//                Logger.d("Client 读取Server响应成功 " + responseRequest.getTag());
//                HttpCallBackTest callBack = callBackHashMap.get(responseRequest.getTag());
//                Logger.d("Client 查询Callback缓存 " + callBack);
//                if (callBack != null) {
//                    Logger.d("Client 响应Callback " + callBack);
//                    if(responseRequest.getStatus().getStatus() == HttpRequestStatus.Status.SUCCESS){
//                        callBack.onResponse(responseRequest.getResponse());
//                    }else {
//                        callBack.onFailure();
//                    }
//
//                    callBackHashMap.remove(responseRequest.getTag());
//                }

            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Logger.d(e);
        }


        handler = null;
    }
}
