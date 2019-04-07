package com.example.kristianiademo.dagger.modules;

import com.example.kristianiademo.dagger.scopes.DemoApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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

    @DemoApplicationScope
    @Provides
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
