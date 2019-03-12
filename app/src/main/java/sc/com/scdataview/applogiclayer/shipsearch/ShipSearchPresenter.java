package sc.com.scdataview.applogiclayer.shipsearch;

import sc.com.data_provider.entity.DatabaseEntity;
import sc.com.data_provider.entity.ShipNameEntity;
import sc.com.data_provider.provider.impl.ShipNameProvider;

/**
 * Created by Eric on 2018/8/2.
 */
public class ShipSearchPresenter implements ShipSearchContract.Presenter {
    private ShipSearchContract.View view;

    public ShipSearchPresenter(ShipSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void searchShipLocal(String searchText) {
        view.clearSearchLocal();
        ShipNameProvider.getInstance().queryLikeCh(DatabaseEntity.getShipNameQueryLikeEntity(searchText), result_ch -> {
            view.appendSearchLocal(result_ch);
        });
        ShipNameProvider.getInstance().queryLikeEn(DatabaseEntity.getShipNameQueryLikeEntity(searchText), query_result -> {
            view.appendSearchLocal(query_result);
        });

    }



    @Override
    public void onStart() {

    }
}
