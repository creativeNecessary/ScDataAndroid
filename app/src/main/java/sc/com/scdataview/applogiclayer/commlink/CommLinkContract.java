package sc.com.scdataview.applogiclayer.commlink;

import java.util.List;

import sc.com.commonlib.api.mvp.BasePresenter;
import sc.com.commonlib.api.mvp.BaseView;
import sc.com.commonlib.impl.http.entity.CommLinkItem;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkContract {

    public interface View extends BaseView {
        void onPageChange(int pageNum);
        void showCommLinkList(List<CommLinkItem> link_list);
        void setLoadingIndicator(boolean show);



    }


    public interface Presenter extends BasePresenter {
        void getCommLinkList(int pageNum);
        void showCommLinkDetail(CommLinkItem comm_link);


    }
}
