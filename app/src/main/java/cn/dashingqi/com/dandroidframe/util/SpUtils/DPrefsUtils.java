package cn.dashingqi.com.dandroidframe.util.SpUtils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import com.google.common.collect.ImmutableMap;


/**
 * 封装的Sp工具类
 */
public class DPrefsUtils {

    private static Context applicationContext;
    private static SharedPreferences sp;
    private static String SpName = "DashingQi";

    private static String SpDefaultNameKey ="_preferences";
    private final SharedPreferences sharedPreferences;

    private DPrefsUtils(Context context){
        sharedPreferences = context.getSharedPreferences(context.getPackageName() + SpDefaultNameKey, Context.MODE_PRIVATE);
    }

    private DPrefsUtils(Context context,String SpName){
        sharedPreferences = context.getSharedPreferences(SpName, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (applicationContext == null) {
            synchronized (DPrefsUtils.class) {
                if (applicationContext == null)
                    applicationContext = context.getApplicationContext();
            }

            if (sp == null) {
                sp = applicationContext.getSharedPreferences(SpName, Context.MODE_PRIVATE);
            }
        }
    }

    private static SharedPreferences getSp() {
        assert sp != null;
        return sp;
    }

    private static Context getContext() {
        assert applicationContext != null;
        return applicationContext;
    }

    /**
     * 插入一个键值对
     *
     * @param key
     * @param value
     */
    public static boolean putValue(String key, Object value) {
        return putKeyAndValues(ImmutableMap.of(key, value));
    }

    /**
     * 插入一组键值对
     *
     * @param keyAndValues
     */
    public static boolean putKeyAndValues(Map<String, ?> keyAndValues) {

        if (keyAndValues != null && keyAndValues.size() > 0) {
            SharedPreferences sp = getSp();
            SharedPreferences.Editor edit = sp.edit();
            for (Map.Entry<String, ?> me : keyAndValues.entrySet()) {
                Object value = me.getValue();
                if (value instanceof Integer) {
                    edit.putInt(me.getKey(), (int) value);
                } else if (value instanceof Float) {
                    edit.putFloat(me.getKey(), (float) value);
                } else if (value instanceof Double) {
                    edit.putLong(me.getKey(), Double.doubleToLongBits((double) value));
                } else if (value instanceof Long) {
                    edit.putLong(me.getKey(), (long) value);
                } else if (value instanceof Boolean) {
                    edit.putBoolean(me.getKey(), (boolean) value);
                } else if (value instanceof CharSequence) {
                    edit.putString(me.getKey(), value.toString());
                } else {
                    throw new ClassCastException("不支持类型" + value.getClass().getName());
                }
            }

            return edit.commit();
        }
        return false;
    }

    public static String getStringValue(String key, String defaultValue) {
        SharedPreferences sp = getSp();
        return sp.getString(key, defaultValue);
    }

    public static int getIntValue(String key, int defaultValue) {
        return getSp().getInt(key, defaultValue);
    }

    public static boolean getBooleanValue(String key, boolean defaultValue) {
        return getSp().getBoolean(key, defaultValue);
    }

    public static Long getLongValue(String key, Long defaultValue) {
        return getSp().getLong(key, defaultValue);
    }

    public static double getDoubleValue(String key, double defaultValue) {
        long value = getSp().getLong(key, Double.doubleToLongBits(defaultValue));
        return Double.longBitsToDouble(value);
    }

    public static float getFloatValue(String key, float defaultValue) {
        return getSp().getFloat(key, defaultValue);
    }

    public static boolean removeValue(String key) {
        SharedPreferences.Editor edit = getSp().edit();
        edit.remove(key);
        return edit.commit();
    }

    public static boolean removeValues(String... keys){
        SharedPreferences.Editor edit = getSp().edit();
        for (String key : keys){
            edit.remove(key);
        }

        return edit.commit();
    }
}
