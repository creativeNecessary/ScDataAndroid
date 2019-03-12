package sc.com.scdataview.applogiclayer.commlink;

import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.impl.http.request.GetCommLinkDetailRequest;
import sc.com.commonlib.impl.http.response.GetCommLinkDetailResponse;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkDetailPresenter implements CommLinkDetailContract.Presenter {

    private CommLinkDetailContract.View view;

    public CommLinkDetailContract.View getView() {
        return view;
    }

    public CommLinkDetailPresenter(CommLinkDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getCommLinkDetail(int id) {
        view.setLoadingIndicator(true);
        GetCommLinkDetailRequest request = new GetCommLinkDetailRequest(id);
        request.execute(new HttpCallBack<GetCommLinkDetailResponse>() {
            @Override
            public void onFailure() {
                view.setLoadingIndicator(false);
            }

            @Override
            public void onResponse(GetCommLinkDetailResponse netResponse) {
                view.setLoadingIndicator(false);
                view.showDetail(netResponse.getCommLinkDetailItems());

            }
        });

    }

    @Override
    public void onStart() {

    }
}
