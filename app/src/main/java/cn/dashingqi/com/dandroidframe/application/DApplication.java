package cn.dashingqi.com.dandroidframe.application;

import android.app.Application;
import android.content.Context;

import cn.dashingqi.com.dandroidframe.util.SpUtils.DPrefsUtils;
import cn.dashingqi.com.dandroidframe.util.log.DLoggerUtils;

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
        DPrefsUtils.init(instance);
        //初始化Logger
        DLoggerUtils.LoggerInit();

    }

    public static Context getContext(){
        return instance;
    }
}
