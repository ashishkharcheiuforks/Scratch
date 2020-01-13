package com.appspell.scratchapplication.features.data

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface GitHubApi {

    @GET("https://api.github.com/user/repos")
    fun fetchData(): Single<ResponseBody>
}