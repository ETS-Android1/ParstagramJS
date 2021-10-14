package com.example.parstagramjs;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("dLK7j6Z1r6P4Nyt0W7X2QFaD4BcSp34Bnxjqha98")
                .clientKey("mKakpHCuwlUThtRINNS3F3QonhIdhV6PyvmWtNDc")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
