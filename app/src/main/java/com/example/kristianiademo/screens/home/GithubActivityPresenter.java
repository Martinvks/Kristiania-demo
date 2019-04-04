package com.example.kristianiademo.screens.home;

import com.example.kristianiademo.model.Repo;
import com.example.kristianiademo.network.GithubService;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubActivityPresenter {
    private final GithubService githubService;

    private GithubActivityView githubActivityView;

    public GithubActivityPresenter(GithubService githubService) {
        this.githubService = githubService;
    }

    public void attatch(GithubActivityView view) {
        githubActivityView = view;
    }

    public void detatch() {
        githubActivityView = null;
    }

    public void onGithubUserSearch(String username) {
        username = username.trim();
        if (username.length() > 0) {
            githubService.listRepos(username)
                    .enqueue(getListReposResponseListener());
        }
    }

    private Callback<List<Repo>> getListReposResponseListener() {
        return new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    List<Repo> repos = response.body();
                    List<String> repoNames = repos.stream()
                            .map(Repo::getName)
                            .collect(Collectors.toList());

                    if (githubActivityView != null) {
                        githubActivityView.showGithubRepoList(repoNames);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                if (githubActivityView != null) {
                    githubActivityView.showErrorMessage("Ooops");
                }
            }
        };
    }


}
