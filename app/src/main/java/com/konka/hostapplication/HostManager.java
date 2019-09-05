package com.konka.hostapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author zhanghongjie
 * @date 2019/9/5
 * @description
 */
public class HostManager {

    private static final String TAG = HostManager.class.getSimpleName();

    private static final HostManager HOST_MANAGER = new HostManager();

    public static HostManager getInstance() {
        return HOST_MANAGER;
    }

    private DexClassLoader dexClassLoader;

    private Resources resources;

    private Context context;

    private String entryActivityName;

    private HostManager() {
    }

    public void loadFile(String filePath) {
        Log.d(TAG, "file absolute path: " + filePath);

        //实例化ClassLoader
        File dexOutFile = context.getDir("dex", Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(filePath, dexOutFile.getAbsolutePath(), null,
                context.getClassLoader());

        //获取入口Activity包名
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        entryActivityName = packageInfo.activities[0].name;

        //实例化resource
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, filePath);
            resources = new Resources(assetManager, context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void attachContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public String getEntryActivityName() {
        return entryActivityName;
    }
}
