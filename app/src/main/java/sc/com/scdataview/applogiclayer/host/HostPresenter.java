package sc.com.scdataview.applogiclayer.host;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import sc.com.commonlib.CommonKit;
import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.impl.http.entity.ShipName;
import sc.com.commonlib.impl.http.request.CheckUpdateRequest;
import sc.com.commonlib.impl.http.request.GetAllShipNameListRequest;
import sc.com.commonlib.impl.http.request.GetFloatingRateRequest;
import sc.com.commonlib.impl.http.response.CheckUpdateResponse;
import sc.com.commonlib.impl.http.response.GetAllShipNameListResponse;
import sc.com.commonlib.impl.http.response.GetFloatingRateResponse;
import sc.com.data_provider.provider.impl.ShipNameProvider;

/**
 * Created by Eric on 2018/5/21.
 */
public class HostPresenter implements HostContract.Presenter {

    private HostContract.View view;

    public HostPresenter(HostContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.initItemRecycler();
        view.initListener();
        checkUpdate();
        initShipNameData();
        //获取美元兑人民币汇率
        getFloatingRate();

    }

    @Override
    public void checkUpdate() {
        PackageManager packageManager = view.getViewContext().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(view.getViewContext().getPackageName(), 0);
            int versionCode =  packInfo.versionCode;
            CheckUpdateRequest checkUpdateRequest = new CheckUpdateRequest(versionCode);
            checkUpdateRequest.execute(new HttpCallBack<CheckUpdateResponse>() {
                @Override
                public void onFailure() {

                }

                @Override
                public void onResponse(CheckUpdateResponse netResponse) {
                    if (netResponse.getCheckUpdate().needForceUpdate()) {
                        view.needForceUpdate(netResponse.getCheckUpdate());
                    } else if (netResponse.getCheckUpdate().needUpdate()) {
                        view.needUpdate(netResponse.getCheckUpdate());

                    }
                }
            });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getFloatingRate() {
        GetFloatingRateRequest rateRequest = new GetFloatingRateRequest();
        rateRequest.execute(new HttpCallBack<GetFloatingRateResponse>() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onResponse(GetFloatingRateResponse netResponse) {
                if(netResponse == null){
                    CommonKit.USD2CNY = 1;
                    return;
                }
                if(netResponse.getFloatingRate() == null){
                    CommonKit.USD2CNY = 1;
                    return;
                }
                CommonKit.USD2CNY = netResponse.getFloatingRate().getPrice();

            }
        });
    }

    @Override
    public void initShipNameData() {
        GetAllShipNameListRequest request = new GetAllShipNameListRequest();
        request.execute(new HttpCallBack<GetAllShipNameListResponse>() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onResponse(GetAllShipNameListResponse netResponse) {
                //这里进行数据更新
                if (netResponse.getData() == null) {
                    return;
                }
                if (netResponse.getData().size() == 0) {
                    return;
                }
                ShipNameProvider.getInstance().deleteAll(delete_result -> {
                    for (ShipName ship_name : netResponse.getData()) {
                        ShipNameProvider.getInstance().insert(ship_name.getDataBaseEntity(), insert_result -> {});
                    }
                });
            }
        });

    }


}

