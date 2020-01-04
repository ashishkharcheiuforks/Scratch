package com.appspell.scratchapplication.features.di

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.databinding.ActivityMainBinding
import com.appspell.scratchapplication.features.data.MainRepository
import com.appspell.scratchapplication.features.data.MainRepositoryImpl
import com.appspell.scratchapplication.features.data.MarsApi
import com.appspell.scratchapplication.features.domain.MainInteractor
import com.appspell.scratchapplication.features.domain.MainInteractorImpl
import com.appspell.scratchapplication.features.presentation.MainView
import com.appspell.scratchapplication.features.presentation.MainViewImpl
import com.appspell.scratchapplication.features.presentation.MainViewModel
import com.appspell.scratchapplication.features.presentation.MainViewModelImpl
import com.appspell.scratchapplication.utils.viewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class MainActivityModule {

    @Module
    companion object {
        @Provides
        @ActivityScope
        @JvmStatic
        fun provideViewModel(
            activity: AppCompatActivity,
            interactor: MainInteractor
        ): MainViewModel =
            activity.viewModel { MainViewModelImpl(interactor) }

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideDataBinding(activity: AppCompatActivity): ActivityMainBinding =
            DataBindingUtil.setContentView(activity, R.layout.activity_main)

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideGitHubApi(retrofit: Retrofit): MarsApi = retrofit.create(MarsApi::class.java)
    }

    @Binds
    @ActivityScope
    abstract fun bindRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    @ActivityScope
    abstract fun bindInteractor(impl: MainInteractorImpl): MainInteractor

    @Binds
    @ActivityScope
    abstract fun bindView(impl: MainViewImpl): MainView
}