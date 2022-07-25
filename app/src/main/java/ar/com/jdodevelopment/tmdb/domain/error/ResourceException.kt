package ar.com.jdodevelopment.tmdb.domain.error

sealed class ResourceException(message: String?) : Exception(message) {

    sealed class Server(val code: Int? = null, message: String? = null) : ResourceException(message) {
        class NotFound(code: Int?, message: String?) : Server(code, message)
        class Unauthorized(code: Int?, message: String?) : Server(code, message)
        class Unknown(code: Int?, message: String?) : Server(code, message)
    }

    class Network(message: String?) : ResourceException(message)
    class Unknown(message: String?) : ResourceException(message)

}