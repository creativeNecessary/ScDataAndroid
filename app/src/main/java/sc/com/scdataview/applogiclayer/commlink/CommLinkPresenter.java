package sc.com.scdataview.applogiclayer.commlink;

import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.impl.http.entity.CommLinkItem;
import sc.com.commonlib.impl.http.request.GetCommLinkListRequest;
import sc.com.commonlib.impl.http.response.GetCommLinkListResponse;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkPresenter implements CommLinkContract.Presenter {

    private CommLinkContract.View view;
    public static final String TYPE_GALAXY_GUIDE = "galactic_guide";
    private String type ;

    public CommLinkPresenter(CommLinkContract.View view,String type) {
        this.type = type;
        this.view = view;
    }
    @Override
    public void getCommLinkList(int pageNum) {
        GetCommLinkListRequest request = new GetCommLinkListRequest(type,pageNum);
        if(pageNum == 0){
            view.setLoadingIndicator(true);
        }
        request.execute(new HttpCallBack<GetCommLinkListResponse>() {
            @Override
            public void onFailure() {
                view.setLoadingIndicator(false);
            }

            @Override
            public void onResponse(GetCommLinkListResponse netResponse) {
                if(pageNum == 0){
                    view.setLoadingIndicator(false);
                }
                view.showCommLinkList(netResponse.getCommLinkItems());
            }
        });

    }

    @Override
    public void showCommLinkDetail(CommLinkItem linkItem) {
        CommLinkDetailActivity.start(view.getViewContext(),linkItem);
    }



    @Override
    public void onStart() {

    }
}
