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

    public MutableLiveData<List<Repository>> repositories = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> errorEvent = new MutableLiveData<>();

    private FilterType filterType = FilterType.LastMonth;
    private String query = "";
    private boolean hasNextPage;

    public SearchViewModel() {
        search(true, true);
    }

    public void setFilterType(FilterType type) {
        if (filterType == type) return;

        filterType = type;
        search(true, false);
    }

    public void setQuery(CharSequence query) {
        this.query = query.toString();
        search(true, true);
    }

    public void loadMore() {
        search(false, false);
    }

    private void search(boolean refresh, boolean force) {
        if (Boolean.TRUE.equals(isLoading.getValue()) && !force) return;
        if (!hasNextPage && !refresh) return;

        isLoading.setValue(true);
        int page = getCurrentPage(refresh);
        TrendingRepositoriesParams params = new TrendingRepositoriesParams(query, REPOSITORIES_PER_PAGE, page, filterType);
        repository.searchRepositories(params, new NetworkCallback<>() {
            @Override
            public void onSuccess(ArrayList<Repository> data) {
                isLoading.setValue(false);
                hasNextPage = data.size() == REPOSITORIES_PER_PAGE;
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
                errorEvent.postValue(error.getMessage());
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

    public void clearError() {
        errorEvent.setValue(null);
    }
}
