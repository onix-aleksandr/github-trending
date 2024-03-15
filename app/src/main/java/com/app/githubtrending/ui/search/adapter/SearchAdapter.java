package com.app.githubtrending.ui.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.app.githubtrending.R;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.databinding.ItemSearchBinding;

public class SearchAdapter extends ListAdapter<Repository, SearchViewHolder> {

    private final SearchClickListener clickListener;

    public SearchAdapter(SearchClickListener clickListener) {
        super(new SearchDiffCallback());
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        ItemSearchBinding itemListBinding = ItemSearchBinding.bind(itemListView);
        return new SearchViewHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bind(getItem(position), clickListener);
    }
}
