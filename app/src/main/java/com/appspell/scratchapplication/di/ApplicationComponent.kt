package com.appspell.scratchapplication.di

import android.app.Application
import com.appspell.scratchapplication.db.TestDao
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DbModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun provideRetrofit(): Retrofit

    fun provideTestDao(): TestDao
}