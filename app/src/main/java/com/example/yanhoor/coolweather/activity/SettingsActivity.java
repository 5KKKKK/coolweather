package com.example.yanhoor.coolweather.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        updateFrequence=(Button) findViewById(R.id.update_frequence);
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
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("选择时间")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setItems(R.array.update_time, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String[] items = getResources().getStringArray(R.array.update_time);
                                String regEx="[^0-9]";
                                Pattern p = Pattern.compile(regEx);
                                Matcher m = p.matcher(items[which]);
                                String numString=m.replaceAll("").trim();
                                AutoUpdateService.updateTime=Integer.parseInt(numString);

                            }
                        })
                        .setNegativeButton("取消",null).show();
            }
        });
    }

}
