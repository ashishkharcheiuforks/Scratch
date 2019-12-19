package com.appspell.scratchapplication.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.ScratchApplication
import com.appspell.scratchapplication.features.di.DaggerMainActivityComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerMainActivityComponent
            .builder()
            .appComponenent(ScratchApplication.applicationComponent)
            .context(this)
            .build()
    }
}
