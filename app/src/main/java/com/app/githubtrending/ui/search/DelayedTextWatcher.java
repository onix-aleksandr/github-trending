package com.app.githubtrending.ui.search;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

public class DelayedTextWatcher implements TextWatcher {

    private static final int DEFAULT_DELAY = 300;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final TextChangedListener listener;
    private final int delayMillis;

    public DelayedTextWatcher(TextChangedListener listener) {
        this(listener, DEFAULT_DELAY);
    }

    public DelayedTextWatcher(TextChangedListener listener, int delayMillis) {
        this.listener = listener;
        this.delayMillis = delayMillis;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(final Editable s) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(() -> listener.onTextChanged(s.toString()), delayMillis);
    }

    public interface TextChangedListener {
        void onTextChanged(String text);
    }
}

