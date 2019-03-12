package sc.com.scdataview.applogiclayer.host;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import sc.com.commonlib.api.http.DownloadCallBack;
import sc.com.commonlib.common.entity.HostItem;
import sc.com.commonlib.common.factory.NotificationFactory;
import sc.com.commonlib.common.helper.NotificationHelper;
import sc.com.commonlib.common.util.CommonUtil;
import sc.com.commonlib.common.util.DownloadUtil;
import sc.com.commonlib.common.util.FileUtil;
import sc.com.commonlib.common.util.NotificationUtil;
import sc.com.commonlib.common.util.PermissionUtil;
import sc.com.commonlib.impl.http.entity.CheckUpdate;
import sc.com.commonlib.impl.http.entity.DownloadFileEntity;
import sc.com.scdataview.applogiclayer.PermissionActivity;
import sc.com.scdataview.applogiclayer.commlink.CommLinkListActivity;
import sc.com.scdataview.applogiclayer.commlink.CommLinkPresenter;
import sc.com.scdataview.applogiclayer.shiplist.ShipListActivity;
import sc.com.scdataview.base.CommonBaseActivity;
import sc.com.scdataview.R;
import sc.com.scdataview.dialog.NeedUpdateDialog;

/**
 * Created by Eric on 2018/3/9.
 */

public class HostActivity extends CommonBaseActivity implements HostContract.View {
    private HostPresenter presenter;
    private RecyclerView item_recycler;
    private HostItemAdapter hostItemAdapter;


    public static void start(Context context) {
        Intent starter = new Intent(context, HostActivity.class);
        context.startActivity(starter);
    }


    @Override
    public void initView() {
        setToolBarTitle("Ver " + CommonUtil.getVersionName(getViewContext()));
        item_recycler = findViewById(R.id.item_recycler);

    }

    @Override
    public void onNetWorkReady() {
        presenter = new HostPresenter(this);
        presenter.onStart();
    }

    @Override
    public void initItemRecycler() {
        item_recycler.setLayoutManager(new LinearLayoutManager(context));
        hostItemAdapter = new HostItemAdapter(getHostItemList());
        item_recycler.setAdapter(hostItemAdapter);
    }


    public static List<HostItem> getHostItemList() {
        List<HostItem> list = new ArrayList<>();
        HostItem item1 = new HostItem("载具介绍", R.mipmap.ship_introduce);
//        HostItem item2 = new HostItem("银河指南", R.mipmap.galaxy_guide);
        HostItem item3 = new HostItem("公民常识", R.mipmap.citizen_common_sense);
        HostItem item4 = new HostItem("开发计划", R.mipmap.road_map);
        list.add(item1);
//        list.add(item2);
        list.add(item3);
        list.add(item4);
        return list;
    }


    @Override
    public void initListener() {


        item_recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    ShipListActivity.start(getViewContext());
                } else if (position == 1) {
//                    CommLinkListActivity.start(getViewContext(), CommLinkPresenter.TYPE_GALAXY_GUIDE);
                } else {
                    Toast.makeText(context, R.string.no_function, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_host;
    }


    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PermissionUtil.REQUEST_CHECK_DOWNLOAD_PERMISSION) {
            if (resultCode == PermissionUtil.ALL_PERMISSION_GRANTED) {
                downloadApk();
            }
        } else {
            Toast.makeText(context, R.string.permission_denied, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void needUpdate(CheckUpdate checkUpdate) {
        runOnUiThread(() -> {
            NeedUpdateDialog dialog = new NeedUpdateDialog(context, checkUpdate, (dialog1) -> {
                PermissionActivity.start(getViewContext(), PermissionUtil.getDownloadPermission(), PermissionUtil.REQUEST_CHECK_DOWNLOAD_PERMISSION);
                dialog1.dismiss();
                //开始下载

            });
            dialog.show();
        });

    }

    private void downloadApk() {
        DownloadUtil.downloadUpdateApk(new DownloadCallBack() {
            @Override
            public void onDownloadStart(DownloadFileEntity downloadFileEntity) {
                NotificationFactory.createUpdateApkNotification(context, HostActivity.class, 0);

            }

            @Override
            public void updateProgress(float progress) {

                NotificationFactory.createUpdateApkNotification(context, HostActivity.class, (int) progress);

            }

            @Override
            public void onDownloadFinish(DownloadFileEntity downloadFileEntity) {
                NotificationHelper.getInstance().getNotificationManager().cancel(NotificationUtil.ID_UPDATE_APK);
                FileUtil.installApk(context, downloadFileEntity);
            }

            @Override
            public void onFailure(String reason) {

            }
        });


    }

    @Override
    public void needForceUpdate(CheckUpdate checkUpdate) {
        runOnUiThread(() -> {
            NeedUpdateDialog dialog = new NeedUpdateDialog(context, checkUpdate, (dialog1) -> {
                //发送通知，下载
                PermissionActivity.start(getViewContext(), PermissionUtil.getDownloadPermission(), PermissionUtil.REQUEST_CHECK_DOWNLOAD_PERMISSION);
                dialog1.dismiss();
            });

            dialog.show();

        });

    }


}
