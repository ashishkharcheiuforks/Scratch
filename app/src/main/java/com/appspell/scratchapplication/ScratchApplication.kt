package com.appspell.scratchapplication

import android.app.Application
import com.appspell.scratchapplication.di.ApplicationComponent
import com.appspell.scratchapplication.di.DaggerApplicationComponent

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
    }
}