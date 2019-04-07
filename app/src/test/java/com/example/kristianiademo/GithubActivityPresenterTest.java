package com.example.kristianiademo;

import com.example.kristianiademo.model.Repo;
import com.example.kristianiademo.network.GithubService;
import com.example.kristianiademo.screens.home.GithubActivityPresenter;
import com.example.kristianiademo.screens.home.GithubActivityView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

public class GithubActivityPresenterTest {

    @Mock
    GithubService githubService;

    @Mock
    GithubActivityView githubActivityView;

    private GithubActivityPresenter githubActivityPresenter;

    private InOrder inOrder;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        MockitoAnnotations.initMocks(this);
        inOrder = inOrder(githubActivityView);

        githubActivityPresenter = new GithubActivityPresenter(githubService);
        githubActivityPresenter.setGithubActivityView(githubActivityView);
    }

    @Test
    public void listRepositoriesForGivenUserTest() {
        when(githubService.listRepos("testUser")).thenReturn(
                Observable.fromCallable(this::getRepoList)
        );

        githubActivityPresenter.onGithubUserSearch("testUser");
        inOrder.verify(githubActivityView).hideSoftKeyboard();
        inOrder.verify(githubActivityView).showGithubRepoList(eq(
                Arrays.asList(
                        "Test repository",
                        "Test repository 2"
                )
        ));
    }

    @Test
    public void showErrorMessageOnNetowrkError() {
        when(githubService.listRepos("testUser")).thenReturn(
                Observable.error(new Exception())
        );

        githubActivityPresenter.onGithubUserSearch("testUser");
        inOrder.verify(githubActivityView).hideSoftKeyboard();
        inOrder.verify(githubActivityView).showErrorMessage("Ooops");
    }

    private List<Repo> getRepoList() {
        return Arrays.asList(
                new Repo(1, "Test repository", "https://github.com/testRepo"),
                new Repo(2, "Test repository 2", "https://github.com/testRepo2")
        );
    }
}