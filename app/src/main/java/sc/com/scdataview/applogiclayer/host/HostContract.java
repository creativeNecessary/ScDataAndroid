package sc.com.scdataview.applogiclayer.host;

import java.util.List;

import sc.com.commonlib.api.mvp.BasePresenter;
import sc.com.commonlib.api.mvp.BaseView;
import sc.com.commonlib.impl.http.entity.CheckUpdate;
import sc.com.commonlib.impl.http.entity.ShipEntity;

/**
 * Created by Eric on 2018/3/12.
 */

public class HostContract {

    interface View extends BaseView {

        void needUpdate(CheckUpdate checkUpdate);
        void needForceUpdate(CheckUpdate checkUpdate);

        void initItemRecycler();
        void initListener();
    }

    interface Presenter extends BasePresenter{

       void checkUpdate();
       void getFloatingRate();
       void initShipNameData();


    }

}
