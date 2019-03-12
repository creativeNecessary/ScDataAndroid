package sc.com.scdataview.applogiclayer.shipfilter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

import sc.com.commonlib.common.constant.Extras;
import sc.com.commonlib.common.util.StarCitizenCode;
import sc.com.commonlib.impl.http.entity.FilterType;
import sc.com.scdataview.R;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/6/5.
 */
public class ShipFilterActivity extends CommonBaseActivity implements ShipFilterContract.View{
    private ShipFilterContract.Presenter presenter;
    private RecyclerView filter_recycler;
    private View cancel_view ,clear_text;
    private TextView now_choose;
    private FilterType now_choose_type;
    private ShipFilterAdapter shipFilterAdapter;

    public static void start(Context context,FilterType filterType) {
        Intent starter = new Intent(context, ShipFilterActivity.class);
        starter.putExtra(Extras.DATA,filterType);
        ((Activity)context).startActivityForResult(starter, StarCitizenCode.REQUEST_SHIP_FILTER);
    }

    @Override
    public void initView() {
        now_choose_type = (FilterType) getIntent().getSerializableExtra(Extras.DATA);
        refresh_layout.setEnabled(false);
        filter_recycler = findViewById(R.id.filter_recycler);
        cancel_view = findViewById(R.id.cancel_view);
        clear_text = findViewById(R.id.clear_text);
        now_choose = findViewById(R.id.now_choose);
        if(now_choose_type == null){
            now_choose.setText(R.string.now_choose);
        }else {
            now_choose.setText(now_choose_type.getType_ch());

        }


    }

    @Override
    public void onNetWorkReady() {
        presenter = new ShipFilterPresenter(this);
        presenter.onStart();
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_ship_filter;
    }

    @Override
    public void setLoadingIndicator(boolean show) {
        refresh_layout.post(()->refresh_layout.setRefreshing(show));
    }

    @Override
    public void initFilterRecycler() {
        shipFilterAdapter = new ShipFilterAdapter(Collections.emptyList());
        filter_recycler.setLayoutManager(new GridLayoutManager(getViewContext(),4));
        filter_recycler.setAdapter(shipFilterAdapter);
    }

    @Override
    public void initListener() {
        cancel_view.setOnClickListener((view)->{
            setResult(StarCitizenCode.RESPONSE_SHIP_FILTER_CANCEL);
            finish();

        });

        clear_text.setOnClickListener((view)->{
            setResult(StarCitizenCode.RESPONSE_SHIP_FILTER_CLEAR);
            finish();


        });
        filter_recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                FilterType filterType = (FilterType) adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra(Extras.DATA,filterType);
                setResult(StarCitizenCode.RESPONSE_SHIP_FILTER_CHOOSE,intent);
                finish();

            }
        });
    }

    @Override
    public void showFilter(List<FilterType> filterTypeList) {
        runOnUiThread(()->shipFilterAdapter.setNewData(filterTypeList));
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
