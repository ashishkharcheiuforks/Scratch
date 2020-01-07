package com.appspell.scratchapplication.features.downloader

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber

class DownloadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    companion object {
        const val PARAMETER_URL = "PARAMETER_URL"
    }

    override fun doWork(): Result {
        Timber.i("Worker started")

        val url = inputData.getString(PARAMETER_URL)
        return if (url.isNullOrEmpty()) {
            Result.failure()
        } else {
            Timber.i("Worker url: $url")

            return Result.success()
        }
    }
}