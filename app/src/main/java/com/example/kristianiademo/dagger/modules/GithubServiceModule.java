package com.example.kristianiademo.dagger.modules;

import com.example.kristianiademo.dagger.scopes.DemoApplicationScope;
import com.example.kristianiademo.network.GithubService;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class GithubServiceModule {

    @DemoApplicationScope
    @Provides
    public GithubService githubService(Retrofit githubRetrofit) {
        return githubRetrofit.create(GithubService.class);
    }

    @DemoApplicationScope
    @Provides
    public Retrofit retrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .build();
    }
}
