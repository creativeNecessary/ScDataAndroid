package sc.com.scdataview.applogiclayer.shipinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;

import java.text.DecimalFormat;
import java.util.Collections;

import sc.com.commonlib.CommonKit;
import sc.com.commonlib.common.constant.Extras;
import sc.com.commonlib.common.glide.GlideApp;
import sc.com.commonlib.common.util.HttpUtil;
import sc.com.commonlib.common.util.StatusBarUtil;
import sc.com.commonlib.impl.http.entity.ShipDetail;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.data_provider.entity.ShipNameEntity;
import sc.com.scdataview.R;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/3/16.
 */

public class ShipDetailsActivity extends CommonBaseActivity implements ShipDetailsContract.View {

    private Banner ship_image_banner;
    private RecyclerView detail_recycler_view;
    private CollapsingToolbarLayout toolbar_layout;
    private ShipDetailsContract.Presenter presenter;
    private ShipDetail shipDetail;
    private ShipDetailAdapter detailAdapter;
    private String ship_name;
    private int ship_id;

    private View headView;

//    private FloatingActionButton show_model_button;


    public static void start(Context context, ShipEntity entity) {
        Intent starter = new Intent(context, ShipDetailsActivity.class);
        starter.putExtra(Extras.DATA, entity);
        starter.putExtra(Extras.SHIPDETAIL_FROM, Extras.SHIPDETAIL_FROM_LIST);

        context.startActivity(starter);
    }

    public static void start(Context context, ShipNameEntity entity) {
        Intent starter = new Intent(context, ShipDetailsActivity.class);
        starter.putExtra(Extras.DATA, entity);
        starter.putExtra(Extras.SHIPDETAIL_FROM, Extras.SHIPDETAIL_FROM_SEARCH);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        setToolBarTitle(ship_name);
        ship_image_banner = findViewById(R.id.ship_image_banner);
//        show_model_button = findViewById(R.id.show_model_button);
        detailAdapter = new ShipDetailAdapter(Collections.emptyList());
        detail_recycler_view = findViewById(R.id.detail_recycler_view);
        detail_recycler_view.setLayoutManager(new GridLayoutManager(this, 3));
        detail_recycler_view.setAdapter(detailAdapter);

        toolbar_layout = findViewById(R.id.toolbar_layout);
        toolbar_layout.setCollapsedTitleTextColor(Color.WHITE);
        toolbar_layout.setExpandedTitleColor(Color.TRANSPARENT);
        ship_image_banner.setImageLoader(new GlideImageLoader());
        refresh_layout.setEnabled(false);

        headView = getLayoutInflater().inflate(R.layout.headview_ship_details, (ViewGroup) detail_recycler_view.getParent(), false);
        detailAdapter.addHeaderView(headView);
        initListener();

    }

    @Override
    public void onNetWorkReady() {
        presenter = new ShipDetailPresenter(this, ship_id);
        presenter.onStart();
        presenter.getShipDetail();

    }

    private void initListener() {
//        show_model_button.setOnClickListener(v -> presenter.showShipModel(shipDetail));

    }

    private void initHeadView() {

        ImageView manufacturer_image = headView.findViewById(R.id.manufacturer_image);
        TextView manufacturer_text = headView.findViewById(R.id.manufacturer_text);
        TextView name_text = headView.findViewById(R.id.name_text);
        TextView name_ch_text = headView.findViewById(R.id.name_ch_text);
        TextView size_text = headView.findViewById(R.id.size_text);
        TextView focus_text = headView.findViewById(R.id.focus_text);
        TextView max_crew_text = headView.findViewById(R.id.max_crew_text);
        TextView cargo_capacity_text = headView.findViewById(R.id.cargo_capacity_text);
        TextView mass_text = headView.findViewById(R.id.mass_text);
        TextView scm_speed_text = headView.findViewById(R.id.scm_speed_text);
        TextView afterburner_speed_text = headView.findViewById(R.id.afterburner_speed_text);
        TextView length_text = headView.findViewById(R.id.length_text);
        TextView beam_text = headView.findViewById(R.id.beam_text);
        TextView height_text = headView.findViewById(R.id.height_text);
        TextView price_text = headView.findViewById(R.id.price_text);
        ship_image_banner.setImages(shipDetail.operateImageUrlList());
        ship_image_banner.start();
        GlideApp.with(this)
                .load(HttpUtil.getInstance().getRobertSpaceUrl(shipDetail.getManufacturer().getSource_url()))
                .thumbnail(0.1f)
                .into(manufacturer_image);
        manufacturer_text.setText(shipDetail.getManufacturer().getName());
        name_text.setText(shipDetail.getName());
        name_ch_text.setText(shipDetail.getName_ch());
        size_text.setText(shipDetail.getSize());
        focus_text.setText(shipDetail.getFocus());
        max_crew_text.setText(shipDetail.getMax_crew());
        cargo_capacity_text.setText(shipDetail.getCargocapacity());
        mass_text.setText(shipDetail.getMass());
        scm_speed_text.setText(shipDetail.getScm_speed());
        afterburner_speed_text.setText(shipDetail.getAfterburner_speed());
        length_text.setText(shipDetail.getLength());
        beam_text.setText(shipDetail.getBeam());
        height_text.setText(shipDetail.getHeight());
        if(!TextUtils.isEmpty(shipDetail.getShip_price())){
            StringBuilder builder = new StringBuilder("$");
            builder.append(shipDetail.getShip_price());
            builder.append("/¥");
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            builder.append( decimalFormat.format(Float.valueOf(shipDetail.getShip_price()) * CommonKit.USD2CNY));
            price_text.setText(builder);
        }


    }

    @Override
    protected void beforeSetContentView() {
        StatusBarUtil.setTransparent(this);
        if (getActivityFrom() == Extras.SHIPDETAIL_FROM_LIST) {
            ShipEntity entity = (ShipEntity) getIntent().getSerializableExtra(Extras.DATA);
            ship_name = entity.getName();
            ship_id = entity.getId();
        } else if (getActivityFrom() == Extras.SHIPDETAIL_FROM_SEARCH) {
            ShipNameEntity nameEntity = (ShipNameEntity) getIntent().getSerializableExtra(Extras.DATA);
            ship_name = nameEntity.getShip_name();
            ship_id = Integer.valueOf(nameEntity.getShip_id());
        }


    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_ship_details;
    }


    @Override
//    @SuppressLint("RestrictedApi")
    public void showShipDetail(ShipDetail shipDetail) {
        runOnUiThread(() -> {
            Logger.d(shipDetail.getShip_price());
            this.shipDetail = shipDetail;
//            if (TextUtils.isEmpty(shipDetail.getModel3d_url())) {
//                show_model_button.setVisibility(View.GONE);
//            }
            initHeadView();
            detailAdapter.setNewData(shipDetail.getShip_equipment());

        });

    }

    @Override
    public void setLoadingIndicator(boolean show) {
        refresh_layout.post(() -> refresh_layout.setRefreshing(show));
    }

    @Override
    public int getActivityFrom() {
        return getIntent().getIntExtra(Extras.SHIPDETAIL_FROM, -1);
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}


//        IjkMediaPlayer.loadLibrariesOnce(null);
//        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
//        mediaController = new AndroidMediaController(this,false);
//        video_view = findViewById(R.id.video_view);
//        video_view.setMediaController(mediaController);
//        video_view.setVideoPath(files[0].getAbsolutePath());
//        video_view.start();