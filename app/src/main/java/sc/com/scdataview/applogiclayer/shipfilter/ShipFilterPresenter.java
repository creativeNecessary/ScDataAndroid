package sc.com.scdataview.applogiclayer.shipfilter;

import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.impl.http.request.GetFilterDataRequest;
import sc.com.commonlib.impl.http.response.GetFilterDataResponse;

/**
 * Created by Eric on 2018/6/5.
 */
public class ShipFilterPresenter implements ShipFilterContract.Presenter {
    private ShipFilterContract.View view;

    public ShipFilterPresenter(ShipFilterContract.View view) {
        this.view = view;
    }

    @Override
    public void getFilterData() {
        view.setLoadingIndicator(true);
        GetFilterDataRequest request = new GetFilterDataRequest();
        request.execute(new HttpCallBack<GetFilterDataResponse>() {
            @Override
            public void onFailure() {
                view.setLoadingIndicator(false);

            }

            @Override
            public void onResponse(GetFilterDataResponse netResponse) {
                view.setLoadingIndicator(false);
                view.showFilter(netResponse.getFilterTypeList());
            }
        });

    }

    @Override
    public void onStart() {
        view.initFilterRecycler();
        view.initListener();
        getFilterData();
    }
}
