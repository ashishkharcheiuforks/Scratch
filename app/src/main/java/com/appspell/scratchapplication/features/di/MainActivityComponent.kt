package com.appspell.scratchapplication.features.di

import androidx.appcompat.app.AppCompatActivity
import com.appspell.scratchapplication.di.ApplicationComponent
import com.appspell.scratchapplication.features.MainActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [MainActivityModule::class],
    dependencies = [ApplicationComponent::class]
)
interface MainActivityComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder

        fun applicationComponent(component: ApplicationComponent): Builder

        fun build(): MainActivityComponent

    }

    fun inject(mainActivity: MainActivity)
}