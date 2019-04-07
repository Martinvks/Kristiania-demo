package com.example.kristianiademo.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kristianiademo.DemoApplication;
import com.example.kristianiademo.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GithubActivity extends AppCompatActivity implements GithubActivityView {

    @Inject
    GithubActivityPresenter githubActivityPresenter;

    private ArrayAdapter<String> stringArrayAdapter;
    private ArrayList<String> repositoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerGithubActivityComponent.builder()
                .demoApplicationComponent(DemoApplication.get(this).component())
                .build()
                .injectGithubActivity(this);


        EditText editText = findViewById(R.id.edit_text_github);
        Button searchButton = findViewById(R.id.button_github_search);
        searchButton.setOnClickListener((view) -> {
            githubActivityPresenter.onGithubUserSearch(editText.getText().toString());
        });

        ListView listView = findViewById(R.id.repo_list_view);

        repositoryList = new ArrayList<>();

        stringArrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.repo_list_view_item,
                repositoryList
        );

        listView.setAdapter(stringArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        githubActivityPresenter.setGithubActivityView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        githubActivityPresenter.detatch();
    }

    /* GithubActivityView callbacks */

    @Override
    public void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );
            imm.hideSoftInputFromWindow(
                    view.getWindowToken(),
                    InputMethodManager.RESULT_UNCHANGED_SHOWN
            );
        }
    }

    @Override
    public void showGithubRepoList(List<String> repositories) {
        repositoryList.clear();
        repositoryList.addAll(repositories);
        stringArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast toast = Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        );
        toast.show();
    }
}
