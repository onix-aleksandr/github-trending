package com.app.githubtrending.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.githubtrending.data.entity.FilterType;
import com.app.githubtrending.data.entity.NetworkCallback;
import com.app.githubtrending.data.entity.Repository;
import com.app.githubtrending.data.repository.TrendingRepository;
import com.app.githubtrending.data.repository.params.TrendingRepositoriesParams;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {

    private static final int REPOSITORIES_PER_PAGE = 20;

    private final TrendingRepository repository = new TrendingRepository();

    MutableLiveData<List<Repository>> repositories = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private FilterType filterType = FilterType.LastMonth;
    private String query = "";

    public SearchViewModel() {
        search(true);
    }

    public void setFilterType(FilterType type) {
        if (filterType == type) return;

        filterType = type;
        search(true);
    }

    public void setQuery(CharSequence query) {
        this.query = query.toString();
        search(true);
    }

    public void loadMore() {
        search(false);
    }

    private void search(boolean refresh) {
        if (Boolean.TRUE.equals(isLoading.getValue())) return;

        isLoading.setValue(true);
        int page = getCurrentPage(refresh);
        TrendingRepositoriesParams params = new TrendingRepositoriesParams(query, REPOSITORIES_PER_PAGE, page, filterType);
        repository.searchRepositories(params, new NetworkCallback<>() {
            @Override
            public void onSuccess(ArrayList<Repository> data) {
                isLoading.setValue(false);
                if (repositories.getValue() == null) {
                    repositories.postValue(data);
                } else {
                    if (refresh) {
                        repositories.postValue(data);
                    } else {
                        ArrayList<Repository> oldRepositories = new ArrayList<>(repositories.getValue());
                        oldRepositories.addAll(data);
                        repositories.postValue(oldRepositories);
                    }
                }
            }

            @Override
            public void onError(Throwable error) {
                isLoading.setValue(false);
            }
        });
    }

    private int getCurrentPage(boolean refresh) {
        if (refresh) {
            return 0;
        } else {
            List<Repository> repos = repositories.getValue();
            if (repos == null) {
                return 0;
            } else {
                return repos.size() / REPOSITORIES_PER_PAGE;
            }
        }
    }
}
