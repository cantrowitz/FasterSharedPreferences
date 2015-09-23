package com.cantrowitz.fastersharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by adamcantrowitz on 9/23/15.
 */
public class FasterSharedPreferences implements SharedPreferences {

    private final SharedPreferences sharedPreferences;
    private final KeyAdapter keyAdapter;

    private FasterSharedPreferences(SharedPreferences sharedPreferences, KeyAdapter keyAdapter) {
        this.sharedPreferences = sharedPreferences;
        this.keyAdapter = keyAdapter;
    }

    public static FasterSharedPreferences get(Context context, String name, int mode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(getFileName(context, mode), mode);
        return new FasterSharedPreferences(sharedPreferences, new KeyAdapter.FSPKeyAdapter(name));
    }

    private static String getFileName(Context context, int mode) {
        return context.getString(R.string.faster_shared_preference_name);
    }

    @Override
    public Map<String, ?> getAll() {

        Map<String, Object> filteredMap = new HashMap<>();
        Map<String, ?> all = sharedPreferences.getAll();
        for (String key : all.keySet()) {
            if (keyAdapter.isValid(key)) {
                filteredMap.put(keyAdapter.original(key), all.get(key));
            }
        }

        return filteredMap;
    }

    @Nullable
    @Override
    public String getString(String s, String s1) {
        return sharedPreferences.getString(keyAdapter.adapt(s), s1);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String s, Set<String> set) {
        return sharedPreferences.getStringSet(keyAdapter.adapt(s), set);
    }

    @Override
    public int getInt(String s, int i) {
        return sharedPreferences.getInt(keyAdapter.adapt(s), i);
    }

    @Override
    public long getLong(String s, long l) {
        return sharedPreferences.getLong(keyAdapter.adapt(s), l);
    }

    @Override
    public float getFloat(String s, float v) {
        return sharedPreferences.getFloat(keyAdapter.adapt(s), v);
    }

    @Override
    public boolean getBoolean(String s, boolean b) {
        return sharedPreferences.getBoolean(keyAdapter.adapt(s), b);
    }

    @Override
    public boolean contains(String s) {
        return sharedPreferences.contains(keyAdapter.adapt(s));
    }

    @Override
    public Editor edit() {
        return new FSPEditor(keyAdapter, sharedPreferences);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException("registerOnSharedPreferenceChangeListener is not currently supported");
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException("unregisterOnSharedPreferenceChangeListener is not currently supported");
    }


}
