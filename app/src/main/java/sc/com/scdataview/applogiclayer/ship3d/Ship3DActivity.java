package sc.com.scdataview.applogiclayer.ship3d;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.io.File;

import sc.com.sc3dview.view.ShowModelView;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/3/16.
 */

public class Ship3DActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private ShowModelView ship_3d_view;


    public static void start(Context context) {
        Intent starter = new Intent(context, Ship3DActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship3d);
        ship_3d_view = findViewById(R.id.ship_3d_view);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        File file = new File( Environment.getExternalStorageDirectory().getAbsolutePath()+"/models");
        boolean isdic = file.isDirectory();
        File [] files = file.listFiles();
        Logger.d(isdic);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(permissions.length != grantResults.length){
            finish();
        }
    }
}
