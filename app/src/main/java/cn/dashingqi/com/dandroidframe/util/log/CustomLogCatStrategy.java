package cn.dashingqi.com.dandroidframe.util.log;

import android.util.Log;

import com.orhanobut.logger.LogStrategy;

/**
 * <p>文件描述：解决AndroidStudio3.1版本下的日志排版问题 <p>
 * <p>作者：DashingQi <p>
 * <p>创建时间：2018/12/7<p>
 * <p>更改时间：2018/12/7<p>
 * <p>版本号：1<p>
 */
public class CustomLogCatStrategy implements LogStrategy {
    @Override
    public void log(int priority, String tag, String message) {
        Log.println(priority, randomKey() + tag, message);

    }
    private int last;

    private String randomKey() {
        int random = (int) (10 * Math.random());
        if (random == last) {
            random = (random + 1) % 10;
        }
        last = random;
        return String.valueOf(random);
    }
}
