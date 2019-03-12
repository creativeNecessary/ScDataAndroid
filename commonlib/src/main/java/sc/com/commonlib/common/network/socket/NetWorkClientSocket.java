package sc.com.commonlib.common.network.socket;

import android.os.SystemClock;

import com.orhanobut.logger.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2018/11/14.
 */
public class NetWorkClientSocket implements Runnable {
    //服务端存储Client
    private Socket socket;
    private String tag;
    private long connectionTime;
    private boolean closed = false;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;


    public NetWorkClientSocket(Socket socket, long connectionTime) {
        this.socket = socket;
        this.connectionTime = connectionTime;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //接收信息
        //发送网络请求
        //进行响应
        Logger.d("Server Socket 开始运行");

        try {

            while (!closed) {
                //阻塞
                Logger.d("Server Socket 读取请求阻塞");
                byte [] length = new byte[1];
                inputStream.readFully(length,0,1);

                int dataLength = length[0] & 0xFF;

                byte [] requestData = new byte[dataLength];
                inputStream.readFully(requestData,0,dataLength);

                Logger.d("Server Socket 读取到Length"+requestData.length);

//                HttpRequestTest request = (HttpRequestTest) inputStream.readObject();
                //读入请求
//                Logger.d("Server Socket 读取请求成功" + request.getTag());
//                CommonKit.getHttpClient().newCall(request.getParameter().getRequest()).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        //全局失败
//                        e.printStackTrace();
//                        try {
//                            request.initErrorResponse("Server Error");
//                            outputStream.writeObject(request);
//                            outputStream.flush();
//                            outputStream.reset();
//
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        //构建response
//                        Logger.d("Server Socket 收到网络请求响应" + request.getTag());
//                        if (response.body() != null) {
//                            try {
//                                request.initResponse(response.body().string());
//                            } catch (Exception e) {
//                                //解析json报错 更正错误状态
//                                request.initErrorResponse("Json解析错误");
//                            }
//
//                        } else {
//
//
//                        }
//                        Logger.d("Server Socket 处理完成 进行回复" + request.getStatus().getStatus());
//                        outputStream.writeObject(request);
//                        outputStream.flush();
//                        outputStream.reset();
//
//
//                    }
//                });

            }


        } catch (Exception e) {
            Logger.d(e);
            e.printStackTrace();
        }


    }
}
