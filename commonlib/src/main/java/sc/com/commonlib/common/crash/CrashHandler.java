package sc.com.commonlib.common.crash;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import sc.com.commonlib.common.util.CommonUtil;
import sc.com.commonlib.common.util.FileUtil;

/**
 * Created by Eric on 2018/11/15.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;
    private Thread.UncaughtExceptionHandler exceptionHandler;
    private Map<String, String> infos = new HashMap<>();

    private static class ClassHolder {
        private static CrashHandler instance = new CrashHandler();
    }

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return ClassHolder.instance;
    }

    public void init(Context context) {
        this.context = context;
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && exceptionHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            exceptionHandler.uncaughtException(t, e);
        } else {
            SystemClock.sleep(3000);
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        deleteRecordMoreThen20();
        //收集设备参数信息
        collectDeviceInfo(context);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    private void deleteRecordMoreThen20() {
        File cashDir = FileUtil.getCrashDir();
        if (cashDir != null && cashDir.exists()) {
            File[] files = cashDir.listFiles();
            if (files != null && files.length >= 20) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }


    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("---------------------start--------------------------");
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuffer.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.flush();
        printWriter.close();
        String result = writer.toString();
        stringBuffer.append(result);
        stringBuffer.append("--------------------end---------------------------");

        String filePath = FileUtil.getCrashDir() + "/" + CommonUtil.getNowDate() + ".txt";

        try {
            File file = new File(filePath);
            boolean haveFile = false;
            if (!file.exists()) {
                haveFile = file.createNewFile();
            } else {
                haveFile = true;
            }
            if (haveFile) {
                PrintStream printStream = new PrintStream(new FileOutputStream(file));
                printStream.append(stringBuffer);
                printStream.flush();
                printStream.close();
            } else {
                Toast.makeText(context, "创建文件失败", Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }


}
