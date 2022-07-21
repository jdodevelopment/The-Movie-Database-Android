package ar.com.jdodevelopment.tmdb.domain.entity

sealed class Error {

    sealed class Server(val code: Int? = null, val message: String? = null) : Error() {
        class NotFound(code: Int?, message: String?) : Server(code, message)
        class Unauthorized(code: Int?, message: String?) : Server(code, message)
        class Unknown(code: Int?, message: String?) : Server(code, message)
    }

    class Network : Error()
    class Unknown : Error()

}