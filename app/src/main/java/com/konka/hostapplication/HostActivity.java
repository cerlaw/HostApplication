package com.konka.hostapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.konka.pluginstanderlibrary.IPluginStandard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.konka.pluginstanderlibrary.IPluginStandard.CLASS_NAME;

/**
 * @author zhanghongjie
 * @date 2019/9/5
 * @description
 */
public class HostActivity extends AppCompatActivity {

    /**
     * 要跳转的插件的className
     */
    private String className;

    private IPluginStandard iPluginStandard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra(CLASS_NAME);

        //通过反射获取类名
        try {
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            //可以强转为IPluginStander
            iPluginStandard = (IPluginStandard) instance;
            iPluginStandard.attach(this);
            //可以传递信息
            Bundle bundle = new Bundle();
            iPluginStandard.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        iPluginStandard.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iPluginStandard.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        iPluginStandard.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        iPluginStandard.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPluginStandard.onDestroy();
    }

    @Override
    public ClassLoader getClassLoader() {
        return HostManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return HostManager.getInstance().getResources();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra(CLASS_NAME);
        Intent newIntent = new Intent(this, HostActivity.class);
        newIntent.putExtra(CLASS_NAME, className);
        super.startActivity(newIntent);
    }
}
