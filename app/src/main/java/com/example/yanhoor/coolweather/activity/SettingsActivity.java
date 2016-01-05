package com.example.yanhoor.coolweather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.yanhoor.coolweather.R;

/**
 * Created by yanhoor on 2016/1/5.
 */
public class SettingsActivity extends Activity {
    private CheckBox autoUpdateCheck;
    private Button updateFrequence;

    public static boolean isAutoUpdate;

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
                isAutoUpdate=isChecked;
            }
        });
        updateFrequence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
