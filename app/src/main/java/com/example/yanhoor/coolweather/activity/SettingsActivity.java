package com.example.yanhoor.coolweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.yanhoor.coolweather.R;
import com.example.yanhoor.coolweather.service.AutoUpdateService;

import java.util.List;

/**
 * Created by yanhoor on 2016/1/8.
 */
public class SettingsActivity extends PreferenceActivity {
    public static boolean isUpdateEnable;
    public static int updateTime=0;
    /*
    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("settingsActivity","onCreate");
        //将资源设置默认值，false表示系统只会在之前没有调用过该方法时才设置默认值，不会覆盖之前的设置
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }
*/

    public static class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        CheckBoxPreference updateCheckBoxPreference;
        ListPreference frequencyListPreference;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
            //将资源设置默认值，false表示系统只会在之前没有调用过该方法时才设置默认值，不会覆盖之前的设置
            PreferenceManager.setDefaultValues(getActivity(), R.xml.settings, false);
            updateCheckBoxPreference = (CheckBoxPreference) findPreference("update_enable_boolean_key");
            frequencyListPreference = (ListPreference) findPreference("update_frequency_key");
            frequencyListPreference.setSummary(frequencyListPreference.getValue()+"小时");
            updateCheckBoxPreference.setOnPreferenceChangeListener(this);
            frequencyListPreference.setOnPreferenceChangeListener(this);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (preference.getKey().equals("update_enable_boolean_key")) {
                SettingsActivity.isUpdateEnable=Boolean.parseBoolean(newValue.toString());
            } else if (preference.getKey().equals("update_frequency_key")) {
                String textValue = newValue.toString();//textValue 是选中的选项对应的value
                AutoUpdateService.updateTime = Integer.parseInt(textValue);//设置更新时间
                SettingsActivity.updateTime= Integer.parseInt(textValue);
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(textValue);//index contains the index of the clicked item
                CharSequence[] entries = listPreference.getEntries();//entries[index] 是选中的选项
                if (index >= 0) {
                    frequencyListPreference.setSummary(entries[index]);//更新summary
                }
            }
            return true;
        }
    }

    //显示preferenceHeader资源文件
    @Override
    public void onBuildHeaders(List<Header> target){
        loadHeadersFromResource(R.xml.preference_headers,target);
    }

    @Override
    public boolean isValidFragment(String s){
        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Intent i=new Intent(this,AutoUpdateService.class);
        if (isUpdateEnable){
            startService(i);
            if (updateTime!=0){
                startService(i);
            }else{
                stopService(i);
            }
        }

        Log.d("SettingsActivity","onDestroy");
    }

}
