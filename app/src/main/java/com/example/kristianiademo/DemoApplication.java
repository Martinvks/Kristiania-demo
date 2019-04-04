package com.example.kristianiademo;

import android.app.Activity;
import android.app.Application;

public class DemoApplication extends Application {

    private DemoApplicationComponent demoApplicationComponent;

    public static DemoApplication get(Activity activity) {
        return (DemoApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        demoApplicationComponent = DaggerDemoApplicationComponent.builder().build();
    }

    public DemoApplicationComponent component() {
        return demoApplicationComponent;
    }
}
