package com.example.kristianiademo;

import com.example.kristianiademo.dagger.modules.GithubServiceModule;
import com.example.kristianiademo.dagger.scopes.DemoApplicationScope;
import com.example.kristianiademo.network.GithubService;

import dagger.Component;

@DemoApplicationScope
@Component(modules = {GithubServiceModule.class})
public interface DemoApplicationComponent {
    GithubService getGithubService();
}
