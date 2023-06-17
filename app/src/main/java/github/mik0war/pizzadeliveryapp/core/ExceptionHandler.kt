package github.mik0war.entity

interface ExceptionHandler<T> {
    fun mapExceptionToModel(exception: Exception): T
}

