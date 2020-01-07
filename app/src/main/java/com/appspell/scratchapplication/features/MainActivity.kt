package com.appspell.scratchapplication.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.ScratchApplication
import com.appspell.scratchapplication.db.TestDao
import com.appspell.scratchapplication.db.TestEntity
import com.appspell.scratchapplication.features.di.DaggerMainActivityComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
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

        // Clear all and add first value
        dao.add(TestEntity("first${System.currentTimeMillis()}", "first value: ${System.currentTimeMillis()}"))
            .doOnSubscribe {
                // delete all values before add a new one
                dao.clearAll()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                text.text = it.message
            })
            .apply { disposable.add(this) }

        // Emit values periodically
        Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMapMaybe { time ->
                dao.add(TestEntity("key", "interval value $time"))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                text.text = it.message
            })
            .apply { disposable.add(this) }

        // Observe results
        dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                text.text = it.last().value
            }, {
                text.text = it.message
            })
            .apply { disposable.add(this) }


    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
