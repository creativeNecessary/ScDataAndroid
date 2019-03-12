package sc.com.scdataview.applogiclayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import java.util.ArrayList;

import sc.com.commonlib.common.constant.Extras;
import sc.com.commonlib.common.util.PermissionUtil;
import sc.com.scdataview.R;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/5/23.
 */
public class PermissionActivity extends CommonBaseActivity {

    private int requestCode = -1;

    public static void start(Context context, ArrayList permissions,int code) {
        Intent starter = new Intent(context, PermissionActivity.class);
        starter.putExtra(Extras.DATA, permissions);
        starter.putExtra(Extras.REQUEST_CODE, code);
        starter.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        ((Activity) context).startActivityForResult(starter, code);
        ((Activity) context).overridePendingTransition(0, 0);//去掉切换动画关键

    }

    @Override
    public void initView() {
        ArrayList<String> permissions = getIntent().getStringArrayListExtra(Extras.DATA);
        requestCode = getIntent().getIntExtra(Extras.REQUEST_CODE,-1);
        checkScPermission(permissions);

    }

    @Override
    public void onNetWorkReady() {

    }


    private void checkScPermission(ArrayList<String> permissions) {
        if (permissions.size() == 0) {
            setScResult(PermissionUtil.ALL_PERMISSION_GRANTED);
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String permission : permissions) {
            int grant = ContextCompat.checkSelfPermission(context, permission);

            if (grant == PackageManager.PERMISSION_DENIED) {
                arrayList.add(permission);
            }
        }
        if (arrayList.size() == 0) {
            setScResult(PermissionUtil.ALL_PERMISSION_GRANTED);
            return;
        }
        ActivityCompat.requestPermissions(this, arrayList.toArray(new String[arrayList.size()]), requestCode);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_check_permission;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            boolean have_denied = false;
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    have_denied = true;
                    break;
                }
            }
            if (have_denied) {
                setScResult(PermissionUtil.HAVE_PERMISSION_DENIED);
            } else {
                setScResult(PermissionUtil.ALL_PERMISSION_GRANTED);
            }

        } else {
            setScResult(PermissionUtil.HAVE_PERMISSION_DENIED);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void setScResult(int code) {
        setResult(code);
        finish();
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
