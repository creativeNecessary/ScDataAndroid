package sc.com.scdataview.applogiclayer.shipsearch;

import java.util.List;

import sc.com.commonlib.api.mvp.BasePresenter;
import sc.com.commonlib.api.mvp.BaseView;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.data_provider.entity.ShipNameEntity;

/**
 * Created by Eric on 2018/8/2.
 */
public class ShipSearchContract {


    public interface View extends BaseView {

        void appendSearchLocal(List<ShipNameEntity> shipList);

        void clearSearchLocal();

        void initRecycler();

        void initListener();

    }


    public interface Presenter extends BasePresenter {

        void searchShipLocal(String searchText);

    }
}
