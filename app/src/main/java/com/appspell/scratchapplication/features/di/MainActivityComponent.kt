package com.appspell.scratchapplication.features.di

import android.content.Context
import com.appspell.scratchapplication.di.ActivityScope
import com.appspell.scratchapplication.di.ApplicationComponent
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun appComponenent(component: ApplicationComponent): Builder

        fun build(): MainActivityComponent
    }
}