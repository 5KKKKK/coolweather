package com.example.yanhoor.coolweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.yanhoor.coolweather.R;
import com.example.yanhoor.coolweather.service.AutoUpdateService;

/**
 * Created by yanhoor on 2016/1/5.
 */
public class SettingsActivity extends Activity {
    private CheckBox autoUpdateCheck;
    private Button updateFrequence;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings_layout);
        autoUpdateCheck=(CheckBox)findViewById(R.id.auto_update_check);
        updateFrequence=(Button)findViewById(R.id.update_frequence);
        autoUpdateCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //启动自动更新天气服务
                Intent intent=new Intent(SettingsActivity.this, AutoUpdateService.class);
                if (isChecked){
                    Log.d("SettingsActivity","开启自动更新");
                    startService(intent);
                }else{
                    Log.d("SettingsActivity","关闭自动更新");
                    stopService(intent);
                }
            }
        });
        updateFrequence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
