package ar.com.jdodevelopment.tmdb.data.error

import ar.com.jdodevelopment.tmdb.data.dto.HttpErrorResponseDTO
import ar.com.jdodevelopment.tmdb.domain.entity.Error
import ar.com.jdodevelopment.tmdb.domain.error.ErrorResolver
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject


class DefaultErrorResolver @Inject constructor(
    private val gson: Gson,
) : ErrorResolver {

    override fun resolve(throwable: Throwable): Error {
        return when (throwable) {
            is HttpException -> resolveHttpException(throwable)
            is IOException -> Error.Network()
            else -> Error.Unknown()
        }
    }

    private fun resolveHttpException(httpException: HttpException): Error.Server {
        val code = httpException.code()
        val errorBody = httpException.response()?.errorBody()?.string()
        val errorResponseDTO = parseErrorResponse(errorBody)
        val finalMessage = errorResponseDTO?.statusMessage
        return when (code) {
            HttpURLConnection.HTTP_UNAUTHORIZED ->
                Error.Server.Unauthorized(errorResponseDTO?.statusCode, finalMessage)
            HttpURLConnection.HTTP_NOT_FOUND ->
                Error.Server.NotFound(errorResponseDTO?.statusCode, finalMessage)
            else ->
                Error.Server.Unknown(errorResponseDTO?.statusCode, finalMessage)
        }
    }

    private fun parseErrorResponse(httpBody: String?): HttpErrorResponseDTO? {
        return try {
            gson.fromJson(httpBody, HttpErrorResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            null
        }
    }

}