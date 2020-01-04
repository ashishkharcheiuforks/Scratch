package com.appspell.scratchapplication.features.data

import io.reactivex.Single
import retrofit2.http.GET

interface MarsApi {

    @GET("realestate")
    fun fetchRealestate(): Single<List<MarsPropertyDTO>>
}