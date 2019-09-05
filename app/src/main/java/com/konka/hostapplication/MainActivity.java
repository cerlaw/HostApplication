package com.konka.hostapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

import static com.konka.pluginstanderlibrary.IPluginStandard.CLASS_NAME;

/**
 * @author zhanghongjie
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void load(View view) {
        HostManager.getInstance().attachContext(this);
        File file = new File(Environment.getExternalStorageDirectory(), "plugin.apk");
        HostManager.getInstance().loadFile(file.getAbsolutePath());
    }

    public void click(View view) {
        Intent intent = new Intent(this, HostActivity.class);
        intent.putExtra(CLASS_NAME, HostManager.getInstance().getEntryActivityName());
        startActivity(intent);
    }
}
