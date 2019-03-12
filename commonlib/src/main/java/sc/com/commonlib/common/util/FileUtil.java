package sc.com.commonlib.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import sc.com.commonlib.CommonKit;
import sc.com.commonlib.impl.http.entity.DownloadFileEntity;

/**
 * Created by Eric on 2018/3/1.
 */

public class FileUtil {

    public static String FILE_PROVIDER_AUTHORITY = "sc.com.commonlib.FileProvider";

    public static String getNetCacheDirPath() {
        String path = "";
        File externalCacheDir = CommonKit.getApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            path = externalCacheDir.getAbsolutePath() + "/NetCache";
            File file = new File(path);
            if (file.exists()) {
                return path;
            } else {
                file.mkdirs();

            }
        }
        return path;
    }

    public static File getNetCacheDir() {
        File netCache = null;
        File externalCacheDir = CommonKit.getApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            String path = externalCacheDir.getAbsolutePath() + "/NetCache";
            netCache = new File(path);
            if (!netCache.exists()) {
                netCache.mkdirs();
            }
        }
        return netCache;
    }

    public static File getModelsDir() {
        File netCacheDir = getNetCacheDir();
        File modelDir = null;
        if (netCacheDir != null) {
            String path = netCacheDir.getAbsolutePath() + "/Models";
            modelDir = new File(path);
            if (!modelDir.exists()) {
                modelDir.mkdirs();
            }
        }
        return modelDir;
    }

    public static File getUpdateApkDir() {
        File netCacheDir = getNetCacheDir();
        File modelDir = null;
        if (netCacheDir != null) {
            String path = netCacheDir.getAbsolutePath() + "/UpdateApk";
            modelDir = new File(path);
            if (!modelDir.exists()) {
                modelDir.mkdirs();
            }
        }
        return modelDir;
    }

    public static File getImageCacheDir() {
        File netCache = null;
        File externalCacheDir = CommonKit.getApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            String path = externalCacheDir.getAbsolutePath() + "/ImageCache";
            netCache = new File(path);
            if (!netCache.exists()) {
                netCache.mkdirs();
            }
        }
        return netCache;
    }

    public static File getCrashDir() {
        File netCache = null;
        File externalCacheDir = CommonKit.getApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            String path = externalCacheDir.getAbsolutePath() + "/Crash";
            netCache = new File(path);
            if (!netCache.exists()) {
                netCache.mkdirs();
            }
        }
        return netCache;
    }


    public static String readTextFileFromResource(Context context, int resourceId) {
        StringBuilder body = new StringBuilder();
        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                body.append(nextLine);
                body.append("\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Resources.NotFoundException e) {
            throw new RuntimeException("Resources not find " + resourceId);
        }

        return body.toString();

    }

    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        String ext = fileName.substring(index, fileName.length());

        return ext;
    }

    public static String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();

    }

    public static void cleanApkDir(){
        File apkDir = getUpdateApkDir();
        File[] childFile = apkDir.listFiles();
        for(File child :childFile){
            child.deleteOnExit();

        }

    }

    public static Uri getFileUri(Context context, File file) {
        return FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, file);
    }
    public static Uri getFileUri(Context context, String filePath) {

        return FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, new File(filePath));
    }

    public static Uri getFileUri(Context context, DownloadFileEntity entity) {
        return FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, new File(entity.getPath()));
    }

    public static void installApk(Context context,DownloadFileEntity downloadFileEntity){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(FileUtil.getFileUri(context, downloadFileEntity), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
