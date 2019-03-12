package sc.com.sc3dview;

import android.content.Context;
import android.net.Uri;
import android.util.Log;



import java.io.File;
import java.io.IOException;

import sc.com.sc3dview.api.ModelLoaderListener;
import sc.com.sc3dview.model.ModelObject;
import sc.com.sc3dview.model.ObjObject;
import sc.com.sc3dview.model.StlObject;
import sc.com.sc3dview.utils.FileUtils;


public class ModelFactory {
    private static final String TAG = "ModelFactory";

    private static byte[] modelBytes = null;
    private static String modelType;
    private static ModelObject modelObject;

    public static void decodeFile(Context context, String filePath, ModelLoaderListener listener) {

        if (FileUtils.isNullString(filePath)) {
            throw new IllegalArgumentException("filePath can't be null!");
        }

        modelType = FileUtils.getType(filePath).toLowerCase();
        File file = new File(filePath);
        try {
            modelBytes = FileUtils.getFileBytes(context, Uri.fromFile(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (modelBytes == null) {
            listener.loaderFailure();
            return;
        }

        modelObject = decodeByteArray(context, modelBytes, listener);
    }


    private static ModelObject decodeByteArray(Context context, byte[] data, ModelLoaderListener listener) {

        ModelObject modelObject = null;

        if (modelType.equals("obj")) {
            modelObject = new ObjObject(data, context, ModelObject.DRAW_MODEL, listener);
        } else if (modelType.equals("stl")) {
            modelObject = new StlObject(data, context, ModelObject.DRAW_MODEL, listener);
        } else if (modelType.equals("3ds")) {
            Log.e(TAG, " can't handle 3ds model");
            listener.loaderFailure();
        } else {
            Log.e(TAG, "model type error!");
            listener.loaderFailure();
        }

        return modelObject;
    }


    public static void cancelDecode() {
        if (modelObject != null) {
            modelObject.cancelTask();
        } else {
            throw new NullPointerException("ModelObject was null, can't call cancelDecode()! please call decodeFile() first.");
        }
    }


}
