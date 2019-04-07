package com.example.kristianiademo.screens.home;

import android.support.annotation.NonNull;

import com.example.kristianiademo.model.Repo;
import com.example.kristianiademo.network.GithubService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GithubActivityPresenter {
    private final GithubService githubService;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private GithubActivityView githubActivityView;

    public GithubActivityPresenter(@NonNull GithubService githubService) {
        this.githubService = githubService;
    }

    public void setGithubActivityView(GithubActivityView view) {
        githubActivityView = view;
    }

    public void detatch() {
        disposable.clear();
        githubActivityView = null;
    }

    public void onGithubUserSearch(String username) {
        githubActivityView.hideSoftKeyboard();
        username = username.trim();
        if (username.length() > 0) {
            disposable.add(
                    githubService.listRepos(username)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .flatMapIterable(x -> x)
                            .map(Repo::getName)
                            .toList()
                            .subscribe(repositoryNames -> {
                                        if (githubActivityView != null) {
                                            githubActivityView.showGithubRepoList(repositoryNames);
                                        }
                                    },
                                    throwable -> {
                                        if (githubActivityView != null) {
                                            githubActivityView.showErrorMessage("Ooops");
                                        }
                                    })
            );
        }
    }
}
