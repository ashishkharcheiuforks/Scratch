package com.appspell.scratchapplication.network.oauth

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OAuthBasicAPI {

    @GET("https://github.com/login/oauth/authorize")
    fun authorize(@Query("client_id") clientId: String): Single<String>

    @POST("https://github.com/login/oauth/access_token")
    fun refreshToken(@Body body: OauthTokenRequest): Single<OAuthResponse>
}