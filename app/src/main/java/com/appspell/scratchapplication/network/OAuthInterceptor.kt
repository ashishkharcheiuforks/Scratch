package com.appspell.scratchapplication.network

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHORIZATION_HEADER_KEY = "Authorization"
private const val AUTHORIZATION_HEADER_BEARER = "Bearer"

private const val OAUTH_ENDPOINT = "https://github.com/login/oauth/authorize"

class OAuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(executeMainRequest(chain))
        return when {
            response.code == 401 && response.request.url.toString() == OAUTH_ENDPOINT -> response
            response.code == 401 -> {
                refreshToken()
                chain.proceed(executeMainRequest(chain))
            }
            else -> response
        }
    }

    private fun executeMainRequest(chain: Interceptor.Chain) =
        chain.request()
            .newBuilder()
            .apply {
                if (!getToken().isNullOrEmpty()) {
                    header(AUTHORIZATION_HEADER_KEY, "$AUTHORIZATION_HEADER_BEARER ${getToken()}")
                }
            }
            .build()

    private fun refreshToken() {

    }

    private fun getToken(): String? = "TOKEN"
}