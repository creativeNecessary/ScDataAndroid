package sc.com.scdataview.applogiclayer.commlink;

import java.util.List;

import sc.com.commonlib.api.mvp.BasePresenter;
import sc.com.commonlib.api.mvp.BaseView;
import sc.com.commonlib.impl.http.entity.CommLinkDetailItem;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkDetailContract {

    public interface View extends BaseView {

        void setLoadingIndicator(boolean show);
        void showDetail(List<CommLinkDetailItem> commLinkDetailItems);


    }


    public interface Presenter extends BasePresenter {
        void getCommLinkDetail(int id);


    }
}
