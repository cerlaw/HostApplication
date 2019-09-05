package com.konka.pluginapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.konka.pluginstanderlibrary.IPluginStandard;

/**
 * @author zhanghongjie
 * @date 2019/9/5
 * @description
 */
public class BaseActivity extends AppCompatActivity implements IPluginStandard {

    protected Activity hostActivity;

    @Override
    public void setContentView(int layoutResID) {
        if (hostActivity == null) {
            super.setContentView(layoutResID);
        } else {
            hostActivity.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (hostActivity == null) {
            return super.findViewById(id);
        } else {
            return hostActivity.findViewById(id);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (hostActivity == null) {
            return super.getClassLoader();
        } else {
            return hostActivity.getClassLoader();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (hostActivity == null) {
            return super.getLayoutInflater();
        } else {
            return hostActivity.getLayoutInflater();
        }
    }

    @Override
    public Window getWindow() {
        if (hostActivity == null) {
            return super.getWindow();
        } else {
            return hostActivity.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (hostActivity == null) {
            return super.getWindowManager();
        } else {
            return hostActivity.getWindowManager();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (hostActivity == null) {
            super.startActivity(intent);
        } else {
            Intent newIntent = new Intent();
            newIntent.putExtra(CLASS_NAME, intent.getComponent().getClassName());
            hostActivity.startActivity(newIntent);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void attach(Activity activity) {
        this.hostActivity = activity;
    }
}
