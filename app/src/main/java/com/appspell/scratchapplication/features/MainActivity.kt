package com.appspell.scratchapplication.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.ScratchApplication
import com.appspell.scratchapplication.db.DbEntity
import com.appspell.scratchapplication.db.TestDao
import com.appspell.scratchapplication.features.di.DaggerMainActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dao: TestDao

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

        dao.add(DbEntity("key", "test_value"))
            .subscribeOn(Schedulers.io())
            .subscribe({}, {
                text.text = it.message
            })
            .apply { disposable.add(this) }

        dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                text.text = it.first().value
            }
            .subscribe({}, {
                text.text = it.message
            })
            .apply { disposable.add(this) }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
