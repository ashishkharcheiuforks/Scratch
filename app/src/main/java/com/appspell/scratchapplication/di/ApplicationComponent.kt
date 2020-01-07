package com.appspell.scratchapplication.di

import android.app.Application
import com.appspell.scratchapplication.features.downloader.DownloadImageSource
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun getDownloadImageSource(): DownloadImageSource
}