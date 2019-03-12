package sc.com.commonlib.common.network.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by Eric on 2018/11/14.
 */
public class NetWorkServer implements Runnable {

    private boolean stop = false;

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8688);

            while (!stop){
                Socket socket =  serverSocket.accept();
                //流程 构建ClientSocket对象
                NetWorkClientSocket clientSocket = new NetWorkClientSocket(socket,System.currentTimeMillis());
                NetWorkThreadPool.getInstance().getThreadPool().execute(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

    public void stop() {
        stop = true;
    }
}
