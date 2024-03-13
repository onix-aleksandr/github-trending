package com.app.githubtrending.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.githubtrending.ui.favourites.FavouritesFragment;
import com.app.githubtrending.ui.search.SearchFragment;

public class HomePagerAdapter extends FragmentStateAdapter {

    private static final int FRAGMENT_COUNT = 2;
    private static final int SEARCH_FRAGMENT = 0;
    private static final int FAVOURITES_FRAGMENT = 1;

    public HomePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case SEARCH_FRAGMENT -> {
                return new SearchFragment();
            }
            case FAVOURITES_FRAGMENT -> {
                return new FavouritesFragment();
            }
            default -> {
                return new Fragment();
            }
        }
    }

    @Override
    public int getItemCount() {
        return FRAGMENT_COUNT;
    }
}
