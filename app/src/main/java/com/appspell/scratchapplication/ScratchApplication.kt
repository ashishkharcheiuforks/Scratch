package com.appspell.scratchapplication

import android.app.Application
import com.appspell.scratchapplication.di.ApplicationComponent
import com.appspell.scratchapplication.di.DaggerApplicationComponent
import timber.log.Timber

class ScratchApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()

        Timber.plant( if(BuildConfig.DEBUG) Timber.DebugTree() else object: Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

            }
        })
    }
}