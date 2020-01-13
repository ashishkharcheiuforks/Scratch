package com.appspell.scratchapplication.network.oauth

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Provider

private const val AUTHORIZATION_HEADER_KEY = "Authorization"
private const val AUTHORIZATION_HEADER_BEARER = "Bearer"

private const val OAUTH_ENDPOINT = "https://api.github.com/login/oauth/access_token"


// you can get tokens here https://github.com/settings/applications/new
private const val CLIENT_ID = "97549d53844026aa7f2a"
private const val CLIENT_SECRET = "679aef320a733eb63f673a8adf354e6e5cb46e62"

class OAuthInterceptor(private val oauthAPI: Provider<OAuthBasicAPI>) : Interceptor {
    private var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(executeMainRequest(chain))
        return when {
            response.code == 401 && response.request.url.toString() == OAUTH_ENDPOINT -> response
            response.code == 401 || response.code == 403 -> {
                // request a new token
                refreshToken()
                // execute origin
                chain.proceed(executeMainRequest(chain))
            }
            else -> response
        }
    }

    private fun executeMainRequest(chain: Interceptor.Chain) =
        chain.request()
            .newBuilder()
            .apply {
                if (!token.isNullOrEmpty()) {
                    header(AUTHORIZATION_HEADER_KEY, "$AUTHORIZATION_HEADER_BEARER $token")
                }
            }
            .build()

    private fun refreshToken() {
        startBasicAuth()
    }

    private fun startBasicAuth() {
        val code = oauthAPI.get().authorize(clientId = CLIENT_ID).blockingGet()
        val response = oauthAPI.get().refreshToken(
            OauthTokenRequest(
                client_id = CLIENT_ID,
                client_secret = CLIENT_SECRET,
                code = code
            )
        ).blockingGet()
        token = response.accessToken
    }
}