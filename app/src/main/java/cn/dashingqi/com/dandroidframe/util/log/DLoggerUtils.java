package cn.dashingqi.com.dandroidframe.util.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import cn.dashingqi.com.dandroidframe.BuildConfig;

/**
 * <p>文件描述：<p>
 * <p>作者：北京车车网络技术有限公司<p>
 * <p>创建时间：2018/12/7<p>
 * <p>更改时间：2018/12/7<p>
 * <p>版本号：1<p>
 */
public class DLoggerUtils {

    /**
     * 初始化Logger
     */
    public static void LoggerInit() {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(new CustomLogCatStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("DashingQi")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                //返回 true表示打印Log日志 false不打印
                return BuildConfig.LOOGER_DEBUG;
            }
        });
    }

    public static void v(String tag, String msg) {
        Logger.t(tag).v(msg);
    }

    public static void i(String tag, String msg) {
        Logger.t(tag).i(msg);
    }

    public static void d(String tag, String msg) {
        Logger.t(tag).d(msg);
    }

    public static void d(String tag, Object object) {
        Logger.t(tag).d(object);
    }

    public static void d(String tag, Object... objects) {
        Logger.t(tag).d(objects);
    }

    public static void d(String tag) {
        Logger.d(tag);
    }

    public static void w(String tag, String msg) {
        Logger.t(tag).w(msg);
    }

    public static void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    public static void json(String tag, String json) {
        Logger.t(tag).json(json);
    }

    public static void xml(String tag, String xml) {
        Logger.xml(xml);
    }
}
