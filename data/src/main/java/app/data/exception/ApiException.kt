package app.data.exception

class ApiException(val code: Int, message: String?) : Exception(message) {
    fun code(): Int {
        return code
    }
}