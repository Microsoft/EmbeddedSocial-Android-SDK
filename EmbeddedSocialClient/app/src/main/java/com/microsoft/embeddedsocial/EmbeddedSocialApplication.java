/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.embeddedsocial;

import com.microsoft.embeddedsocial.sdk.EmbeddedSocial;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import java.lang.Thread.UncaughtExceptionHandler;

import androidx.multidex.MultiDex;

/**
 * Application class for the project.
 */
public class EmbeddedSocialApplication extends Application implements UncaughtExceptionHandler {

    private static final UncaughtExceptionHandler DEFAULT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build());
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        EmbeddedSocial.init(this, R.raw.embedded_social_config);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        DEFAULT_EXCEPTION_HANDLER.uncaughtException(thread, ex);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
