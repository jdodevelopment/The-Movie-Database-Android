package ar.com.jdodevelopment.tmdb.data.interceptor

import ar.com.jdodevelopment.tmdb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authorizationHeader = "Bearer $TOKEN"
        val newRequest = chain.request()
            .newBuilder()
            .header(AUTHORIZATION_HEADER_NAME, authorizationHeader)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val AUTHORIZATION_HEADER_NAME = "Authorization"
        private const val TOKEN = BuildConfig.TMDB_API_KEY
    }

}