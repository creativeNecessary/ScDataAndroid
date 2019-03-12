package sc.com.scdataview.applogiclayer.shiplist;


import java.util.List;

import sc.com.commonlib.impl.http.entity.FilterType;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.commonlib.api.mvp.BasePresenter;
import sc.com.commonlib.api.mvp.BaseView;

/**
 * Created by Eric on 2018/3/12.
 */

public class ShipListContract {


    public interface View extends BaseView{
        void onPageChange(int pageNum);
        void showShips(List<ShipEntity> shipList);
        void setLoadingIndicator(boolean show);

        void initShipListRecycler();
        void initListener();

    }


    public interface Presenter extends BasePresenter {
        void getShips(int pageNum , FilterType filterType);
        void showShipDetail(ShipEntity shipEntity);


    }

}
