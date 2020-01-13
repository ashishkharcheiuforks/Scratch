package com.appspell.scratchapplication.network.oauth


data class OauthTokenRequest(
    val client_id: String,
    val client_secret: String,
    val code: String
)