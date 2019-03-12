package sc.com.commonlib.common.system.network;

import android.content.ComponentName;


/**
 * Created by Eric on 2018/11/7.
 */
public interface INetWorkStateListener extends INetWorkListener {

    interface OnBindSuccessListener extends INetWorkStateListener {
        void onBindSuccess();
    }

    interface OnBindFailedListener extends INetWorkStateListener {
        void onBindFailed(int code, String reason);
    }

    interface OnServiceDisconnectedListener extends INetWorkStateListener {
        void onServiceDisconnected(ComponentName name);
    }

    interface OnBindingDiedListener extends INetWorkStateListener {
        void onBindingDied(ComponentName name);
    }

    interface OnNullBindingListener extends INetWorkStateListener {
        void onNullBinding(ComponentName name);
    }





}
