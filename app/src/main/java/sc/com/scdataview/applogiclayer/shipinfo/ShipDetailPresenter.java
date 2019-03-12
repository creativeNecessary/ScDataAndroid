package sc.com.scdataview.applogiclayer.shipinfo;


import sc.com.commonlib.api.http.HttpCallBack;
import sc.com.commonlib.impl.http.entity.ShipDetail;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.commonlib.impl.http.request.GetShipDetailRequest;
import sc.com.commonlib.impl.http.response.GetShipDetailResponse;
import sc.com.scdataview.applogiclayer.ship3d.DownloadModelActivity;

/**
 * Created by Eric on 2018/3/26.
 */

public class ShipDetailPresenter implements ShipDetailsContract.Presenter {
    private ShipDetailsContract.View view;
    private int ship_id;


    public ShipDetailPresenter(ShipDetailsContract.View view, int ship_id) {
        this.view = view;
        this.ship_id = ship_id;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void getShipDetail() {
        //
        view.setLoadingIndicator(true);
        GetShipDetailRequest request = new GetShipDetailRequest(ship_id);
        request.execute(new HttpCallBack<GetShipDetailResponse>() {
            @Override
            public void onFailure() {
                view.setLoadingIndicator(false);
            }

            @Override
            public void onResponse(GetShipDetailResponse netResponse) {
                view.setLoadingIndicator(false);
                view.showShipDetail(netResponse.getShipDetail());

            }
        });
    }

    @Override
    public void showShipModel(ShipDetail shipDetail) {

        DownloadModelActivity.start(view.getViewContext(),shipDetail);

    }
}
