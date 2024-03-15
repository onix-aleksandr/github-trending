package com.app.githubtrending.ui.search.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.githubtrending.R;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.databinding.ItemSearchBinding;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    private final ItemSearchBinding binding;

    public SearchViewHolder(@NonNull ItemSearchBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Repository repository, SearchClickListener clickListener) {
        binding.itemSearchStars.setText(String.valueOf(repository.getStarsCount()));
        if (repository.getDescription().isEmpty()) {
            binding.itemSearchDescription.setText(itemView.getContext().getString(R.string.default_description));
        } else {
            binding.itemSearchDescription.setText(repository.getDescription());
        }
        binding.itemSearchUsername.setText(repository.getOwnerUsername());

        ImageLoader imageLoader = Coil.imageLoader(itemView.getContext());

        ImageRequest request = new ImageRequest.Builder(itemView.getContext())
            .data(repository.getImageUrl())
            .crossfade(true)
            .placeholder(R.drawable.avatar_default)
            .target(binding.itemSearchAvatar)
            .build();
        imageLoader.enqueue(request);

        binding.getRoot().setOnClickListener(v -> clickListener.onRepositoryClick(repository));
    }
}
