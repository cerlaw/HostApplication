package com.konka.pluginapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * @author zhanghongjie
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hostActivity, "点击了", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(hostActivity, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
