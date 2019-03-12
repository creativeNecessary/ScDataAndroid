package sc.com.scdataview.applogiclayer.shipsearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.data_provider.entity.ShipNameEntity;
import sc.com.scdataview.R;
import sc.com.scdataview.applogiclayer.shipinfo.ShipDetailsActivity;
import sc.com.scdataview.applogiclayer.shiplist.ShipListAdapter;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/8/2.
 */
public class ShipSearchActivity extends CommonBaseActivity implements ShipSearchContract.View {

    private EditText search_edit;
    private RecyclerView search_display;
    private ShipLocalSearchAdapter localSearchAdapter;
    private List<ShipNameEntity> local_data;
    private ShipSearchPresenter presenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShipSearchActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        setToolBarTitle("搜索");
        local_data = new ArrayList<>();
        search_edit = findViewById(R.id.search_edit);
        search_display = findViewById(R.id.search_display);
        initRecycler();
        initListener();

    }

    @Override
    public void onNetWorkReady() {
        presenter = new ShipSearchPresenter(this);

    }

    @Override
    public int getContentViewId() {

        return R.layout.activity_ship_search;
    }


    @Override
    public void appendSearchLocal(List<ShipNameEntity> shipList) {
        runOnUiThread(() -> {
            List<ShipNameEntity> removeList = new ArrayList<>();

            for (ShipNameEntity searchEntity : shipList) {

                for (ShipNameEntity localEntity : local_data) {

                    if (searchEntity.getShip_id().equals(localEntity.getShip_id())) {
                        removeList.add(searchEntity);
                        break;
                    }

                }
            }
            shipList.removeAll(removeList);
            local_data.addAll(shipList);
            localSearchAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void clearSearchLocal() {
        local_data.clear();
        localSearchAdapter.notifyDataSetChanged();
    }


    @Override
    public void initRecycler() {
        search_display.setLayoutManager(new LinearLayoutManager(context));

        localSearchAdapter = new ShipLocalSearchAdapter(local_data);

        search_display.setAdapter(localSearchAdapter);

    }

    @Override
    public void initListener() {
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                presenter.searchShipLocal(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search_display.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShipDetailsActivity.start(getViewContext(), (ShipNameEntity) adapter.getItem(position));
            }
        });

    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
