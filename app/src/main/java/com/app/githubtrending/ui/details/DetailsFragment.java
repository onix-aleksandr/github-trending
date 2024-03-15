package com.app.githubtrending.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.githubtrending.R;
import com.app.githubtrending.data.database.AppDatabaseHolder;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.databinding.FragmentDetailsBinding;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    private DetailsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        Repository repository = DetailsFragmentArgs.fromBundle(getArguments()).getRepository();
        viewModel.setupDatabase(AppDatabaseHolder.getDatabaseInstance(requireContext()), repository);
        setupUi(repository);
    }

    private void setupUi(Repository repository) {
        binding.textViewName.setText(repository.getName());
        binding.textViewOwner.setText(repository.getOwnerUsername());
        binding.textViewStars.setText(String.valueOf(repository.getStarsCount()));
        binding.textViewForks.setText(String.valueOf(repository.getForksCount()));
        binding.textViewLanguage.setText(repository.getLanguage());
        binding.textViewDescription.setText(repository.getDescription());
        binding.textViewCreatedAt.setText(repository.getCreatedAt().toString());
        binding.textViewUrl.setText(repository.getUrl());

        ImageLoader imageLoader = Coil.imageLoader(requireContext());

        ImageRequest request = new ImageRequest.Builder(requireContext())
            .data(repository.getImageUrl())
            .crossfade(true)
            .placeholder(R.drawable.avatar_default)
            .target(binding.imageViewRepo)
            .build();
        imageLoader.enqueue(request);

        binding.detailsLike.setOnClickListener(v -> viewModel.switchFavouriteStatus(repository));

        binding.detailsBack.setOnClickListener(v -> Navigation.findNavController(requireActivity(), R.id.mainFragmentHost).popBackStack());

        viewModel.isInFavourites.observe(getViewLifecycleOwner(), isInFavourites -> {
            if (isInFavourites) {
                binding.detailsLike.setImageResource(R.drawable.heart_selected);
            } else {
                binding.detailsLike.setImageResource(R.drawable.heart_idle);
            }
        });
    }
}
