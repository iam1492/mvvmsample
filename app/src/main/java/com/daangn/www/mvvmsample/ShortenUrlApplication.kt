package com.daangn.www.mvvmsample

import android.app.Application
import com.daangn.www.mvvmsample.di.appModules
import org.koin.android.ext.android.startKoin

class ShortenUrlApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModules)
    }
}