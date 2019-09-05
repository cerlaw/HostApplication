package com.konka.pluginstanderlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * @author zhanghongjie
 * @date 2019/9/5
 * @description 通过此接口处理Plugin Application的生命周期以及注入上下文
 */
public interface IPluginStandard {

    String CLASS_NAME = "className";

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    boolean onTouchEvent(MotionEvent event);

    void onBackPressed();

    /**
     * 需要宿主Activity给插件Activity注入上下文
     */
    void attach(Activity activity);
}
