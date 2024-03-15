package com.app.githubtrending.ui.search.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.app.githubtrending.data.entity.Repository;

public class SearchDiffCallback extends DiffUtil.ItemCallback<Repository> {

    @Override
    public boolean areItemsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
        return true;
    }
}
