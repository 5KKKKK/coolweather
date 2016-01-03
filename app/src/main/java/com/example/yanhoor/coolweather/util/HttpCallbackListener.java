package com.example.yanhoor.coolweather.util;

/**
 * Created by yanhoor on 2016/1/3.
 */
/*回调服务返回的结果*/
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
