package com.appspell.scratchapplication.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.ScratchApplication
import com.appspell.scratchapplication.features.di.DaggerMainActivityComponent
import com.appspell.scratchapplication.features.downloader.DownloadWorker
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

private const val DOWNLOAD_URL =
    "https://mars.nasa.gov/system/downloadable_items/40047_PIA02406.jpg"

class MainActivity : AppCompatActivity() {

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

        startDownload.setOnClickListener {
            // Start worker
            val inputData = Data.Builder()
                .putString(DownloadWorker.PARAMETER_URL, DOWNLOAD_URL)
                .build()

            val request = OneTimeWorkRequestBuilder<DownloadWorker>()
                .setInputData(inputData)
                .build()

            WorkManager.getInstance(this).enqueue(request)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
