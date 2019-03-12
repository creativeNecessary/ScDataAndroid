package sc.com.scdataview.applogiclayer.ship3d;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import sc.com.commonlib.api.http.DownloadCallBack;
import sc.com.commonlib.common.constant.Extras;
import sc.com.commonlib.impl.http.entity.DownloadFileEntity;
import sc.com.commonlib.impl.http.entity.ShipDetail;
import sc.com.commonlib.impl.http.entity.ShipEntity;
import sc.com.scdataview.R;
import sc.com.scdataview.base.CommonBaseActivity;

/**
 * Created by Eric on 2018/4/24.
 */
public class DownloadModelActivity extends CommonBaseActivity   {
    private ShipDetail shipDetail;
    private TextView progress_text;

    public static void start(Context context, ShipDetail shipDetail) {
        Intent starter = new Intent(context, DownloadModelActivity.class);
        starter.putExtra(Extras.DATA, shipDetail);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        progress_text = findViewById(R.id.progress_text);
        shipDetail = (ShipDetail) getIntent().getSerializableExtra(Extras.DATA);
        if (shipDetail == null) {
            //
            finish();
        }

        downModelFile();

    }

    @Override
    public void onNetWorkReady() {

    }

    private void downModelFile() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_download_model;
    }


    @Override
    public Context getViewContext() {
        return this;
    }
}
