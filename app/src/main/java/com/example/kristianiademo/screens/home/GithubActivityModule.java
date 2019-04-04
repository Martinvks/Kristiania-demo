package com.example.kristianiademo.screens.home;

import com.example.kristianiademo.dagger.scopes.ActivityScope;
import com.example.kristianiademo.network.GithubService;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubActivityModule {

    @ActivityScope
    @Provides
    GithubActivityPresenter githubActivityPresenter(GithubService githubService) {
        return new GithubActivityPresenter(githubService);
    }
}
