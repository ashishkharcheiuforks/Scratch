package com.appspell.scratchapplication.features.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("repos/{org}/{repositoryName}")
    fun fetchRepositoryList(
        @Path("org") org: String,
        @Path("repositoryName") repositoryName: String
    ): Single<RepositoryResponse>
}