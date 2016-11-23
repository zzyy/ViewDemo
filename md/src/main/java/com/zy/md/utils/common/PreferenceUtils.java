package com.zy.md.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.orhanobut.logger.Logger;

/**
 * Created by Simon on 2016/9/29.
 */
public class PreferenceUtils {
    private static final String TAG = "BfcCommon_SPUtils";
    public static final String DEFAULT_PREFERENCES_FILE_NAME = "bfc_share_preference";
    private final SharedPreferences mPreferences;

    private PreferenceUtils(Context ctx, String filePath, int mode) {
        mPreferences = ctx.getApplicationContext().getSharedPreferences(filePath, mode);
    }

    private static PreferenceUtils sInstance;

    public SharedPreferences getPreferences() {
        return mPreferences;
    }

    /**
     * 获取实例, 使用默认文件保存sharePreferences数据的
     *
     * @return 返回单例对象
     */
    public static PreferenceUtils with(Context ctx) {
        if (sInstance == null) {
            synchronized (PreferenceUtils.class) {
                if (sInstance == null) {
                    sInstance = new PreferenceUtils(ctx, DEFAULT_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
                }
            }
        }

        return sInstance;
    }

    /**
     * 获取实例, 使用指定的文件保存sharePreferences数据的
     *
     * @param fileName sharePreference文件名
     */
    public static PreferenceUtils with(Context ctx, String fileName) {
        return new PreferenceUtils(ctx, fileName, Context.MODE_PRIVATE);
    }

    /**
     * 获取实例, 使用指定的文件保存sharePreferences数据的
     *
     * @param fileName sharePreference文件名
     * @param mode     保存文件的模式, {@link Context#MODE_PRIVATE }, @{@link Context#MODE_APPEND}, @{@link Context#MODE_WORLD_READABLE }, @{@link Context#MODE_WORLD_WRITEABLE }
     */
    protected static PreferenceUtils with(Context ctx, String fileName, int mode) {
        return new PreferenceUtils(ctx, fileName, mode);
    }


    /**
     * 保存数据到SharePreferences
     */
    public void put(String key, int value) {
        this.putAuto(key, value);
    }

    public void put(String key, float value) {
        this.putAuto(key, value);
    }

    public void put(String key, long value) {
        this.putAuto(key, value);
    }

    public void put(String key, boolean value) {
        this.putAuto(key, value);
    }

    public void put(String key, String value) {
        this.putAuto(key, value);
    }

    private void putAuto(String key, Object value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else {
            editor.putString(key, value.toString());
        }

        editor.apply();
    }

    /**
     * 从Sharepreference获取数据
     */
    public int get(String key, int defaultValue) {
        try {
            return mPreferences.getInt(key, defaultValue);
        } catch (ClassCastException e) {
            Logger.e(TAG, "SPUtils获取数据异常, 现在返回的数据是int, 请查看获取的数据是否为int");
            throw e;
        }
    }

    public float get(String key, float defaultValue) {
        return mPreferences.getFloat(key, defaultValue);
    }

    public long get(String key, long defaultValue) {
        return mPreferences.getLong(key, defaultValue);
    }

    public boolean get(String key, boolean defaultValue) {
        return mPreferences.getBoolean(key, defaultValue);
    }

    public String get(String key, String defaultValue) {
        return mPreferences.getString(key, defaultValue);
    }


    /**
     * 清楚保存的数据
     *
     * @param key
     */
    public void remove(String key) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
