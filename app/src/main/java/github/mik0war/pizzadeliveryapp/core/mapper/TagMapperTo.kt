package github.mik0war.pizzadeliveryapp.core.mapper

interface TagMapperTo<T>: MapperTo<T> {
    fun mapSuccess(id: Int, name: String): T
    fun mapError(errorName: String): T
}