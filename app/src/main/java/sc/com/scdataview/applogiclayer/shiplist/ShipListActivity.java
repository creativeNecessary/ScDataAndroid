package sc.com.scdataview.applogiclayer.shiplist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.Collections;
import java.util.List;

import sc.com.commonlib.common.constant.Extras;
import sc.com.commonlib.common.util.StarCitizenCode;
import sc.com.commonlib.impl.http.entity.FilterType;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.scdataview.R;
import sc.com.scdataview.applogiclayer.commlink.CommLinkDetailActivity;
import sc.com.scdataview.applogiclayer.shipfilter.ShipFilterActivity;
import sc.com.scdataview.applogiclayer.shipsearch.ShipSearchActivity;
import sc.com.scdataview.base.CommonBaseActivity;
import sc.com.scdataview.base.CommonBaseFragment;

/**
 * Created by Eric on 2018/3/12.
 */

public class ShipListActivity extends CommonBaseActivity implements ShipListContract.View {
    private ShipListContract.Presenter presenter;
    private RecyclerView ship_list_recycler_view;
    private SwipeRefreshLayout refresh_layout;
    private ShipListAdapter list_adapter;
    private TextView filter_text;
    private TextView search_text;
    private FilterType filterType;
    private int pageNum = 0;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShipListActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {

        refresh_layout = findViewById(R.id.refresh_layout);
        filter_text = findViewById(R.id.filter_text);
        search_text = findViewById(R.id.search_text);
        ship_list_recycler_view = findViewById(R.id.ship_list_recycler_view);
    }

    @Override
    public void onNetWorkReady() {
        presenter = new ShipListPresenter(this);
        presenter.onStart();

    }

    @Override
    public void initShipListRecycler() {
        ship_list_recycler_view.setLayoutManager(new LinearLayoutManager(getViewContext()));
        list_adapter = new ShipListAdapter(Collections.emptyList());
        list_adapter.openLoadAnimation();
        list_adapter.setPreLoadNumber(10);
        ship_list_recycler_view.setAdapter(list_adapter);

    }

    @Override
    public void initListener() {
        refresh_layout.setOnRefreshListener(() -> {
            pageNum = 0;
            onPageChange(pageNum);
        });

        list_adapter.setOnLoadMoreListener(() -> {
            pageNum++;
            onPageChange(pageNum);
        }, ship_list_recycler_view);

        list_adapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            if (position < adapter.getItemCount()) {
                presenter.showShipDetail(list_adapter.getItem(position));
            }

        });
        filter_text.setOnClickListener((view)-> ShipFilterActivity.start(getViewContext(),filterType));
        search_text.setOnClickListener(v -> ShipSearchActivity.start(getViewContext()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == StarCitizenCode.REQUEST_SHIP_FILTER){
            if (resultCode == StarCitizenCode.RESPONSE_SHIP_FILTER_CLEAR){
                filterType = null;
                pageNum = 0;
                presenter.getShips(pageNum,filterType);
                filter_text.setText("筛选");

            }else if (resultCode == StarCitizenCode.RESPONSE_SHIP_FILTER_CHOOSE){
                filterType = (FilterType) data.getSerializableExtra(Extras.DATA);
                pageNum = 0;
                presenter.getShips(pageNum,filterType);
                filter_text.setText(filterType.getType_ch());


            }

        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_ship_list;
    }

    @Override
    public void onPageChange(int pageNum) {
        presenter.getShips(pageNum,filterType);

    }



    @Override
    public void showShips(List<ShipEntity> shipList) {
       runOnUiThread(() -> {
            if (pageNum == 0) {
                list_adapter.setNewData(shipList);
            } else {
                list_adapter.loadMoreComplete();
                list_adapter.addData(shipList);
                if(shipList.size()<10){
                    list_adapter.loadMoreEnd(true);
                }
            }
        });

    }

    @Override
    public void setLoadingIndicator(boolean show) {
        refresh_layout.post(() -> refresh_layout.setRefreshing(show));

    }



    @Override
    public Context getViewContext() {
        return this;
    }
}
