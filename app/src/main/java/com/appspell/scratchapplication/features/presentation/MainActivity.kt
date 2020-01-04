package com.appspell.scratchapplication.features.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appspell.scratchapplication.ScratchApplication
import com.appspell.scratchapplication.features.di.DaggerMainActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var binder: MainBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainActivityComponent
            .builder()
            .appComponent(ScratchApplication.applicationComponent)
            .activity(this)
            .build()
            .inject(this)

        binder.bind(this)
    }
}
