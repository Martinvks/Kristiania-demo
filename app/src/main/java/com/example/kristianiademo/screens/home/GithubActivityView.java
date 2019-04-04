package com.example.kristianiademo.screens.home;

import java.util.List;

public interface GithubActivityView {
    void showGithubRepoList(List<String> repositories);
    void showErrorMessage(String message);
}
