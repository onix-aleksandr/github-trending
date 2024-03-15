package com.app.githubtrending.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.githubtrending.R;
import com.app.githubtrending.component.scroll.EndlessScrollListener;
import com.app.githubtrending.data.entity.FilterType;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.databinding.FragmentSearchBinding;
import com.app.githubtrending.ui.home.HomeFragmentDirections;
import com.app.githubtrending.ui.search.adapter.SearchAdapter;
import com.app.githubtrending.ui.search.adapter.SearchClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchClickListener {

    private FragmentSearchBinding binding;

    private SearchViewModel viewModel;

    private SearchAdapter adapter;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureViewModel();
        configureSpinner();
        configureList();

        binding.setViewModel(viewModel);

        navController = Navigation.findNavController(requireActivity(), R.id.mainFragmentHost);
    }

    private void configureList() {
        adapter = new SearchAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.searchList.setLayoutManager(layoutManager);
        binding.searchList.setAdapter(adapter);

        viewModel.repositories.observe(getViewLifecycleOwner(), repositories -> adapter.submitList(repositories));
        viewModel.isLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.searchProgress.setVisibility(View.VISIBLE);
            } else {
                binding.searchProgress.setVisibility(View.INVISIBLE);
            }
        });

        EndlessScrollListener listener = new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                viewModel.loadMore();
            }

            @Override
            public boolean isLoading() {
                return Boolean.TRUE.equals(viewModel.isLoading.getValue());
            }
        };

        binding.searchList.addOnScrollListener(listener);
    }

    private void configureViewModel() {
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    private void configureSpinner() {
        List<String> items = new ArrayList<>();
        for (FilterType type : FilterType.values()) {
            items.add(getString(type.filterName));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.searchFilter.setAdapter(adapter);

        binding.searchFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                FilterType selectedFilter = FilterType.values()[position];
                viewModel.setFilterType(selectedFilter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public void onRepositoryClick(Repository repository) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(repository));
    }
}
