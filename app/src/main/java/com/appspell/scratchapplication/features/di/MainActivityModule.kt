package com.appspell.scratchapplication.features.di

import com.appspell.scratchapplication.features.data.GitHubApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi = retrofit.create(GitHubApi::class.java)
}