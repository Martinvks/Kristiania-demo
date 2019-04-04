package com.example.kristianiademo.screens.home;

import com.example.kristianiademo.DemoApplicationComponent;
import com.example.kristianiademo.dagger.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = GithubActivityModule.class, dependencies = DemoApplicationComponent.class)
public interface GithubActivityComponent {
    void injectGithubActivity(GithubActivity activity);
}
