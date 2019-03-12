package sc.com.scdataview.applogiclayer.commlink;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

import sc.com.commonlib.common.constant.Extras;
import sc.com.commonlib.impl.http.entity.CommLinkItem;
import sc.com.scdataview.R;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkListActivity extends CommonBaseActivity implements CommLinkContract.View {
    private RecyclerView comm_link_list_recycler_view;
    private CommLinkListAdapter adapter;
    private int pageNum = 0;
    private CommLinkPresenter commLinkPresenter;
    private String type;

    public static void start(Context context,String type) {
        Intent starter = new Intent(context, CommLinkListActivity.class);
        starter.putExtra(Extras.DATA,type);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        type = getIntent().getStringExtra(Extras.DATA);
        setToolBarTitle(R.string.galaxy_guide);
        comm_link_list_recycler_view = findViewById(R.id.comm_link_list_recycler_view);
        comm_link_list_recycler_view.setLayoutManager(new LinearLayoutManager(getViewContext()));
        adapter = new CommLinkListAdapter(Collections.emptyList());
        comm_link_list_recycler_view.setAdapter(adapter);
        initListener();

    }

    @Override
    public void onNetWorkReady() {
        commLinkPresenter = new CommLinkPresenter(this,type);
        commLinkPresenter.getCommLinkList(0);
    }

    private void initListener() {
        adapter.setOnLoadMoreListener(() -> {
            pageNum++;
            onPageChange(pageNum);
        }, comm_link_list_recycler_view);
        refresh_layout.setOnRefreshListener(() -> {
            pageNum = 0;
            onPageChange(pageNum);
        });
        comm_link_list_recycler_view.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                commLinkPresenter.showCommLinkDetail((CommLinkItem) adapter.getItem(position));
            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_comm_link_list;
    }

    @Override
    public void onPageChange(int pageNum) {
        commLinkPresenter.getCommLinkList(pageNum);
    }

    @Override
    public void showCommLinkList(List<CommLinkItem> link_list) {
        runOnUiThread(()->{
            if(pageNum == 0){
                adapter.setNewData(link_list);
            }else {
                adapter.loadMoreComplete();
                adapter.addData(link_list);
                if(link_list.size()<10){
                    adapter.loadMoreEnd(true);
                }
            }

        });
    }

    @Override
    public void setLoadingIndicator(boolean show) {
        refresh_layout.post(()-> refresh_layout.setRefreshing(show));
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
