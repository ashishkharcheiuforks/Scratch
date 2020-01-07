package com.appspell.scratchapplication.features.downloader

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.appspell.scratchapplication.ScratchApplication
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

class DownloadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    companion object {
        const val PARAMETER_URL = "PARAMETER_URL"
        const val OUT_PARAMETER_URL = "OUT_PARAMETER_URL"
    }

    private val source = ScratchApplication.applicationComponent.getDownloadImageSource()

    override fun doWork(): Result {
        Timber.i("Worker started")

        val url = inputData.getString(PARAMETER_URL)
        return if (url.isNullOrEmpty()) {
            Result.failure()
        } else {
            Timber.i("Worker url: $url")

            val body = source.downloadImage(url).execute()

            if (!body.isSuccessful) {
                Result.failure()
            }

            val fileName = body.body()?.byteStream()?.let {
                writeToDisk(it.readBytes())
            }

            if (fileName.isNullOrEmpty()) {
                Result.failure()
            }

            val outData = workDataOf(OUT_PARAMETER_URL to fileName)

            return Result.success(outData)
        }
    }

    private fun writeToDisk(byteArray: ByteArray): String {
        try {
            val tmpFile = File.createTempFile("tmp_file", ".jpg", applicationContext.cacheDir)
            val fileOutputStream = FileOutputStream(tmpFile)
            fileOutputStream.write(byteArray)
            return tmpFile.absolutePath
        } catch (e: Exception) {
            Timber.e(e)
        }
        return ""
    }
}