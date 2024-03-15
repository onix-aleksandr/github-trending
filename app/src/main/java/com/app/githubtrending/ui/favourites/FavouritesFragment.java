package com.app.githubtrending.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.githubtrending.R;
import com.app.githubtrending.data.database.AppDatabaseHolder;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.databinding.FragmentFavouritesBinding;
import com.app.githubtrending.ui.home.HomeFragmentDirections;
import com.app.githubtrending.ui.search.adapter.SearchAdapter;
import com.app.githubtrending.ui.search.adapter.SearchClickListener;

public class FavouritesFragment extends Fragment implements SearchClickListener {

    private FragmentFavouritesBinding binding;

    private SearchAdapter adapter;

    private NavController navController;

    private FavouritesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(requireActivity(), R.id.mainFragmentHost);
        viewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);
        viewModel.setupDatabase(AppDatabaseHolder.getDatabaseInstance(requireContext()));
        configureList();
    }

    private void configureList() {
        adapter = new SearchAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.favouritesList.setLayoutManager(layoutManager);
        binding.favouritesList.setAdapter(adapter);

        viewModel.repositories.observe(getViewLifecycleOwner(), repositories -> adapter.submitList(repositories));
    }

    @Override
    public void onRepositoryClick(Repository repository) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(repository));
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchFavourites();
    }
}
