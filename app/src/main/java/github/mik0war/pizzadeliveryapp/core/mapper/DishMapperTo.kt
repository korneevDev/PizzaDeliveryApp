package github.mik0war.pizzadeliveryapp.core.mapper


interface DishMapperTo<T> : MapperTo<T> {
    fun mapSuccess(id: Int, name: String, description: String, price: Int, imageUrl: String): T

    fun mapError(errorName: String): T
}


