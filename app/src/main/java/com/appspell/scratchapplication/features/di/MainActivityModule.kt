package com.appspell.scratchapplication.features.di

import androidx.appcompat.app.AppCompatActivity
import com.appspell.scratchapplication.features.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindActvity(impl: MainActivity): AppCompatActivity
}