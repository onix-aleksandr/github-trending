package com.app.githubtrending.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.githubtrending.R;
import com.app.githubtrending.databinding.FragmentHomeBinding;
import com.app.githubtrending.ui.home.adapter.HomePagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        HomePagerAdapter adapter = new HomePagerAdapter(this);
        binding.homePager.setAdapter(adapter);

        String[] tabTitles = getResources().getStringArray(R.array.home_tab_titles);

        TabLayoutMediator mediator = new TabLayoutMediator(binding.homeTabs, binding.homePager,
            (tab, position) -> tab.setText(tabTitles[position]));
        mediator.attach();
    }
}
