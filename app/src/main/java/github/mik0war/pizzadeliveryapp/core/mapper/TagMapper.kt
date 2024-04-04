package github.mik0war.pizzadeliveryapp.core.mapper

interface TagMapper<T> {
    fun mapSuccess(id: Int, name: String): T
    fun mapError(errorName: String): T
}