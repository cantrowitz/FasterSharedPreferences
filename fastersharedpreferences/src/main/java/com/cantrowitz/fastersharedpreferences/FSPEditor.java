package com.cantrowitz.fastersharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;
import java.util.Set;

/**
 * Created by adamcantrowitz on 9/23/15.
 */
class FSPEditor implements Editor {

    private final KeyAdapter keyAdapter;
    private final Editor editor;
    private final SharedPreferences sharedPreferences;

    FSPEditor(KeyAdapter keyAdapter, SharedPreferences sharedPreferences) {
        this.keyAdapter = keyAdapter;
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    @Override
    public Editor putString(String key, String value) {
        editor.putString(keyAdapter.adapt(key), value);
        return this;
    }

    @Override
    public Editor putStringSet(String key, Set<String> values) {
        editor.putStringSet(keyAdapter.adapt(key), values);
        return this;
    }

    @Override
    public Editor putInt(String key, int value) {
        editor.putInt(keyAdapter.adapt(key), value);
        return this;
    }

    @Override
    public Editor putLong(String key, long value) {
        editor.putLong(keyAdapter.adapt(key), value);
        return this;
    }

    @Override
    public Editor putFloat(String key, float value) {
        editor.putFloat(keyAdapter.adapt(key), value);
        return this;
    }

    @Override
    public Editor putBoolean(String key, boolean value) {
        editor.putBoolean(keyAdapter.adapt(key), value);
        return this;
    }

    @Override
    public Editor remove(String key) {
        editor.remove(keyAdapter.adapt(key));
        return this;
    }

    @Override
    public Editor clear() {

        Map<String, ?> all = sharedPreferences.getAll();
        for (String key : all.keySet()) {
            if (keyAdapter.isValid(key)) {
                editor.remove(key);
            }
        }

        return this;
    }

    @Override
    public boolean commit() {
        return editor.commit();
    }

    @Override
    public void apply() {
        editor.apply();
    }
}
