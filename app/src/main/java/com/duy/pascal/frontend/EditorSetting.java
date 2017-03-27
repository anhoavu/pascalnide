package com.duy.pascal.frontend;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.duy.pascal.frontend.data.Preferences;

/**
 * Created by Duy on 02-Mar-17.
 */

public class EditorSetting extends Preferences {
    public EditorSetting(Context context) {
        super(context);
    }

    public EditorSetting(SharedPreferences mPreferences, Context context) {
        super(mPreferences, context);
    }

    public boolean isWrapText() {
        return getBoolean(context.getString(R.string.key_pref_word_wrap));
    }

    public float getTextSize() {
//        return TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                Float.parseFloat(getString(context.getString(R.string.key_pref_font_size))),
//                context.getResources().getDisplayMetrics());
        return Float.parseFloat(getString(context.getString(R.string.key_pref_font_size)));
    }

    public Typeface getFont() {
        return null;
    }

    public boolean isShowLineNumbers() {
        return getBoolean(context.getString(R.string.key_pref_show_line_number));
    }
}