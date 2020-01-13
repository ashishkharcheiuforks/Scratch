package com.appspell.scratchapplication.network.oauth

import com.squareup.moshi.Json

data class OAuthResponse (
    @Json(name = "access_token")
    val accessToken: String
)