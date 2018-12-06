package cn.dashingqi.com.dandroidframe.application;

import android.app.Application;
import android.content.Context;

import cn.dashingqi.com.dandroidframe.util.SpUtils.DShareUtils;

/**
 * @ProjectName: DAndroidFrame
 * @Package: cn.dashingqi.com.dandroidframe.application
 * @ClassName: DApplication
 * @Author: DashingQI
 * @CreateDate: 2018/12/6 10:06 PM
 * @UpdateUser:
 * @UpdateDate: 2018/12/6 10:06 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class DApplication extends Application {

    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
        //初始化SharedPreference
        DShareUtils.init(instance);

    }

    public static Context getContext(){
        return instance;
    }
}