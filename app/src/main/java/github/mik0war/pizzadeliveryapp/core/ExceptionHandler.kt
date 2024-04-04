package github.mik0war.pizzadeliveryapp.core

interface ExceptionHandler<T> {
    fun mapExceptionToModel(exception: Exception): T
}

