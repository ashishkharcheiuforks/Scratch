package com.appspell.scratchapplication.features.downloader

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface DownloadImageSource {

    @GET
    fun downloadImage(@Url url: String): Call<ResponseBody>
}