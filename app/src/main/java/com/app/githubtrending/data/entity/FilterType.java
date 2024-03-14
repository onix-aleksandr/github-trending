package com.app.githubtrending.data.entity;

import androidx.annotation.StringRes;

import com.app.githubtrending.R;

public enum FilterType {
    LastMonth(R.string.last_month),
    LastWeek(R.string.last_week),
    LastDay(R.string.last_day);

    FilterType(@StringRes int filterName) {
        this.filterName = filterName;
    }

    public final int filterName;
}
