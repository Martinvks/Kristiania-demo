package com.example.kristianiademo.dagger.modules;

import com.example.kristianiademo.dagger.scopes.DemoApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    @DemoApplicationScope
    @Provides
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @DemoApplicationScope
    @Provides
    public OkHttpClient okHttpClient() {
        return new OkHttpClient
                .Builder()
                .build();
    }
}
