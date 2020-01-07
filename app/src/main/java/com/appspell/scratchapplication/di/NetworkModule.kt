package com.appspell.scratchapplication.di

import com.appspell.scratchapplication.features.downloader.DownloadImageSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://appspell.com")
        .build()

    @Provides
    @Singleton
    fun provideDownloadApi(retrofit: Retrofit): DownloadImageSource =
        retrofit.create(DownloadImageSource::class.java)

}