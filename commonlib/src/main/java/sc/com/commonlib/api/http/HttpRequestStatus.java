package sc.com.commonlib.api.http;


import sc.com.commonlib.impl.http.entity.SerializableBaseData;

/**
 * Created by Eric on 2018/11/21.
 */
public class HttpRequestStatus extends SerializableBaseData {

    private  Status status = Status.SUCCESS;

    private String reason;

    public enum  Status{
        SUCCESS,FAILED
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
