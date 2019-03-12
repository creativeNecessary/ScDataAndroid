package sc.com.commonlib.common.network.entity;

import sc.com.commonlib.api.http.HttpRequestTest;
import sc.com.commonlib.impl.http.entity.SerializableBaseData;

/**
 * Created by Eric on 2018/11/26.
 */
public final class SocketData extends SerializableBaseData {

    private final int msgLength;

    private final byte[] requestData;

    private final HttpRequestTest requestTest;


    public SocketData(HttpRequestTest requestTest) {
        this.requestTest = requestTest;
        this.requestData = requestTest.getParameter().change2Json().getBytes();
        this.msgLength = requestData.length;

    }

    public byte[] getSendData() {
        int size = 1 + requestData.length;
        byte[] data = new byte[size];
        System.arraycopy(requestData,0,data,1,size);
        data[0] = (byte)requestData.length;
        return data;
    }


}
