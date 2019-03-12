package sc.com.scdataview.applogiclayer.shipinfo;

import sc.com.commonlib.impl.http.entity.ShipDetail;
import sc.com.commonlib.api.mvp.BasePresenter;
import sc.com.commonlib.api.mvp.BaseView;

/**
 * Created by Eric on 2018/3/26.
 */

public class ShipDetailsContract {
    public interface View extends BaseView {
        void showShipDetail(ShipDetail shipDetail);
        void setLoadingIndicator(boolean show);
        int getActivityFrom();

    }

    public interface Presenter extends BasePresenter {
        void getShipDetail();
        void showShipModel(ShipDetail shipDetail);


    }
}
