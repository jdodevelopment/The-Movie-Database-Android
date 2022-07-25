package ar.com.jdodevelopment.tmdb.data.error

import ar.com.jdodevelopment.tmdb.data.dto.HttpErrorResponseDTO
import ar.com.jdodevelopment.tmdb.domain.error.ResourceException
import ar.com.jdodevelopment.tmdb.domain.error.ExceptionResolver
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject


class DefaultExceptionResolver @Inject constructor(
    private val gson: Gson,
) : ExceptionResolver {

    override fun resolve(throwable: Throwable): ResourceException {
        return when (throwable) {
            is HttpException -> resolveHttpException(throwable)
            is IOException -> ResourceException.Network(throwable.message)
            else -> ResourceException.Unknown(throwable.message)
        }
    }

    private fun resolveHttpException(httpException: HttpException): ResourceException.Server {
        val code = httpException.code()
        val errorBody = httpException.response()?.errorBody()?.string()
        val errorResponseDTO = parseErrorResponse(errorBody)
        val finalMessage = errorResponseDTO?.statusMessage
        return when (code) {
            HttpURLConnection.HTTP_UNAUTHORIZED ->
                ResourceException.Server.Unauthorized(errorResponseDTO?.statusCode, finalMessage)
            HttpURLConnection.HTTP_NOT_FOUND ->
                ResourceException.Server.NotFound(errorResponseDTO?.statusCode, finalMessage)
            else ->
                ResourceException.Server.Unknown(errorResponseDTO?.statusCode, finalMessage)
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