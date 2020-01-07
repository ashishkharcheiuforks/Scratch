package com.appspell.scratchapplication.features

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.appspell.scratchapplication.R
import com.appspell.scratchapplication.ScratchApplication
import com.appspell.scratchapplication.features.di.DaggerMainActivityComponent
import com.appspell.scratchapplication.features.downloader.DownloadWorker
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

private const val DOWNLOAD_URL =
    "https://mars.nasa.gov/system/downloadable_items/40047_PIA02406.jpg"

private const val PERMISSION_REQUEST_CODE = 123

private const val WORKER_TAG = "WORKER_TAG"

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
            startBackgroundDownloadProcess()
        }

        requestPermissions()
    }

    private fun startBackgroundDownloadProcess() {
        val inputData = Data.Builder()
            .putString(DownloadWorker.PARAMETER_URL, DOWNLOAD_URL)
            .build()

        val request = OneTimeWorkRequestBuilder<DownloadWorker>()
            .addTag(WORKER_TAG)
            .setInputData(inputData)
            .build()

        // start
        WorkManager.getInstance(applicationContext).enqueue(request)

        // observe result
        WorkManager.getInstance(applicationContext).getWorkInfosByTagLiveData(WORKER_TAG)
            .observe(this, Observer { list ->
                val url = list.first().outputData.getString(DownloadWorker.OUT_PARAMETER_URL)
                Timber.i("Received result from worker: $url")

                showDownloadedImage(url)
            })
    }

    private fun showDownloadedImage(url: String?) {
        val bitmap = BitmapFactory.decodeFile(url)
        image.setImageBitmap(bitmap)
    }

    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                AlertDialog.Builder(this)
                    .setMessage(R.string.request_permissions)
                    .create()
                    .show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, R.string.permission_granted, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, R.string.permission_has_not_granted, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
