package com.appspell.scratchapplication.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.ScratchApplication
import com.appspell.scratchapplication.features.data.GitHubApi
import com.appspell.scratchapplication.features.di.DaggerMainActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: GitHubApi

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent
            .builder()
            .applicationComponent(ScratchApplication.applicationComponent)
            .activity(this)
            .build()
            .inject(this)

        // just random api to test
        api.fetchRepositoryList(org = "appspell", repositoryName = "Scratch")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    text.text = it.toString()
                },
                {
                    text.text = it.message
                }
            )
            .apply { disposable.add(this) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
