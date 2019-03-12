package sc.com.scdataview.applogiclayer.shiplist;


import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.impl.http.entity.FilterType;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.commonlib.impl.http.request.GetShipListRequest;
import sc.com.commonlib.impl.http.response.GetShipListResponse;
import sc.com.scdataview.applogiclayer.shipinfo.ShipDetailsActivity;

/**
 * Created by Eric on 2018/3/12.
 */

public class ShipListPresenter implements ShipListContract.Presenter {

    private ShipListContract.View view;

    public ShipListPresenter(ShipListContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.initShipListRecycler();
        view.initListener();
        getShips(0,null);
    }


    @Override
    public void getShips(int pageNum, FilterType filterType) {
        GetShipListRequest request = new GetShipListRequest(pageNum,filterType);
        request.execute(new HttpCallBack<GetShipListResponse>() {
            @Override
            public void onFailure() {
                view.setLoadingIndicator(false);
            }

            @Override
            public void onResponse(GetShipListResponse netResponse) {
                view.setLoadingIndicator(false);
                view.showShips(netResponse.getShipEntityList());
            }
        });
    }

    @Override
    public void showShipDetail(ShipEntity shipEntity) {
        ShipDetailsActivity.start(view.getViewContext(),shipEntity);
    }
}
