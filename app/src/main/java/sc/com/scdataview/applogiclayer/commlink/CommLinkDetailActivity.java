package sc.com.scdataview.applogiclayer.commlink;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import sc.com.commonlib.common.constant.Extras;
import sc.com.commonlib.impl.http.entity.CommLinkDetailItem;
import sc.com.commonlib.impl.http.entity.CommLinkItem;
import sc.com.scdataview.R;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkDetailActivity extends CommonBaseActivity implements CommLinkDetailContract.View {

    private CommLinkItem commLinkItem;
    private RecyclerView data_recycler;
    private CommLinkDetailAdapter adapter;

    public static void start(Context context, CommLinkItem commLinkItem) {
        Intent starter = new Intent(context, CommLinkDetailActivity.class);
        starter.putExtra(Extras.DATA, commLinkItem);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        data_recycler = findViewById(R.id.data_recycler);
        data_recycler.setLayoutManager(new LinearLayoutManager(getViewContext()));
        refresh_layout.setEnabled(false);
        adapter = new CommLinkDetailAdapter(Collections.emptyList());
        data_recycler.setAdapter(adapter);
        CommLinkDetailPresenter presenter = new CommLinkDetailPresenter(this);
        commLinkItem = (CommLinkItem) getIntent().getSerializableExtra(Extras.DATA);
        setToolBarTitle(commLinkItem.getTitle());
        presenter.getCommLinkDetail(commLinkItem.getId());
    }

    @Override
    public void onNetWorkReady() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_comm_link_detail;
    }

    @Override
    public void setLoadingIndicator(boolean show) {
        refresh_layout.post(() -> refresh_layout.setRefreshing(show));
    }

    @Override
    public void showDetail(List<CommLinkDetailItem> commLinkDetailItems) {
        runOnUiThread(() -> adapter.setNewData(commLinkDetailItems));

    }


    @Override
    public Context getViewContext() {
        return this;
    }
}
