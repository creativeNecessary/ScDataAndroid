package sc.com.scdataview.applogiclayer.shipfilter;

import java.util.List;

import sc.com.commonlib.api.mvp.BasePresenter;
import sc.com.commonlib.api.mvp.BaseView;
import sc.com.commonlib.impl.http.entity.CheckUpdate;
import sc.com.commonlib.impl.http.entity.FilterType;

/**
 * Created by Eric on 2018/6/5.
 */
public class ShipFilterContract {

    interface View extends BaseView {
        void setLoadingIndicator(boolean show);
        void initFilterRecycler();
        void initListener();
        void showFilter(List<FilterType> filterTypeList);
    }

    interface Presenter extends BasePresenter {

        void getFilterData();


    }


}
