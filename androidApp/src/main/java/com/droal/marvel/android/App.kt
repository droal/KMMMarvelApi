package com.droal.marvel.android

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.droal.marvel.di.ContextArg
import com.droal.marvel.di.InjectorCommon

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        InjectorCommon.provideContextArgs(ContextArg(this))
    }
}